package com.xzy.service;

import com.xzy.pojo.Article;
import com.xzy.pojo.PageBean;

public interface ArticleService {
    /**
     * 新增文章
     */
    void add(Article article);

    /**
     * 文章列表(条件分页)
     */
    PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state);
}
