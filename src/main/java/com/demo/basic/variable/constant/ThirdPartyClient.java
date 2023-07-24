package com.demo.basic.variable.constant;

import org.testng.annotations.Test;

/**
 * https://codeplayer.vip/p/j7thg
 * 常量引用替换: Java编译器会在编译源代码时，也会进行优化，会将代码中的常量引用替换为对应的字面值
 */
public class ThirdPartyClient {

    /**
     * Constant2.APP_ID  的引用 被替换成了该常量在编译时的字符串字面值
     * Constant2.APP_KEY 的引用 被替换成了该常量在编译时的字符串字面值
     * String text = "theAppId" + "thePassword" + args;
     * 在Java中，常量在初始化赋值后是不能再被改变的，因此Java编译器就会针对常量进行优化，从而在运行时避免变量引用的调用开销。
     */
    @Test
    public void postRequest() {
        String text = Constant2.APP_ID + Constant2.APP_KEY ;
        System.out.println(text);
    }

}