package com.demo.basic.code_block.construct_block.demo2;

/**
 * 构造代码被调用时候，所有的构造代码块都会顺序执行一次
 */
public class Person2 {
    String name;
    int age;

    {
        System.out.println("I am chinese");
    }

    {
        System.out.println("I am male");
    }

    public Person2() {
        System.out.println("Person2() is called");
    }

    public Person2(String name) {
        System.out.println("Person2(String name) is called");
        this.name = name;
    }

    public Person2(int age) {
        System.out.println("Person2(int age) is called");
        this.age = age;
    }

    public Person2(String name, int age) {
        System.out.println("Person2(String name, int age) is called");
        this.name = name;
        this.age = age;
    }
}


class BlockTest2 {
    public static void main(String[] args) {
        new Person2();
        System.out.println();

        new Person2("ocean");
        System.out.println();
        
        new Person2("ocean",30);

    }
}