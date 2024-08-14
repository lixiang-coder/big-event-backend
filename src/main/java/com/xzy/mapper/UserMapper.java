package com.xzy.mapper;

import com.xzy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查找用户
     */
    @Select("select * from user where username = #{name}")
    User findByUserName(String name);

    /**
     * 注册用户
     */
    @Insert("insert into user (username,password,create_time,update_time)" +
            " values (#{name}, #{md5String}, now(), now())")
    void register(String name, String md5String);

    /**
     * 更新用户信息
     */
    @Update("update user set nickname = #{nickname} ,email = #{email}, update_time = #{updateTime} where id = #{id}")
    void update(User user);

    /**
     * 更新用户头像
     */
    @Update("update user set user_pic = #{avatarUrl},update_time = now() where id = #{id}")
    void updateAvatar(String avatarUrl, Integer id);

    /**
     * 更新用户密码
     */
    @Update("update user set password = #{newPwd} ,update_time = now() where id = #{id}")
    void updatePwd(String newPwd, Integer id);
}
