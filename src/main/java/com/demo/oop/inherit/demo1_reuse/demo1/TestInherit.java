package com.demo.oop.inherit.demo1_reuse.demo1;

public class TestInherit {
    public static void main(String[] args) {
        //一定是先调用父类构造器，再调用本类构造器
        Parrot parrot=new Parrot("parrot","grain","triangle","chinese");

        //Parrot类拥有本类和Bird类、Animal类全部的方法
        //注意这里parrot调用的是自己的方法(继承过来的也要想象成是自己的)
        System.out.println(parrot.getSpecies());
        System.out.println(parrot.getFood());
        System.out.println(parrot.getSwingsShape());
        System.out.println(parrot.getLanguage());
        parrot.eat();
        parrot.fly();
        parrot.say();
    }
}
