package com.xzy.service;

import com.xzy.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 新增文章分类
     */
    void add(Category category);

    /**
     * 查询文章分类列表
     */
    List<Category> list();

    /**
     * 获取文章分类详情
     */
    Category detail(Integer id);

    /**
     * 更新文章分类
     */
    void update(Category category);

    /**
     * 删除文章分类
     */
    void delete(Integer id);
}
