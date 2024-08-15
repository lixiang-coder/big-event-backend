package com.xzy.interceptors;

import com.xzy.utils.JwtUtil;
import com.xzy.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * 统一拦截
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 解析jwt令牌
        String token = request.getHeader("Authorization");

        try {
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            // 获取令牌
            String redisToken = operations.get(token);
            if (redisToken == null){
                throw new RuntimeException();
            }
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
