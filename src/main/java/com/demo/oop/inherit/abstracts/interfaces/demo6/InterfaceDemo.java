package com.demo.oop.inherit.abstracts.interfaces.demo6;

import java.util.Arrays;

interface A {
    /**
     * 静态常量
     */
    public static final int A = 1;

    /**
     * 公共抽象方法
     */
    public abstract void infoA();

    /**
     * java8开始接口允许定义具象方法，但是必须用default修饰
     * @param letters
     */
    default void printLetters(char[] letters) {
        System.out.println("char[] letters of interface A....");
        System.out.println(Arrays.toString(letters));
    }
}


interface B {
    /**
     * 静态常量
     */
    public static final int B = 2;

    /**
     * 公共抽象方法
     */
    public abstract void infoB();

    /**
     * 在java8 中的接口中不仅增加了默认方法，还增加了静态方法。使用方式接口名.方法名。
     */
    public static void show() {
        System.out.println("show time....");
    }
}


/**
 * 接口之间可以多继承
 */
interface C extends A, B {
}


/**
 * 抽象类
 */
abstract class D {
    public void printLetters(char[] letters) {
        System.out.println("char[] letters of class D....");
        System.out.println(Arrays.toString(letters));
    }

    public abstract void info();
}


/**
 * 多实现，implements部分必须放在extends部分之后。
 * <p>
 * 一个类实现了一个或多个接口之后，这个类必须完全实现这些接口里所定义的全部抽象方法（也就
 * 是重写这些抽象方法）；否则，该类将保留从父接口那里继承到的抽象方法，该类也必须定义成抽象类。
 * @author epanhai
 */

public class InterfaceDemo extends D implements C {

    @Override
    public void infoA() {
    }

    @Override
    public void infoB() {
    }

    @Override
    public void info() {
    }


}

class Test {

    public static void main(String[] args) {
        //类优先的原则: 若一个接口中定义了一个默认方法，而另一个父类中又定义了一个同名方法时，选择父类中的方法
        new InterfaceDemo().printLetters("abc".toCharArray());

        //在 java8 中的接口中不仅增加了默认方法，还增加了静态方法。使用方式接口名.方法名。
        //注意：不能通过子类类名调用,个人感觉接口中定义的方法没有被继承过来
        B.show();

        //继承拥有父类的静态变量
        System.out.println(InterfaceDemo.A);
        System.out.println(InterfaceDemo.B);


    }
}