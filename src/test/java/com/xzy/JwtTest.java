package com.xzy;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.lang.invoke.VarHandle;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {


    /**
     * 生成jwt令牌
     */
    @Test
    public void testGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "1");
        claims.put("username", "张三");

        // 生成jwt令牌
        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3))   // 添加过期时间为3小时
                .sign(Algorithm.HMAC256("itheima"));    //指定加密算法，配置密钥

        System.out.println(token);
    }

    /**
     * 解析jwt令牌
     */
    @Test
    void parseToken(){
        // 定义字符串模拟用户传入的token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoiMSIsInVzZXJuYW1lIjoi5byg5LiJIn0sImV4cCI6MTcyMzYxMzQ5NH0" +
                ".SxFfdSn4MNY9cByeFpGV711A7cQDK3FdsOAvxOJcFCI";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

        //如果篡改了头部和载荷部分的数据，那么验证失败
        //如果秘钥改了了，验证失败
        //token过期
    }
}
