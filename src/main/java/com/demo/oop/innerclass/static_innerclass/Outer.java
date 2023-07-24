package com.demo.oop.innerclass.static_innerclass;

/**
 * 外部类被加载，其静态内部类不会被同时加载
 * 一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。
 */
public class Outer {
    static {
        System.out.println("load outer class…");
    }

    //静态内部类
    static class StaticInner {
        static {
            System.out.println("load static inner class…");
        }

        static void staticInnerMethod() {
            System.out.println("static inner method…");
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer(); //此刻其内部类不会被加载
        System.out.println("===========分割线===========");
        Outer.StaticInner.staticInnerMethod(); //外部类之前已经被加载,且只会被加载一次，调用内部类的静态方法，内部类被加载
    }
}