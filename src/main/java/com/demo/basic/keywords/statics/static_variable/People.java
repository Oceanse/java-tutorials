package com.demo.basic.keywords.statics.static_variable;


import org.testng.annotations.Test;


/**
 * static关键字主要用于内存管理。我们可以应用java static关键字在变量，方法，代码块和内部类中； static关键字属于类，而不是类的实例
 *
 * 静态变量
 * 定义：static关键字修饰的变量，叫类变量或者静态变量,是类级别或者模板级别的变量，被所有对象所共享(和具体的对象无关)
 *      注意：类变量只能定义在类内方法外, 即使是类方法内部也不能定义静态变量,因为静态变量在类加载的时候就会被创建初始化，静态方法只有在被调用的时候才会被执行，
 *           也就是如果静态方法内部定义了静态变量，只有静态方法被调用的时候才能初始化其内部定义的静态变量，这就违背了静态变量在类加载的时候就会被创建初始化
 *
 * 存储位置：方法区中(具体说是方法区中的静态区）,被类的所有实例共享,可以节省内存，使程序存储器高效
 * 方法区补充：方法区与 Java 堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。
 * 虽然 Java 虚拟机规范把方法区描述为堆的一个逻辑部分，但是它却有一个别名叫做 Non-Heap（非堆），目的应该是与 Java 堆区分开来。

 * 初始化：
 * JVM会在加载字节码文件的时候静态变量进行空间分配以及初始化(参考饥饿单例模式)，当系统创建该类的对象时，系统不会再为类变量分配内存， 也不会
 * 再次对类变量进行初始化(和静态代码块相似)
 *
 * 生存时间：静态变量的生命周期与类的生命周期相同，随着类的字节码文件加载而创建初始化，随着字节码文件的销毁而销毁(取决于类的生命周期)，生存时间比对象还要长；
 *
 * 使用：类名.类变量或者对象.类变量访问，但是建议类名.类变量, 因为类变量(包括类方法)和对象是无关的
 *      另外在当前类调用时候可以省略类名或者对象名
 *
 *
 * 用途：比如常用一个布尔型静态成员变量做控制符；计数器(统计对象数量)； 若所有对象共享相同的属性(员工公司名称，学生所在的大学名称)，建议定义为静态属性
 *
 * 注意：
 * 1 对static关键字而言，有一条非常重要的规则：类成员（包括成员变璧、方法、初始化块、内部类和内部枚举）
 * 不能访问实例成员（包括成员变量、方法、初始化块、内部类和内部枚举）如果确实需要在静态方法中访问另一个
 * 普通方法，则只能重新创建一个对象(静态方法内部可以调用构造方法)，然后通过对象去访问普通方法
 *
 * 2我们知道，序列化的目的就是为了 把 Java 对象转换为字节序列。对象转换为有序字节流，以便其能够在网络上传输或者保存在本地文件中。
 * 声明为 static 和 transient 类型的变量不能被序列化，因为 static 修饰的变量保存在方法区中，只有堆内存才会被序列化。
 * 而 transient 关键字的作用就是防止对象进行序列化操作。
 * @author epanhai
 */
public class People {

    /**
     * 实例变量和对象绑定， 不同的对象拥有自己单独的一套实例变量
     */
    private String name;
    private int age;


    /**
     * 所有对象共享这个静态变量，或者说任何一个对象修改这个静态变量，产生的结果对所有的对象是等价的
     */
    private static String nation = "China";
    private static int count;


    public People() {
        System.out.println("People() is invoked");
        //等价于this.count++; this用在构造方法时候表示正在创建的对象
        count++;
    }

    /**
     * static variable和具体对象无关，所以一般不会出现构造方法中；
     * 但是也有例外情况，比如统计创建的对象数量
     * @param name
     * @param age
     */
    public People(String name, int age) {
        System.out.println("People(String name, int age) is invoked");
        this.name = name;
        this.age = age;
        //等价于this.count++; this用在构造方法时候表示正在创建的对象
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static String getNation() {
        return nation;
    }

    public static void setNation(String nation) {
        People.nation = nation;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        People.count = count;
    }

    public void  display(){
        System.out.println(name + " is " + age + " and comes from " + nation);
    }



    @Test
    public void test() {
        People ocean = new People("phy", 31);
        People mxz = new People("mxz", 55);

        //s和s2共享类变量nation
        ocean.display();
        mxz.display();

        //通过对象(引用)访问静态变量nation
        //对象访问,这里本质还是用对象所属的类名进行访问：People.nation
        System.out.println(ocean.nation);
        //对象访问,这里本质还是用对象所属的类名进行访问：People.nation
        System.out.println(mxz.nation);
        if ((ocean.getNation() == mxz.getNation())) {
            System.out.println("s.getNation()==s2.getNation()");
        } else {
            System.out.println("s.getNation()!=s2.getNation()");
        }

        //这里本质还是用对象所属的类名进行访问：People.nation，所以依然可以正常访问到nation,不会出现空指针异常，也说明了静态变量不需要依赖对象的存在
        mxz = null;
        System.out.println(mxz.nation);

        //类名访问
        System.out.println(People.nation);

        //当前类中可以省略类名访问静态变量
        //在a类中可以通过静态导来实现省略类名使用b类的静态属性或者静态方法
        System.out.println(nation);

        //任何一个对象修改这个静态变量，产生的结果对所有的对象是等价的
        ocean.nation = "USA";
        System.out.println(ocean.nation);
    }


    /**
     * test2方法执行之前会首先通过空参构造创建People对象，然后在开始执行test2方法,所以一共会创建三个对象
     */
    @Test
    public void test2() {
        People s = new People("ocean", 100);
        People s2 = new People("ocean", 120);
        System.out.println("The count of the instance of People is " + People.getCount());
    }


}
