package com.xzy.controller;


import com.xzy.pojo.Result;
import com.xzy.pojo.User;
import com.xzy.service.UserService;
import com.xzy.utils.JwtUtil;
import com.xzy.utils.Md5Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "用户相关接口")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{6,16}$") String username, @Pattern(regexp = "^\\S{6,16}$")String password){
        // 参数校验 使用validation用正则表达式进行校验
        /*if (username == null || username.length() <= 5 || username.length() >= 16
        && password == null || password.length() <= 5 || password.length() >= 16){
            return Result.error("参数不合法");
        }*/

        // 1.查询用户，判断是否存在
        User user = userService.findByUserName(username);
        if (user == null){
            // 2.不存在，则注册
            userService.register(username,password);
            return Result.success();
        }else {
            // 3.存在则提示占用
            return Result.error("用户名已被注册");
        }
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{6,16}$") String username, @Pattern(regexp = "^\\S{6,16}$")String password){
        // 1.根据用户名查询User
        User loginUser = userService.findByUserName(username);
        // 2.判断是否存在该用户
        if (loginUser == null) {
            return Result.error("用户不存在");
        }
        // 3.判断密码是否正确
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            // 代码走到这里表示登录成功
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            // 返回token
            return Result.success(token);
        }

        return Result.error("密码错误");
    }

}
