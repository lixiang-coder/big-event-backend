package com.xzy.interceptors;

import com.xzy.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 统一拦截
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 解析jwt令牌
        String token = request.getHeader("Authorization");

        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 解析没报错，则放行
            return true;
        } catch (Exception e) {
            // http响应状态码为401
            response.setStatus(401);
            // 解析失败，报错不放行
            return false;
        }

    }
}
