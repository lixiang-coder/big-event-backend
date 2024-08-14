package com.xzy.service.impl;

import com.xzy.mapper.CategoryMapper;
import com.xzy.pojo.Category;
import com.xzy.service.CategoryService;
import com.xzy.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        // 补充属性值
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(userId);
        categoryMapper.add(category);
    }


    @Override
    public List<Category> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        return categoryMapper.list(userId);
    }


    @Override
    public Category detail(Integer id) {
        return categoryMapper.detail(id);
    }


    @Override
    public void update(Category category) {
        // 补充属性值
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }
}
