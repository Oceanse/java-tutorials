package com.demo.oop.oopintrouce.constructor;

import java.util.Arrays;

/**
 * @author epanhai
 */
public class Student {

    /**
     * 实例变量只有在对象创建时候才会被声明初始化，Student如果只是装载到内存而没有创建对象，实例属性name等是没有初始值或者默认值
     */
    String name;
    int id;
    String[] subject;


    /**
     * 空参构造方法：空参构造函数根据类型为对象提供默认值，个人理解严格来说是构造器没有对属性值做任何修改，因为属性默认值和声明值/构造代码块是在构造器调用之前完成的
     * 尽量手动添加无参构造方法，避免在创建子类对象或者其他场景遇到空参构造不存在的问题
     */
    public Student() {
        System.out.println("Student 无参构造器");
    }

    /**
     * 双参构造方法：参数化构造函数用于为不同对象提供不同初始化的值
     */
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        System.out.println("Student 双参构造器");
    }

    /**
     * 三参构造方法：参数化构造函数用于为不同对象提供不同初始化的值
     * this在构造方法中调用当前类中重载的构造方法，只能位于第一行
     */
    public Student(String name, int id, String[] subject) {
        this(name, id);
        this.subject = subject;
        System.out.println("Student 三参构造器");
    }


    /**
     * 拷贝构造函数,可以将一个对象的值复制到另一个对象中
     */
    public Student(Student student) {
        this.name = student.name;
        this.id = student.id;
        this.subject = student.subject;//浅拷贝
        //this.subject = student.subject.clone();//深拷贝
        System.out.println("Student 拷贝构造函数");
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", subject=" + Arrays.toString(subject) +
                '}';
    }

    /**
     * 如果不小心给构造函数前面添加了返回值类型或者void，那么这将使这个构造函数变成一个普通的方法，在运行时将产生找不到构造方法的错误。
     * 这里就变成了普通方法
     */
    public void Constructor() {
    }

}



