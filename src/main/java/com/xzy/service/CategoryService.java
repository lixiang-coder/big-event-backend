package com.xzy.service;

import com.xzy.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 新增文章分类
     *
     * @param category
     */
    void add(Category category);

    /**
     * 查询文章分类列表
     *
     * @return
     */
    List<Category> list();
}
