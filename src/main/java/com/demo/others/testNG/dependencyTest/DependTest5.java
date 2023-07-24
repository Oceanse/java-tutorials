package com.demo.others.testNG.dependencyTest;

import org.testng.annotations.Test;

/**
 * threadPoolSize属性告诉TestNG创建一个线程池以通过多个线程运行测试方法。 使用线程池，会大大降低测试方法的运行时间。
 * invocationCount确定TestNG应该运行这个测试方法的次数。
 */
public class DependTest5 {
    @Test(invocationCount = 3, threadPoolSize = 3)
    public void testThreadPools() {

        System.out.printf("Thread Id : %s%n", Thread.currentThread().getId());

    }

}
