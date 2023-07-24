package com.demo.basic.method.valuepass;

import org.testng.annotations.Test;

/**
 * 实参转化传递给形参时候可能会自动类型转换或者向上转型去匹配形参
 * 如果没有找到匹配的数据类型，那么编译器会自动将一个类型转换成另一个类型(自动类型转换或者向上转型)来匹配被调函数
 * 匹配被调函数只能够在满足自动转换或者向上转型的条件下发生
 * @author epanhai
 */
public class ParameterAutoConvert {

    double add(double x, double y) {
        return x + y;
    }

    /**
     * 由于无法匹配到相关方法，第一个参数1和第二个参数自动转换成为double, 匹配到add(double x, double y)
     */
    @Test
    public void testAdd(){
        add(1.0,1);
        add(1.0f,2L);
    }

    /**
     * 如果没有找到匹配的数据类型，那么会隐式地将一个类型提升到另一个类型(自动类型转换);
     * 匹配被调函数只能够在满足自动转换的条件下发生
     * 当参数类型无法匹配又不能自动转换就会报错
     */
    @Test
    public void testAdd2(){
        //由于无法匹配到相关方法，但是两个boolean类型的实参又不能自动转换成double参数，所以会编译报错
        //this.add("1","2");
    }

    double subtract(double x, double y) {
        return x - y;
    }

    /**
     * 参数自动拆箱
     */
    @Test
    public void testSubtract(){
        add(Double.valueOf(1.0),Double.valueOf(2.0));
    }


    double multiply(Double x, Double y) {
        return x - y;
    }

    /**
     * 参数自动装箱
     */
    @Test
    public void testMultiply(){
        multiply(1.0,1.0);
    }

    /**
     * 参数先自动类型转换，再自动装箱
     */
    @Test
    public void testMultiply2(){
        add(1,1);
    }


    void divide(int a, long b) {
        System.out.println("divide1 method invoked");
    }

    void divide(long a, int b) {
        System.out.println("divide2 method invoked");
    }

    /**
     * 存在重载函数时候，发生参数类型自动转换匹配被调函数时候可能会存在歧义，混淆编译器
     * 如果在方法中没有匹配的类型参数，并且每个方法都会提升相同数量的参数，那么会出现歧义。如下示例代码，将会产生编译时错误。
     */
    @Test
    public void testAdd3s() {
        //this.divide(20, 20);// 这里可以将两个int类型的实参中的任意一个自动转换成long类型去匹配相应的函数，因此会给编译器带来困扰
    }


}
