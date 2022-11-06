package com.demo.basic.method.equals_tostring.equals;

import com.demo.oop.inherit.demo2_this_super.Person;
import org.testng.annotations.Test;


/**
 * 1 Object中的原生equals方法比较的是两个对象的内存地址是否相同
 *
 * 2 Object中的equals()源码：
 *   public boolean equals(Object obj) {
 *      return (this == obj);
 *   }
 *  默认比较的是两个引用对象的地址；
 *  如果equals方法传的参数是基本数据类型，会自动装箱
 *
 * 3 String以及Integer等基本类型的包装类型已经对原生equals方法进行了重写，比较的是两个对象的内容
 *
 * 4 现实业务中自定义的一些类有时候需要比较的是两个对象的内容而不是两个对象的地址，所以需要对原生equals方法进行了重写
 */
public class EqualsDemo {

    String content;
    int num;


    public EqualsDemo() {
    }

    public EqualsDemo(String content, int num) {
        this.content = content;
        this.num = num;
    }


    /**
     * Object中的equals默认比较的是两个变量保存的变量值，如果是两个变量是引用类型变量则比较的是两个变量保存的地址
     */
    @Test
    public void test() {
        Object o1 = new Object();
        Object o2 = new Object();
        System.out.println(o1.equals(o2));//Object中的equals的equals比较的是两个对象的内存地址是否相同
    }

    /**
     * String以及Integer等基本类型的包装类型已经对原生equals方法进行了重写，比较的是两个对象的内容
     */
    @Test
    public void test2() {
        System.out.println(new Integer(1).equals(new Integer(1)));
        System.out.println(new Character('c').equals(new Character('c')));
        System.out.println(new Boolean(true).equals(new Boolean(true)));
        System.out.println(new String("qaz").equals(new String("qaz")));
    }

    /**
     * String以及Integer等基本类型的包装类型已经对原生equals方法进行了重写，比较的是两个对象的内容
     * equals(Object obj)，所以碰到基本类型要自动装箱
     */
    @Test
    public void test2_2() {
        System.out.println(new Integer(1).equals(1));
        System.out.println(new Character('c').equals('c'));
        System.out.println(new Boolean(true).equals(true));
        System.out.println("qaz".equals(new String("qaz")));

        //==是运算符，  包装类和基本类型进行==关系运算时会自动拆箱
        System.out.println(new Integer(1)==1);
        System.out.println(new Character('c')=='c');
        System.out.println(new Boolean(true)==true);
    }

    /**
     * Persons没有重写Object中的equals方法，所以比较的是两个对象的内存地址是否相同
     */
    @Test
    public void test3() {
        Person p = new Person();
        Person p2 = new Person();
        System.out.println(p.equals(p2));
    }


    /**
     *  重写Object的equals方法，比较的两个对象的内容是否相同
     */
    @Override
    public boolean equals(Object obj) {//父类引用指向子类对象
        if (this == obj) {
            return true;
        }

        //父类引用指向子类对象，所以调用子类或者运行时类对象独有方法或者属性时候需要进行强转
        if (obj instanceof EqualsDemo) {
            EqualsDemo e = (EqualsDemo) obj;
            if (this.num == e.num && this.content.equals(e.content)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test4() {

        EqualsDemo e = new EqualsDemo("aa", 123);
        EqualsDemo e2 = new EqualsDemo("aa", 123);

        //本类重写Object中的equals方法，重写后的方法比较的是两个对象的内容是否相同
        System.out.println(e.equals(e2));
    }


}
