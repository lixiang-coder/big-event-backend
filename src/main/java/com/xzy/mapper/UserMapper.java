package com.xzy.mapper;

import com.xzy.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查找用户
     *
     * @param name
     * @return
     */
    @Select("select * from user where username = #{name}")
    User findByUserName(String name);

    /**
     * 注册用户
     *
     * @param name
     * @param md5String
     */
    @Insert("insert into user (username,password,create_time,update_time)" +
            " values (#{name}, #{md5String}, now(), now())")
    void register(String name, String md5String);
}
