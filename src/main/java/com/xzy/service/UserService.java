package com.xzy.service;

import com.xzy.pojo.User;

import java.util.Map;

public interface UserService {

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 注册用户
     *
     * @param username
     * @param password
     */
    void register(String username, String password);


    /**
     * 更新用户信息
     *
     * @param user
     */
    void update(User user);

    /**
     * 更新用户头像
     *
     * @param avatarUrl
     */
    void updateAvatar(String avatarUrl);

    /**
     * 更新用户密码
     * @param newPwd
     */
    void updatePwd(String newPwd);
}
