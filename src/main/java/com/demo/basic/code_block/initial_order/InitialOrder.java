package com.demo.basic.code_block.initial_order;

/**
 * @author epanhai
 * 初始化顺序：静态变量/静态代码块----->实例变量/实例代码块---->构造方法
 */
public class InitialOrder {
    /**
     * 静态属性，类加载时候就会初始化
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
     * 普通属性
     */
    private String field = setAndGetField();




    /**
     * 构造函数
     */
    public InitialOrder() {
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
        new InitialOrder();
    }
}
