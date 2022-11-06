package com.demo.generic.demo6.super_wildcard;

import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;



/**
 * 下界通配符： ? super 类型实参下界， 限制未知类型的下界，要求该传入的类型实参只能是型类型下界或者其父类
 *
 * 泛型通配符可以出现非泛型类和非泛型方法中
 */
public class SuperWildCard {


    void getEle(Set<? super B> set) {
        for (Object o : set) {
            System.out.println(o);
        }
    }


    @Test
    public void test() {
        Set<A> seta = new HashSet<>();
        seta.add(new A());

        Set<B> setb = new HashSet<>();
        setb.add(new B());

        Set<C> setc = new HashSet<>();
        setc.add(new C());

        getEle(seta);
        getEle(setb);
        //getEle2(setc); //void getEle2(Set<? super B> set) 不能接收 Set<C> setc
    }


    /**
     * 下界通配符可以确定泛型类型的最小值，这里最小值就是B, 因此可以添加B类或者B类的子类
     * @param set
     */
    void getEle2(Set<? super B> set) {
        set.add(new B());
        set.add(new C());
    }
}


class A {
}

class B extends A {
}

class C extends B {
}