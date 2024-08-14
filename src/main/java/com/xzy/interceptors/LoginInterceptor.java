package com.xzy.interceptors;

import com.xzy.utils.JwtUtil;
import com.xzy.utils.ThreadLocalUtil;
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
            // 将解析的信息放入ThreadLocalUtil中，后面哪里哪里需要哪里用
            ThreadLocalUtil.set(claims);
            // 解析没报错，则放行
            return true;
        } catch (Exception e) {
            // http响应状态码为401
            response.setStatus(401);
            // 解析失败，报错不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 一次请求完成后，清空ThreadLocalUtil中的数据，防止内存泄露
        ThreadLocalUtil.remove();
    }
}
