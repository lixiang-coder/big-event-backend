package com.xzy.controller;

import com.xzy.pojo.Category;
import com.xzy.pojo.Result;
import com.xzy.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Tag(name = "文章相关接口")
public class categoryController {
    @Resource
    private CategoryService categoryService;


    /**
     * 新增文章分类
     *
     * @param category
     * @return
     */
    @Operation(summary = "新增文章分类")
    @PostMapping
    public Result add(@RequestBody @Validated Category category) {
        categoryService.add(category);
        return Result.success();
    }


    @GetMapping
    @Operation(summary = "查询文章分类列表")
    public Result<List<Category>> list(){
        List<Category> categoryList = categoryService.list();
        return Result.success(categoryList);
    }

}
