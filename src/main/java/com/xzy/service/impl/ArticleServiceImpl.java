package com.xzy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xzy.mapper.ArticleMapper;
import com.xzy.pojo.Article;
import com.xzy.pojo.PageBean;
import com.xzy.service.ArticleService;
import com.xzy.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        // 补充属性
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.add(article);
    }


    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, String categoryId, String state) {
        // 1.创建PageBean 对象
        PageBean<Article> pageBean = new PageBean<>();

        // 2.开启分页查询，PageHelper
        PageHelper.startPage(pageNum, pageSize);

        // 3.调用mapper
        // 补充属性
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        List<Article> as = articleMapper.list(userId, categoryId, state);
        // Page中提供了方法，可以获取PageHelper分页查询后得到的总记录条数和当前页数据
        Page<Article> p = (Page<Article>) as;

        // 把数据填充到PageBean对象中
        pageBean.setTotal(p.getTotal());
        pageBean.setItems(p.getResult());
        return pageBean;
    }
}
