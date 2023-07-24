package com.demo.oop.inherit.demo2_this_super.demo3;

import org.testng.annotations.Test;

/**
 * 当父子类存在同名属性，想要在子类中访问父类属性时候可以通过super访问;
 * 当子类重写父类方法，想要在子类中访问父类被重写的方法时候可以通过super访问;
 */
class A {
    int id=1;

    public void showClassName(){
        System.out.println("A");
    }
}

public class B extends A{
    int id=2;

    @Override
    public void showClassName(){
        System.out.println("B");
    }

    @Test
    public void testSuper(){
        System.out.println(this.id);
        System.out.println(super.id);
        this.showClassName();
        super.showClassName();
    }
}
