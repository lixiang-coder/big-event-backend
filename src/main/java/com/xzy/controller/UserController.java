package com.xzy.controller;

import com.xzy.pojo.Result;
import com.xzy.pojo.User;
import com.xzy.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username,String password){
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

}
