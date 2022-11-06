package com.demo.basic.keywords.finals.unmutable_instance.demo1;

/**
 * 定义：
 * 不可变类（Immutable Objects）：当类的实例一经创建，其内容便不可改变，即无法修改其成员变量。
 *
 * 优点：
 *    1 效率，例如字符串常量池，字符串常量池可以将一些字符常量放在常量池中重复使用，避免每次都重新创建相同的对象、节省存储空间
 *    2 安全性，不可变对象天生是线程安全的，在不同线程共享对象，不需要同步机制，因为对象的值是固定的。
 *
 * 缺点:
 *     资源开销，对象需要频繁的修改属性，则每一次修改都会新创建一个对象，产生大量的资源开销。
 *
 * Java 中八个基本类型的包装类和 String 类都属于不可变类
 *
 * 设计一个不可变类：
 *
 * 类的成员变量都应该是private final的，保证成员变量不可改变。
 * 提供带参构造器，根据传入的参数初始化成员变量
 * 仅为该类的属性提供getter方法，不提供setter等修改成员变量的方法，因为普通方法也无法修改成员变量的值
 *
 */
public class Stu {
    private final int id;

    public Stu(int id) {//构造方法中初始化实例属性，这里是第一次赋值，之后该变量的值不能再改变
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public static void main(String[] args) {
        //实例对象一经创建，其内容便不可改变，即无法修改该对象的成员变量(堆内存中的属性值不能变)。
        Stu u=new Stu(15721166);

        //实例对象一经创建，其内容便不可改变，即无法修改该对象的成员变量(堆内存中的属性值不能变)。
        Double d=new Double(1.0);
    }
}
