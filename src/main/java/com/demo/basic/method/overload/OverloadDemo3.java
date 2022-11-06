package com.demo.basic.method.overload;

/**
 * @author epanhai
 */
public class OverloadDemo3 {

    void sum(int a, long b) {
        System.out.println("a method invoked");
    }

    void sum(long a, int b) {
        System.out.println("b method invoked");
    }

    /**
     * 如果在方法中没有匹配的类型参数，并且每个方法都会提升相同数量的参数，那么会出现歧义。如下示例代码，将会产生编译时错误。
     * @param args
     */
    public static void main(String args[]) {
        OverloadDemo3 o = new OverloadDemo3();
        //o.sum(20, 20);// 这里可以将两个int类型的实参中的任意一个自动转换成long类型去匹配相应的函数，因此会给编译器带来困扰
    }



}
