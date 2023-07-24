package com.demo.basic.code_block.static_block.demo3;

/**
 * 静态方法是被调用的时候才执行的。也就是静态方法的执行时机是被调用时候执行，是被动执行的
 */
public class StaticBlock3 {
    public static String nation;
    public static int count;


    /**
     * 静态代码块是自动执行, 随着类的加载而被执行
     */
    static {
        System.out.println("静态代码块被调用");
        nation="China";
    }

    /**
     * 静态方法是被调用的时候才执行的,也就是静态方法的执行时机是被调用时候执行，是被动执行的
     */
    public static int getCount() {
        return count;
    }


    /**
     * 静态方法是被调用的时候才执行的,也就是静态方法的执行时机是被调用时候执行，是被动执行的
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
