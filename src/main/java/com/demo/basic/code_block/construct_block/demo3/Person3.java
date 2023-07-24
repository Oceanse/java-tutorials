package com.demo.basic.code_block.construct_block.demo3;

/**
 * 构造代码块和实例变量声明值的顺序取决两者在代码中的先后顺序，谁在前，谁先执行
 * 两者都不会随着类加载而主动执行，都是随着创建对象而被动执行
 */
public class Person3 {

    {
        System.out.println("实例代码块1被执行");
        name="haiyang";
    }

    String name=setAndGetName();

    int age=setAndGetAge();

    {
        System.out.println("实例代码块2被执行");
        age = 32;
    }


    public Person3() {
        System.out.println("Person3() is called");
    }

    public Person3(String name, int age) {
        System.out.println("Person3(String name, int age) is called");
        this.name = name;
        this.age = age;
    }


    public  String setAndGetName() {
        String name = "Ocean";
        System.out.println("setAndGetName() is invoked");
        return name;
    }


    public int setAndGetAge() {
        int age = 31;
        System.out.println("setAndGetAge() is invoked");
        return age;
    }

}


class BlockTest3 {
    public static void main(String[] args) {
        //构造代码块和属性声明值执行的顺序取决两者在代码中的先后顺序，谁在前，谁先执行
        Person3 person = new Person3();
        System.out.println(person.name+": "+person.age);

        //初始化顺序：属性声明值/代码块(取决于两者代码先后顺序)-->构造器
        Person3 person2 = new Person3("panhaiyang", 33);
        System.out.println(person2.name+": "+person2.age);
    }
}