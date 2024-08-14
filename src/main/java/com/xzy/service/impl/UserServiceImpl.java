package com.xzy.service.impl;

import com.xzy.mapper.UserMapper;
import com.xzy.pojo.User;
import com.xzy.service.UserService;
import com.xzy.utils.Md5Util;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        // 密码加密
        String md5String = Md5Util.getMD5String(password);
        // 密文存储
        userMapper.register(username, md5String);
    }

}
