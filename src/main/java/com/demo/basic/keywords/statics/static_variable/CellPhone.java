package com.demo.basic.keywords.statics.static_variable;


/**
 * @author epanhai
 */
public class CellPhone {

    /**
     * 静态变量在类加载的时候会被初始化
     */
    public static String brand = setAndGetBrand();


    /**
     * 静态代码块在类加载的时候会被加载执行
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
        new CellPhone();
    }
}