package com.demo.oop.inherit.demo3_Polymorphism.polymorphhismDemo1;

/**
 * 编译时多态：详见overload
 * <p>
 * 多态概念: 一个引用类型变量可以指向多个实际上不同类型的子类对象，在运行时候可以根据实际类型对象的不同而调用对应的方法
 * <p>
 * 运行时多态(动态方法分派): SupperClass obj=new SubClass();  obj.method();
 * obj在编译时期是SupperClass类型， 但是此类型并不是obj的实际类型；obj的实际实现类是在运行时决定的(运行时才会创建对象)；
 * 编译器在编译期间会认为obj是SupperClass类型，因此编译期间无法决定将调用哪个类方法，只有在运行时候可以根据实际类型对象的
 * 不同而调用对应的方法，因此这也叫作运行时多态或动态方法分派。
 * <p>
 * 另一角度:动态绑定和静态绑定
 * 静态绑定(属性)：编译期和运行期，成员变量（包括静态变量和实例变量）只与引用变量所声明的类型的对象绑定，也就是obj在编译阶段已经做了绑定，并且也不会在运行期间发生变化，因此这种绑定属于静态绑定
 * 动态绑定(方法)：编译期，方法与引用变量声明的类型的对象绑定；运行期间，方法与实际引用的对象的进行绑定，这种绑定属于动态绑定，不同时期绑定在不同对象，由 Java 虚拟机动态决定的
 * <p>
 * <p>
 * <p>
 * 多态步骤：
 * 继承(多个子类继承同一个父类)
 * 重写(多个子类重写父类的同一个方法)，
 * 向上转型(父类引用指向子类对象)
 * 父类引用.重写方法
 * <p>
 * 多态使用技巧：面向抽象设计，实现，调用
 * 多态使得程序具有很强的扩展性
 */
public class PolymorphismDemo {

    /**
     * Java的实例方法调用是基于运行时的实际类型的动态调用，而非变量的声明类型。
     *
     * @param args
     */
    public static void main(String[] args) {
        //参数Bank在运行时期分别是SBI，ICICI，AXIS类型，所以导致运行时期调用相同的方法却有着不同的结果
        float interest = getInterest(new SBI());
        float interest1 = getInterest(new ICICI());
        float interest2 = getInterest(new AXIS());
        System.out.println("interest = " + interest);
        System.out.println("interest1 = " + interest1);
        System.out.println("interest2 = " + interest2);
    }

    /**
     * 面向抽象调用
     *
     * @param bank
     * @return
     */
    public static float getInterest(Bank bank) {
        return bank.getRateOfInterest();
    }
}

/**
 * 面向抽象设计
 */
abstract class Bank {
    abstract float getRateOfInterest();
}

/**
 * 面向抽象实现
 */
class SBI extends Bank {
    @Override
    float getRateOfInterest() {
        return 8.4f;
    }
}

/**
 * 面向抽象实现
 */
class ICICI extends Bank {

    @Override
    public float getRateOfInterest() {
        return 7.3f;
    }
}

/**
 * 面向抽象实现
 */
class AXIS extends Bank {

    @Override
    float getRateOfInterest() {
        return 9.7f;
    }
}


