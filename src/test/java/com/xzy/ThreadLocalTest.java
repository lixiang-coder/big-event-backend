package com.xzy;

import org.apache.ibatis.javassist.expr.NewExpr;
import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

    @Test
    public void testThreadLocalSetAndGET(){
        // 实现线程隔离
        ThreadLocal tl = new ThreadLocal();

        // 开始两个线程
        new Thread(()->{
            tl.set("小蓝");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"蓝色").start();

        new Thread(()->{
            tl.set("小绿");
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
            System.out.println(Thread.currentThread().getName() + ":" + tl.get());
        },"绿色").start();

    }
}
