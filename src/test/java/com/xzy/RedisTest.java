package com.xzy;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest //在测试类上添加这个注解，那么将来执行单元测试的时候会先初始化spring容器
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testSet() {
        // 往redis中存储一个键值对
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("username", "zhangsan");
        operations.set("id", "1", 10, TimeUnit.SECONDS);
    }

    @Test
    public void testGet() {
        // 从reids中根据键取值
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String username = operations.get("username");
        System.out.println(username);
    }

}
