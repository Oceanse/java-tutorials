package com.demo.oop.inherit.abstracts.interfaces.demo7;

/**
 * JDK8 开始，支持在接口 Interface 中定义 default 方法。default 方法只能出现在接口 Interface 中。
 *  接口中被 default 修饰的方法被称为默认方法，实现此接口的类如果没 Override 此方法，则直接继承这个方法，不再强制必须实现此方法。
 */
public class DefaultMethodDemo {
    interface MyInterface {
        default void print() {
            System.out.println("Hello World");
        }
    }


    static class MyClass implements MyInterface {}

    public static void main(String[] args) {
        MyInterface obj = new MyClass();
        obj.print();
    }
}