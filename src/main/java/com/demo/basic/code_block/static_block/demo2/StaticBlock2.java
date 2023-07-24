package com.demo.basic.code_block.static_block.demo2;


/**
 * 类加载的时候会完成静态变量初始化和静态代码块的执行，两者的顺序取决于声明的顺序(类加载时候会执行静态代码块，给静态变量初始化)
 */
public class StaticBlock2 {

    /**
     * 类加载时，静态变量就会被初始化
     */
    private static String city=setAndGetCity();


    /**
     * 类加载时，静态变量就会被执行
     */
    static {
        System.out.println("static block 1 is called");
        city="Shanghai";
    }

    /**
     * 类加载时，静态变量就会被执行
     */
    static {
        System.out.println("static block 2 is called");
        company="Ericsson";
    }

    private static String company="ByteCodeDance";

    public static String setAndGetCity() {
        String city = "Weifang";
        System.out.println("setAndGetCity() is invoked");
        return city;
    }

    /**
     * main方法执行之前，类已经被加载，静态代码块也被调用
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("city="+city);
        System.out.println("company="+company);
    }
}
