package com.demo.oop.oopintrouce.constructor;

import org.testng.annotations.Test;

/**
 * 构造方法也叫构造器或者构造函数
 *
 * 语法：
 * 权限修饰符 类名(参数列表) { }
 *
 * 特点：
 * 构造方法与类名相同
 * 构造方法会有一个默认返回值(返回当前类的实例)，不需要显式声明返回值类型，不用return，也不能用void修饰
 * 构造方法不能用static修饰
 *
 * 调用方式：new 类名(参数列表)
 *
 * 作用：
 * 构造函数的主要作用是完成对象的属性初始化工作
 * 空参构造函数根据类型为对象提供默认值，个人理解严格来说是构造器没有对属性值做任何修改，因为属性默认值和声明值/构造代码块是在构造器调用之前完成的
 * 参数化构造函数用于为不同对象提供不同初始化的值
 *
 * Java构造函数重载(overload)： 一个类中可以多个名字相同，参数不同的重载构造方法包括：
 * 无参数构造函数：默认构造函数(无参数构造函数)，如果类中没有构造函数，编译器会自动创建一个默认空参构造函数，如果手动添加了参数化构造器，那么默认空参构造器就会被覆盖消失，所以建议手动加上空参构造器
 * 参数化构造函数
 * this在构造方法中调用当前类中重载的构造方法，只能位于第一行
 * 作用:使得代码简洁：比如五参构造器可以直接调用四参构造器来初始化其中的四个属性，这样可以减少重复代码(软件行业建议不要把相同的代码书写两次以上)，
 *     代码维护方便：如果构造器A调用B,B调用C, 如果修改三个函数共有的某个属性，只需要修改C,方便代码的维护
 *
 * java对象实例变量初始化顺序：
 * 首先为所有的对象创建内存空间(前提是该类已经被加载过)
 * 接着开始对这些实例变量进程初始化：先是属性默认值，然后执行实例代码块或者实例变量声明值，两者的顺序和他们在源代码中的排列顺序相同
 * 最后执行构造器内部的代码，实例化是对象实例化的最后一步
 * 从先后关系的角度分析，可以把属性默认值以及声明值/实例代码块脑补成构造器执行过程的一部分
 * <p>
 * <p>
 * 构造器并不是完全负责创建java对象，实际上当系统构造器的方法体执行之前，系统已经创建了一个对象，为该对象分配内存空间，
 * 并根据实例成员的默认值(或者具体值)以及构造代码块，为这个对象进行了初步初始化；这个阶段这个对象还不能被外部程序访问，
 * 只能在该构造器的方法体中通过this来引用；然后再执行构造器的方法体进一步初始化对象。
 * 当构造器的执行体执行结束后，这个对像作为构造器的返回值被返回，通常还会赋给另一个引用类型的变量，从而让外部程序可以访问该对象。
 * 空参构造器方法体为空，所以不会修改属性值，只是返回创建的对象给外界使用
 * 带参构造器会修改对象的默认属性值/声明值/代码块初始化值，并返回创建的对象给外界使用；
 * 个人这样记忆:把成员变量(无论是否显式初始化)和构造代码块脑补到构造器的代码块中
 * <p>
 * 成员变量初始化顺序：默认值--->初始值/代码块---->构造器---->普通方法
 *
 * 继承中的构造器调用详见继承模块讲解
 *
 * @author epanhai
 */
public class ConstructorTest {

    /**
     * 空参构造函数根据类型为对象提供默认值，个人理解严格来说是构造器没有对属性值做任何修改，因为属性默认值和声明值/构造代码块是在构造器调用之前完成的
     */
    @Test
    public void testEmptyConstructor() {
        Student student = new Student();
        System.out.println(student);
        //java 10支持var, 编译器可以自动类型推断，这样可以避免重复写类型
        // var student = new Student();
    }


    /**
     * 如果类中没有构造函数，编译器会自动创建一个默认空参构造函数，如果手动添加了参数化构造器，
     * 那么默认空参构造器就会被覆盖消失，所以建议手动加上空参构造器
     */
    @Test
    public void testEmptyConstructor2() {
        //new ClassRoom(); ClassRoom空参构造器已被覆盖不存在
    }

    /**
     * 参数化构造函数用于为不同对象提供不同初始值。
     */
    @Test
    public void testParametricConstructor() {
        Student phy = new Student("phy", 31, new String[]{"chinese", "math", "english"});
        Student ocean = new Student("ocean", 30, new String[]{"chinese", "math", "english"});
        System.out.println(phy);
        System.out.println(ocean);
    }

    /**
     * 创建对象时候，堆内存中先为所有实例变量分配好空间，然后先执行声明变量时的初始值或者代码块中的初始值， 再执行构造器的初始值
     * 也就是说构造器的初始值会覆盖声明变量时的初始值
     */
    @Test
    public void testParametricConstructor2() {
        System.out.println(new SoftwareEngineer());
        System.out.println(new SoftwareEngineer("seniorEngineer"));
    }

    /**
     * 调用拷贝构造函数:这里只是浅拷贝
     */
    @Test
    public void testCloneConstructor() {
        String[] subjects=new String[]{"chinese", "math", "english"};
        Student student = new Student("ocean", 18, subjects);
        Student student2 = new Student(student);
        System.out.println("student2 = " + student2);
        student.subject[0]="physics";
        System.out.println("student2 = " + student2);
    }
}