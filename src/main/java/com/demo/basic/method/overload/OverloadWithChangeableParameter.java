package com.demo.basic.method.overload;

import org.testng.annotations.Test;

public class OverloadWithChangeableParameter {
    /**
     * 关于单参数和可变参数的匹配问题
     *
     * @param names
     */
    public void showNames(String... names) {
        System.out.println("(String... names): ");
        for (String name : names) {
            System.out.print(name);
        }
        System.out.println();
    }


    /**
     * 关于单参数和可变参数的匹配问题
     */
    public void showNames(String name) {
        System.out.println("showNames(String name): ");
        System.out.println(name);
    }

    @Test
    public void testShowNames() {
        showNames("ocean");//精确匹配
        showNames(new String[]{"ocean"});//单参数组匹配可变参数方法
    }
}
