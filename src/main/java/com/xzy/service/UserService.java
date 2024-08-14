package com.xzy.service;

import com.xzy.pojo.User;

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
     * @param user
     */
    void update(User user);
}
