package com.xzy.mapper;

import com.xzy.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    /**
     * 新增文章分类
     *
     * @param category
     */
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)" +
            "values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);
}
