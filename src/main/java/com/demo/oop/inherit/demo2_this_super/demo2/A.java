package com.demo.oop.inherit.demo2_this_super.demo2;

/**
 * this和super在构造器当中都要位于首行，因此两者不能共存
 * 若显示调用父类带参构造器，编译器会在子类构造方法的第一句加上super()，但是构造方法中出现了this或者显示调用父类带参构造器，则不会再调用super()
 */
public class A {
    public A() {
        System.out.println("A空参构造");
    }
}

class B extends A{
    int num;

    public B() {
        //这里会隐式的加上super()
        System.out.println("B空参构造");
    }

    public B(int num) {
        System.out.println("B单参构造");
        this.num = num;
    }
}

class C extends B{
    String attr;

    public C() {
        //不会再调用super(),因为super和this不能在构造器中共存
        this("Olay");
        System.out.println("C空参构造");
    }

    public C(String attr) {
        super(100);
        System.out.println("C单参构造");
        this.attr = attr;
    }

    @Override
    public String toString() {
        return "C{" +
                "attr='" + attr + '\'' +
                ", num=" + num +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(new C());
    }
}