package com.xzy.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;//主键ID

    //让springMVC把当前对象转成json字符串时，忽略password，最终返回json字符串中就没有password这个属性了，别引错包
    @JsonIgnore
    private String password;//密码

    private String username;//用户名

    private String nickname;//昵称

    private String email;//邮箱

    private String userPic;//用户头像地址

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//更新时间
}
