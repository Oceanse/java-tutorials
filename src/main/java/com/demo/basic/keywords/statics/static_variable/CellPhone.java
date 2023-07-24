package com.demo.basic.keywords.statics.static_variable;


/**
 * @author epanhai
 */
public class CellPhone {

    /**
     * 静态变量在类加载的时候会被初始化
     * 静态方法给静态变量赋值
     */
    public static String brand = setAndGetBrand();


    /**
     * 静态代码块在类加载的时候会被加载执行
     * 静态代码块给静态变量赋值
     */
    static {
        System.out.println("static codeBlock is invoked");
        brand = "Huawei";
    }

    public static String setAndGetBrand() {
        System.out.println("setAndGetBrand() is invoked");
        brand = "Oppo";
        return brand;
    }

    public static void main(String[] args) {
        System.out.println(brand);
    }
}