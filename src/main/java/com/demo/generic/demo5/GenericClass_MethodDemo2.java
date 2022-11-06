package com.demo.generic.demo5;


import com.demo.collection_map.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author epanhai
 */
public class GenericClass_MethodDemo2 {

    public static void main(String[] args) {

        //匿名内部类使用泛型
        Foo<String> foo = new Foo<String>() {
            @Override
            public void test(String s) {
                System.out.println(s);
            }
        };


        //匿名内部类使用泛型
        Foo<Integer> foo2 = (Integer no) -> {
            System.out.println(no);
        };


        //匿名内部类使用泛型通配符，此时相当于通配符的上限为Object
        Foo<?> foo3 = new Foo<List>() {
            @Override
            public void test(List s) {
                System.out.println(s);
            }
        };


        //匿名内部类使用泛型通配符，此时相当于通配符的上限为Object
        Foo<?> foo4 = (Product p) -> {
            System.out.println(p);
        };

        //匿名内部类使用泛型通配符，通配符的上限为Number
        Foo<? extends Number> foo5 = new Foo<Double>() {
            @Override
            public void test(Double d) {
                System.out.println(d);
            }
        };


        //匿名内部类使用泛型通配符，此时相当于通配符的上限为Object
        Foo<? extends Map> foo6 = (HashMap p) -> {
            System.out.println(p);
        };
    }
}


interface Foo<T> {
    void test(T t);
}


