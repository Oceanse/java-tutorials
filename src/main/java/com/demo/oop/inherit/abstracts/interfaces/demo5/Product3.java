package com.demo.oop.inherit.abstracts.interfaces.demo5;


/**
 * lambda表达式适合创建函数型接口(只有一个抽象方法的接口)实现类对象，相比于匿名内部类创建对象，语法更加简洁，但是使用条件受限
 */
public interface Product3 {

     double getPrice();
}


class AnonymousTest2 {

    public static void getProductInfo(Product3 product){
        System.out.println("price: "+product.getPrice());
    }



    public static void main(String[] args) {

        getProductInfo(()->{
            return 1000;
        });


    }
}
