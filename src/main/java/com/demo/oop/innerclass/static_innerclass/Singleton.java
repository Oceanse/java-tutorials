package com.demo.oop.innerclass.static_innerclass;

/**
 * 内部类实现单例模式
 * 优点：JVM保证了线程安全，利用静态内部类特点实现惰性初始化（Lazy-Initialazation）
 */
public class Singleton {

    /**
     * 构造器私有化，外部不能 new
     */
    private Singleton(){
        System.out.println("Singleton() is called");
    }

    /**
     * 外部类被加载，其静态内部类不会被同时加载，在装载 Singleton 时，不会同时装载 SingletonInstance
     * 一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。
     */
    private static class SingletonInstance{
        //类的静态属性只会在第-次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的。
       private static final Singleton INSTANCE =new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;//外部类内部可以访问内部类私有属性
    }
}

class SingletonTest{
    public static void main(String[] args) {
        Singleton singleton=Singleton.getInstance();
        Singleton singleton2=Singleton.getInstance();
        System.out.println(singleton==singleton2);
    }
}
