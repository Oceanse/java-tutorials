package com.demo.basic.code_block.static_block.demo1;



/**
 * 执行时机：
 * 静态代码块，它是随着类的加载而被执行，只要类被加载了就会执行，而且只会执行一次；相比之下静态方法是被调用的时候才执行的，所以静态代码块要先于静态方法执行
 * 静态代码块在类加载时(加载.class文件)且在main方法和构造器之前被执行(因为main方法和构造函数执行之前类已经被加载，所以静态代码块已经被执行)，且只执行一次
 * 静态代码块是自动执行的; 静态方法是被调用的时候才执行的。也就是静态方法的执行时机是被调用时候执行，是被动执行的
 *
 *
 * 用途：
 * 因为他会在main方法和构造方法之前被调用，所以通常在静态代码块完成一些预备工作(初始化静态数据成员)，比如初始化连接池，解析；
 *
 * 执行顺序：
 * 一个类中可以有多个静态代码块，自上而下顺次执行
 * 类加载的时候会完成静态变量初始化和静态代码块的执行，两者的顺序取决于声明的顺序(类加载时候会执行静态代码块，给静态变量初始化)
 *
 * Note:
 * 1： 静态代码块只能初始化静态数据成员
 * 2： 静态代码块是在类加载的时候自动执行的; 静态方法是被调用的时候才执行的。也就是静态方法的执行时机是被调用时候执行
 *
 * 语法： static{ }
 *
 * @author epanhai*/
public class StaticBlock {
    public static String nation;
    public static int count;


    static {
        System.out.println("静态代码块被调用");
        nation="China";
    }



    /**
     * 构造方法是被调用的时候才执行的。也就是静态方法的执行时机是被调用时候执行，是被动执行的
     */
    public StaticBlock() {
        count++;
    }



    /**
     * 静态方法是被调用的时候才执行的。也就是静态方法的执行时机是被调用时候执行，是被动执行的
     */
    public static int getCount() {
        return count;
    }


    /**
     * 静态方法是被调用的时候才执行的。也就是静态方法的执行时机是被调用时候执行，是被动执行的
     */
    public static void addCount(){
        count++;
    }



    /**
     * 静态方法是被调用的时候才执行的。也就是静态方法的执行时机是被调用时候执行，是被动执行的
     */
    public static void main(String[] args) {
        //静态变量被静态代码块初始化
        System.out.println("nation = " + nation);

        //静态方法不会主动执行，所以count不变
        System.out.println("count = " + count);

        //手动调用静态方法
        addCount();
        System.out.println("count = " + count);

    }
}
