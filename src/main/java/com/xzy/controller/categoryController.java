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
     */
    @Operation(summary = "新增文章分类")
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }


    /**
     * 查询文章分类列表
     */
    @GetMapping
    @Operation(summary = "查询文章分类列表")
    public Result<List<Category>> list() {
        List<Category> categoryList = categoryService.list();
        return Result.success(categoryList);
    }

    /**
     * 获取文章分类详情
     */
    @Operation(summary = "获取文章分类详情")
    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        Category category = categoryService.detail(id);
        return Result.success(category);
    }

    /**
     * 更新文章分类
     * Validated注解：完成参数校验
     */
    @PutMapping
    @Operation(summary = "更新文章分类")
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "删除文章分类")
    public Result delete(Integer id){
        categoryService.delete(id);
        return Result.success();
    }

}
