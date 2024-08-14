package com.xzy.config;

import com.xzy.interceptors.LoginInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录接口和注册接口不拦截以及knife4j页面不能拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns(
                "/swagger-ui.html","/swagger-resources/**","/webjars/**","/swagger-ui/**","/v3/api-docs/**","/doc.html",
                "/user/login",
                "/user/register");
    }
}

