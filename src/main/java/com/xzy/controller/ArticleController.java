package com.xzy.controller;

import com.xzy.pojo.Article;
import com.xzy.pojo.Result;
import com.xzy.service.ArticleService;
import com.xzy.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/article")
@Tag(name = "文章管理相关接口")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    // 这里把拦截逻辑统一放在了拦截器中
    /*@Operation(summary = "查询文章列表")
    @GetMapping("/list")
    public Result<String> list(*//*@RequestHeader(name = "Authorization") String token, HttpServletResponse response*//*){
        // 校验token，这行代码报错，则表示没通过验证
        try {
             Map<String, Object> parsedToken = JwtUtil.parseToken(token);
            return Result.success("所有文章的数据");
        } catch (Exception e) {
            // http响应状态码为401
            response.setStatus(401);
            return Result.error("未登录");
        }
        return Result.success("所有文章的数据");
    }*/

    /**
     * 新增文章
     */
    @PostMapping
    @Operation(summary = "新增文章")
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }


}
