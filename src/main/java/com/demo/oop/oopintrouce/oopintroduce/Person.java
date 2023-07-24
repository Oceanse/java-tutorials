package com.demo.oop.oopintrouce.oopintroduce;
/**
 * OOP：Object Oriented Programming 面向对象
 * POP: Procedure Oriented Programming 面向过程
 * <p>
 * <p>
 * 面向对象是一种更优秀的程序设计方法，它的基本思想是使用类、对象、继承、封装、消息等基本
 * 概念进行程序设计。它从现实世界中客观存在的事物（即对象）出发来构造软件系统，并在系统构造中
 * 尽可能运用人类的自然思维方式， 强调直接以现实世界中的事物（即对象）为中心来思考， 认识问题，
 * 并根据这些事物的本质特点，把它们抽象地表示为系统中的类，作为系统的基本构成单元(而不是用一
 * 些与现实世界中的事物相关比较远， 并且没有对应关系的过程来构造系统), 这使得软件系统的组件可
 * 以直接映像到客观世界，并保持客观世界中事物及其相互关系的本来面貌；
 * 面向对象的语言使用类来封装一类事物的内部状态数据(属性)，还会为这类事物的行为特征提供相应的实现(方法)
 * 属性（状态数据）＋方法（行为）＝类定义
 * <p>
 * <p>
 * 类：对具有相同状态(属性/成员变量)行为(方法)的一组个体进行抽象后的模板
 * <p>
 * 对象：也称为实例，根据类生成的具体实体，具有明确定义的属性(属性值)和方法(方法实现)，
 * 同一类的不同的对象具有各自独有的一套属性和行为，但是共享相同的类变量和类方法,
 * 通过方法的调用不断读写对象的状态(属性)
 * <p>
 * 类和对象的通俗理解：
 * 类可以理解为汽车设计图，对象就是根据图纸设计出的每辆汽车，汽车设计图中，所有的汽车都有品牌属性和价格属性，但是生产出的每辆具体的车的品牌和价格是不同的
 * 定义class就是定义了一种引用数据类型，对应的instance是这种数据类型的实例；
 * <p>
 * 类成员组成设计：
 * 成员变量(类成员变量和实例成员变量) 方法 构造器 代码块 内部类
 * 面向对象程序设计中类的设计很重要
 * java程序员要有高度抽象能力，观察现实世界中对象(不同的电脑)，抽取出这些对象的共同特征(状态和行为，品牌、主板、CPU、硬盘、内存)，脑海中形成一个模板(class Computer)，然后用代码进行类的设计，
 * 当然还有生产商，销售商也可以抽象成一个个的类，然后通过这些类创建对象，所有的对象协作起来形成一个系统；
 *
 * <p>
 * 类和对象的使用:
 * 1.创建类，设计类的成员
 * 2.创建类的对象
 * 3.通过“对象.属性”或“对象.方法”调用对象的结构。
 * <p>
 * 关于引用：
 * 引用变量只是指向对象或者保存对象的地址，但是本身并不保存对象，类似于c语言中的指针
 * Person people1 = new Person("ocean", false, hobby, site);
 * String name=people1.name;
 * people1.toString();
 * People people2=people1;
 * people2=null;
 * people1是引用变量，指向堆内存中的对象；
 * 通过引用访问属性和方法，实际上是访问引用指向的对象的属性和方法
 * 一个对象可以有多个引用变量或者多个引用变量可以指向同一个对象
 * 如果一个对象没有任何引用变量，将会被GC,释放堆内存
 */

import java.util.Arrays;

/**
 * 类成员设计
 * 属性：姓名 年龄 性别 兴趣 地址
 * 方法：打印对象信息
 * 实例代码块
 * 静态代码块
 * 内部类
 *
 * @author epanhai
 */
public class Person {

    /**
     * 实例变量，对象级别的变量,对象被创建后，实例变量内存空间才会在堆中被创建
     */
    private String name;
    private String sex;
    private int age;
    private String[] hobby;
    private Site site;//类与类之间的组合关系

    /**
     * 类变量
     */
    private static String nation;


    /**
     * 空参构造器
     */
    public Person() {
        System.out.println("Person() is called");
    }


    /**
     * 四参构造器
     *
     * @param name
     * @param sex
     * @param age
     * @param hobby
     * @param site
     */
    public Person(String name, String sex, int age, String[] hobby, Site site) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.hobby = hobby;
        this.site = site;
    }

    /**
     * 方法: 打印对象信息
     *
     * @return
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", hobby=" + Arrays.toString(hobby) +
                ", site=" + site +
                '}';
    }

    /**
     * 实例代码块
     */ {
        System.out.println("我是实例代码块，在构造函数之前被调用");
    }

    static {
        System.out.println("我静态代码块，类加载的时候被调用");
        nation = "China";
    }

    /**
     * 内部类
     */
    class InnerClass {
    }
}


class Site {
    private String country;
    private String province;
    private String county;

    public Site() {
    }

    public Site(String country, String province, String county) {
        this.country = country;
        this.province = province;
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return "Site{" +
                "country='" + country + '\'' +
                ", city='" + province + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}


class TestPerson {
    public static void main(String[] args) {

        Site site = new Site("China", "Shanghai", "Changning");
        String[] hobby = {"Code", "Sing", "movie"};

        //people称之为引用或者引用变量
        //栈内存中的Person变量保存着堆内存当中Person对象的地址, 只有通过引用才能找到对象
        Person people = new Person("ocean", "man", 31, hobby, site);

        //这里的引用指向特定对象，就可以把引用当成特定对象，通过引用取调用对象的toString方法
        System.out.println(people);

        //通过引用修改site对象内容
        site.setProvince("Shandong");
        site.setCounty("Anqiu");
        hobby[1] = "music";
        System.out.println(people);

        //栈引用变量不指向任何堆对象，也就是people对应的对象或者实例不存在，那么相应的属性和方法也不存在，所以会导致NPE
        people = null;
        //people.toString();
    }
}
