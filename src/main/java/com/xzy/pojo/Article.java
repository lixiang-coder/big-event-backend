package com.xzy.pojo;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {

    private Integer id;//主键ID

    @Pattern(regexp = "^\\S{1,10}$")
    @NotEmpty   // 一般字符串直接用NotEmpty注解进行校验
    private String title;//文章标题

    @NotEmpty
    private String content;//文章内容

    @NotEmpty
    @URL
    private String coverImg;//封面图像

    private String state;//发布状态 已发布|草稿

    @NotNull
    private Integer categoryId;//文章分类id

    private Integer createUser;//创建人ID

    private LocalDateTime createTime;//创建时间

    private LocalDateTime updateTime;//更新时间
}
