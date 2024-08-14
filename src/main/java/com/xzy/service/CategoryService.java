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

    /**
     * 获取文章分类详情
     *
     * @param id
     * @return
     */
    Category detail(Integer id);

    /**
     * 更新文章分类
     * @param category
     */
    void update(Category category);
}
