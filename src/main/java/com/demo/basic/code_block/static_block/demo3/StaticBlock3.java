package com.demo.basic.code_block.static_block.demo3;


/**
 * 加载时机：
 * 静态代码块，它是随着类的加载而被执行，只要类被加载了就会执行，而且只会加载一次
 * 静态代码块在类加载时(加载.class文件)且在main方法和构造器之前被执行(因为main方法和构造函数执行之前类已经被加载，所以静态代码块已经被执行)，且只执行一次
 * 静态代码块是自动执行的; 静态方法是被调用的时候才执行的。也就是静态方法的执行时机是被调用时候执行，是被动执行的
 *
 * 用途：
 * 因为他会在main方法和构造方法之前被调用，所以通常在静态代码块完成一些预备工作(初始化静态数据成员)，比如初始化连接池，解析；
 *
 * 执行顺序：
 * 一个类中可以有多个静态代码块，自上而下顺次执行
 * 类加载的时候会完成静态变量初始化和静态代码块的执行，两者的顺序取决于声明的顺序(类加载时候会执行静态代码块，给静态变量初始化)
 *
 * Note: 只能初始化静态数据成员
 *
 * 语法： static{ }
 *
 * @author epanhai*/
public class StaticBlock3 {

    private static String city="Weifang";


    static {
        System.out.println("static block 1 is called");
        city="Shanghai";
    }

    static {
        System.out.println("static block 2 is called");
        company="Ericsson";
    }
    private static String company="ByteCodeDance";



    /**
     * main方法执行之前，类已经被加载，静态代码块也被调用
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("city="+city);
        System.out.println("company="+company);
    }



}
