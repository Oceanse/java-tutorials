package com.demo.basic.code_block.static_block.demo1;

/**
 * 执行时机：
 * 静态代码块是自动执行, 随着类的加载而被执行，只要类被加载了就会执行；静态方法是被调用的时候才执行的,是被动执行，所以静态代码块要先于静态方法执行
 * 静态代码块在类加载时(加载.class文件)且在main方法和构造器之前被执行(因为main方法和构造函数执行之前类已经被加载，所以静态代码块已经被执行)，且只执行一次
 * 静态变量会随着类的加载而被初始化
 *
 * 用途：
 * 因为他会在main方法和构造方法之前被调用，所以通常在静态代码块完成一些预备工作(初始化静态数据成员)，比如初始化连接池，解析；
 *
 * 执行顺序：
 * 一个类中可以有多个静态代码块，自上而下顺次执行
 * 类加载的时候会完成静态变量初始化和静态代码块的执行，两者的顺序取决于声明的顺序(类加载时候会执行静态代码块，给静态变量初始化)
 *
 * Note:
 * 静态代码块只能初始化静态数据成员
 *
 * 语法： static{ }
 *
 * @author epanhai*/
public class StaticBlock {

    static {
        System.out.println("static block 1 is called");
    }

    private static String city=setAndGetCity();

    static {
        System.out.println("static block 2 is called");
    }

    private static String company=setAndGetCompany();

    static {
        System.out.println("static block 3 is called");
        //System.out.println(department);//编译不通过，因为此时静态变量isMarried还没有被初始化，会产生非法前向引用错误
    }
    private static String department="dev";

    public static String setAndGetCity() {
        System.out.println("setAndGetCity() is invoked");
        return "Shanghai";
    }

    public static String setAndGetCompany() {
        System.out.println("setAndGetCompany() is invoked");
        return "ByteCodeDance";
    }

    public static String setAndGetName() {
        System.out.println("setAndGetName() is invoked");
        return "Ocean";
    }

    /**
     * main方法执行之前，类已经被加载，静态代码块也被调用
     * @param args
     */
    public static void main(String[] args) {
    }

}
