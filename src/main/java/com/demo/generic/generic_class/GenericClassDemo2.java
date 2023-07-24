package com.demo.generic.generic_class;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Random;

/**
 * 泛型类使用：抽奖机
 */
public class GenericClassDemo2<T> {//此处T是类型形参，也被称为一个类型形参,实例化时候指定具体的类型


    //奖品池，通过使用泛型可以添加任意指定类型的奖品
    //productList也是使用泛型的属性
    ArrayList<T> productList = new ArrayList<>();

    //添加奖品
    void addProduct(T product) {
        productList.add(product);
    }

    //抽奖
    T getProduct() {
        Random random = new Random();
        return productList.get(random.nextInt(productList.size()));
    }

    @Test
    public void testGenericClass() {
        //实例化指定具体的类型实参
        GenericClassDemo2<String> g = new GenericClassDemo2<>();

        //设置奖品池
        String[] productList = {"华为手机", "苹果电脑", "智能音响", "购物券"};
        for (String product : productList) {
            g.addProduct(product);
        }

        //抽奖
        String product = g.getProduct();
        System.out.println("恭喜你抽中了： " + product);
    }


    @Test
    public void testGenericClass2() {
        //实例化指定具体的类型实参
        GenericClassDemo2<Integer> i = new GenericClassDemo2<>();

        //设置奖品池
        Integer[] productList = {1000, 2000, 3000, 4000, 10000};
        for (Integer product : productList) {
            i.addProduct(product);
        }
        //抽奖
        Integer product = i.getProduct();
        System.out.println("恭喜你抽中了： " + product);
    }


}

