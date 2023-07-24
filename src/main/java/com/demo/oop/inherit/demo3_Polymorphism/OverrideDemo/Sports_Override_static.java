package com.demo.oop.inherit.demo3_Polymorphism.OverrideDemo;

/**
 * 重写的目的在于根据创造对象的所属类型不同而表现出多态。
 * 因为静态方法无需创建对象即可使用。没有对象，重写所需要的“对象所属类型” 这一要素不存在，因此无法被重写。
 * 方法覆盖只是针对实例方法，而非静态方法
 * 静态方法可以被继承，但是不能被重写覆盖
 * 静态方法是编译时绑定的，方法重写是运行时绑定的。
 */
public class Sports_Override_static {
    public static void info() {
        //静态方法不能被重写
        System.out.println("Sports make body strong");
    }
}


/**
 *  继承父类的static info()方法，相当于该类拥有static方法info()
 * @return
 */
class Football extends Sports_Override_static {

    //@Override //这里不能添加@Override
    public static void info() {
        //静态方法不能被重写
        System.out.println("Football is the most popular sports");
    }

    public static void main(String[] args) {
        Sports_Override_static.info();//通过父类名调用父类静态方法info()
        Football.info();//通过子类名调用调用父类静态方法info()
        info();//继承父类的static info()方法，相当于该类拥有static方法info()，所以省略类名时候默认会从当前类加载这个方法,等价于Football.info()
        new Football().info();//通过子类对象调用父类静态方法,本质还是通过对象所属的类进行调用

        Sports_Override_static sports=new Football();
        //这里虽然是通过对象引用调用静态方法，但实际上运行的时候还是通过类名调用Sports.info(),静态方法不存在重写和运行时多态，静态方法是编译时绑定的且静态绑定
        sports.info();
    }
}
