package com.xzy.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;
@Data
public class Category {
    @NotNull    // 必须要传
    private Integer id;//主键ID

    // 非空校验
    @NotEmpty   // 必须要传之外，如果是字符串还不能为空
    private String categoryName;//分类名称

    // 非空校验
    @NotEmpty
    private String categoryAlias;//分类别名

    private Integer createUser;//创建人ID

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
}
