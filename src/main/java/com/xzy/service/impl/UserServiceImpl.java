package com.xzy.service.impl;

import com.xzy.mapper.UserMapper;
import com.xzy.pojo.User;
import com.xzy.service.UserService;
import com.xzy.utils.Md5Util;
import com.xzy.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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


    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }


    @Override
    public void updateAvatar(String avatarUrl) {
        // 根据Authorization传的jwt令牌，确定用户身份
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }


    /**
     * 更新用户密码
     *
     * @param newPwd
     */
    @Override
    public void updatePwd(String newPwd) {
        // 根据Authorization传的jwt令牌，确定用户身份
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        // 将加密后的密码存入
        String md5String = Md5Util.getMD5String(newPwd);
        userMapper.updatePwd(md5String,id);
    }


}
