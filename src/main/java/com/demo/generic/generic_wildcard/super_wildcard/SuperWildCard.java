package com.demo.generic.generic_wildcard.super_wildcard;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 下界通配符：? super 类型实参下界， 限制未知类型的下界，要求该传入的类型实参只能是型类型下界或者其父类
 * Set<? super Number>可以看成Set<Number>，Set<Object>的父类, super通配符想象成：(下界，+无穷大)，右区间 G(下界，+无穷大) 是G<? super 下界>的子类
 * Number是Object的子类，Set<? super Number>是Set<Object>的父类，这称之为逆变
 * Set<? super Number>,这里?是一种类型实参，所以<? super Number>可以看成带区间性质的类型实参
 *
 */
public class SuperWildCard {

    /**
     * 只能接收Set<B及其B的父类>参数
     * @param list
     */
    public void testSuperWildcard(List<? super B> list){
        ArrayList<A> listA=new ArrayList<>();
        ArrayList<B> listB=new ArrayList<>();
        ArrayList<C> listC=new ArrayList<>();
        list=listA;
        list=listB;
        //list=listC;
    }



    /**
     * 只能接收Set<B及其B的父类>参数
     * 访问下界泛型时，只能用Object类型接收
     * @param set
     */
    void getEle(Set<? super B> set) {
        for (Object o : set) {
            System.out.println(o);
        }
    }



    /**
     * 只能传递Set<B及其B的父类>参数
     */
    @Test
    public void testGetEle() {
        Set<A> setA = new HashSet<>();
        setA.add(new A());

        Set<B> setB = new HashSet<>();
        setB.add(new B());

        Set<C> setC = new HashSet<>();
        setC.add(new C());

        getEle(setA);
        getEle(setB);
        //getEle2(setc); //void getEle2(Set<? super B> set) 不能接收 Set<C> setC
    }


    /**
     * 下界通配符允许添加元素
     * 下界通配符可以确定泛型类型的最小值，这里最小值就是B, 因此可以添加元素类型为B类或者B类的子类的Set
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