package com.xzy.controller;


import com.xzy.pojo.Result;
import com.xzy.pojo.User;
import com.xzy.service.UserService;
import com.xzy.utils.JwtUtil;
import com.xzy.utils.Md5Util;
import com.xzy.utils.ThreadLocalUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Tag(name = "用户相关接口")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{6,16}$") String username, @Pattern(regexp = "^\\S{6,16}$") String password) {
        // 参数校验 使用validation用正则表达式进行校验
        /*if (username == null || username.length() <= 5 || username.length() >= 16
        && password == null || password.length() <= 5 || password.length() >= 16){
            return Result.error("参数不合法");
        }*/

        // 1.查询用户，判断是否存在
        User user = userService.findByUserName(username);
        if (user == null) {
            // 2.不存在，则注册
            userService.register(username, password);
            return Result.success();
        } else {
            // 3.存在则提示占用
            return Result.error("用户名已被注册");
        }
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{6,16}$") String username, @Pattern(regexp = "^\\S{6,16}$") String password) {
        // 1.根据用户名查询User
        User loginUser = userService.findByUserName(username);
        // 2.判断是否存在该用户
        if (loginUser == null) {
            return Result.error("用户不存在");
        }
        // 3.判断密码是否正确
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            // 代码走到这里表示登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            // 将token存入到redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            // 每次登录，都向redis中存入token
            // 如果是个丧心病狂的用户，一段时间疯狂登录怎么办？redis的抗压一般很强，手动登录远远不会让redis奔溃，保底还设置了过期时间
            operations.set(token, token, 1, TimeUnit.HOURS);
            return Result.success(token);
        }

        return Result.error("密码错误");
    }


    @Operation(summary = "获取用户信息")
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/) {
        // 通过前端传过来的token，解析出用户信息
        /*Map<String, Object> map = JwtUtil.parseToken(token);*/
        // 通过ThreadLocalUtil 获取实体类的信息
        Map<String, Object> map = ThreadLocalUtil.get();

        String username = (String) map.get("username");

        User user = userService.findByUserName(username);
        // 注意密码这种东西不能返回给前端
        //user.setPassword(""); 比较low的方法，高级一点的在实体类的属性上加上@JsonIgnore注解
        return Result.success(user);
    }

    /**
     * 更新用户信息
     * 接口方法的实体参数上添加@Validated注解才能使实体类的成员变量上的注解生效（@Email、@NotNull、@NotEmpty）
     */
    @Operation(summary = "更新用户信息")
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    /**
     * 更新用户头像
     *
     * @URL 进行参数url校验
     */
    @Operation(summary = "更新用户头像")
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    /**
     * 更新用户密码
     * RequestBody注解：MVC框架会自动将请求体中的JSON数据转为Map集合对象
     * RequestHeader：从请求头中获取Authorization这个键的值
     */
    @Operation(summary = "更新用户密码")
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        // 1.校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        // 非空且有值
        if (oldPwd == null || newPwd == null || rePwd == null) {
            return Result.error("参数错误");
        }

        if (oldPwd.isEmpty() || newPwd.isEmpty() || rePwd.isEmpty()) {
            return Result.error("参数错误");
        }

        // 新密码和确认密码必须保持一致
        if (!newPwd.equals(rePwd)) {
            return Result.error("两次填写密码不一致");
        }

        // 代码走到这里表示参数没问题，接下来校验原密码是否正确
        // 根据Authorization传的jwt令牌，确定用户身份
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);

        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码填写不正确");
        }

        // 2.调用service，完成密码更新
        userService.updatePwd(newPwd);
        // 删除redis中的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        // 手动删除redis中的token，从而使它失效
        operations.getOperations().delete(token);
        return Result.success();
    }
}
