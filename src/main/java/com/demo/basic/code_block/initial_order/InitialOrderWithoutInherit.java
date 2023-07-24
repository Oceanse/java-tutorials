package com.demo.basic.code_block.initial_order;

/**
 * @author epanhai
 * 非继承条件下初始化顺序：静态变量/静态代码块----->实例变量/实例代码块---->构造方法
 * 类加载的时候，类变量和静态代码块都会被初始化和调用，
 * 只有对象创建时候，实例变量初始化、构造代码块、构造函数才会被执行
 * 也就是类加载过程中，实例变量初始化、构造代码块、构造函数不会被调用执行
 */
public class InitialOrderWithoutInherit {
    /**
     * 类变量，类加载时候就会初始化
     */
    private static String staticField = setAndGetStaticField();

    /**
     * 静态代码块,类加载的时候被调用
     */
    static {
        staticField="initialedByStaticCodeBlock";
        System.out.println("静态代码块初始化");
    }

    /**
     *  实例代码块
     */
    {
        System.out.println("实例代码块初始化");
        field="initialedByInstanceCodeBlock";
    }

    /**
     * 实例变量
     */
    private String field = setAndGetField();

    /**
     * 构造函数
     */
    public InitialOrderWithoutInherit() {
        field="initialedByConstructor";
        System.out.println("构造函数初始化");
    }

    public static String setAndGetStaticField() {
        String field = "InitialedBySetStaticField";
        System.out.println("setAndGetStaticField() is invoked");
        return field;
    }

    public static String setAndGetField() {
        String filed = "InitialedBySetField";
        System.out.println("setAndGetField() is invoked");
        return filed;
    }

    public static void main(String[] argc) {
        //如果注释掉new语句，那么就只有类变量和静态代码块被调用初始化
        new InitialOrderWithoutInherit();
    }
}
