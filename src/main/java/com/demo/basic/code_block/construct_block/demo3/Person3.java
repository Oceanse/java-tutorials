package com.demo.basic.code_block.construct_block.demo3;

/**
 * Person类中存在若干个包含相同代码逻辑的构造函数，存在很多重复代码，
 * 可以将这部分共同的代码抽取出来，使用构造代码块取代
 *
 * 构造代码块
 * 使用场景：如果多个构造器包含相同的代码，且这些代码无需接受参数，可以把他们抽取出来放在构造代码块中
 *
 * 作用：给对象进行初始化，提高代码的复用性和可维护性
 *
 * 特点：1 代码块是没有独立运行的能力，没法通过类或者对象去调用，它会在在构造器执行之前自动执行(某种程度说，构造代码块是构造器的补充)
 *      2 假如有多个代码块,自上而下顺次执行；因为每个代码块都会被执行，所以建议把多个代码块合并成一个代码块
 *      3 不接受任何参数
 *      4 初始化顺序：属性初始值/代码块(取决于两者代码先后顺序)-->构造器
 * @author epanhai
 */
public class Person3 {

//构造代码块和实例变量直接赋值的执行的顺序取决两者在代码中的先后顺序，谁在前，谁先执行

    {
        System.out.println("代码块1被执行");
        name="haiyang";
    }
    String name="ocean";



    int age=30;
    {
        System.out.println("代码块2被执行");
        age = 31;
    }


    public Person3() {
        System.out.println("Person3() is called");
    }

    public Person3(String name, int age) {
        System.out.println("Person3(String name, int age) is called");
        this.name = name;
        this.age = age;
    }


}


class BlockTest3 {
    public static void main(String[] args) {
        //构造代码块和属性初始值执行的顺序取决两者在代码中的先后顺序，谁在前，谁先执行
        Person3 person = new Person3();
        System.out.println(person.name+": "+person.age);

        //初始化顺序：属性声明值/代码块(取决于两者代码先后顺序)-->构造器
        Person3 person2 = new Person3("panhaiyang", 33);
        System.out.println(person2.name+": "+person2.age);
    }
}