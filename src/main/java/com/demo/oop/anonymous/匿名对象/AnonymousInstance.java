package com.demo.oop.anonymous.匿名对象;


import org.testng.annotations.Test;

/**
我们也可以不定义对象的句柄，而直接调用这个对象的方法。这样的对象叫做匿名对象 new Person().shout();
如果对一个对象只需要进行一次方法调用，那么就可以使用匿名对象。
我们经常将匿名对象作为实参传递给一个方法调用。

* */
public class AnonymousInstance {

    void fact(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        System.out.println("factorial is " + result);
    }

    @Test
    public void test() {
        new AnonymousInstance().fact(5);// calling method with anonymous object
    }


    public static void showSinger(Singer singer){
        singer.sing();
    }

    @Test
    public static void test2() {
        showSinger(new PopularSinger());//匿名对象作为实参传递给一个方法
    }

    /**
     * 个人理解：匿名内部类兼具了匿名内部类和匿名对象的特征
     */
    @Test
    public static void test3() {
        showSinger(new Singer() {
            @Override
            public void sing() {
                System.out.println("I am JayChou");
            }
        });//匿名对象作为实参传递给一个方法
    }


/* 只有函数式接口才能使用lambda
   @Test
    public static void test4() {
        showSinger(()->{
                System.out.println("I am JayChou");
        });//匿名对象作为实参传递给一个方法
    }*/


}

abstract class Singer{
    public abstract void sing();
}

class PopularSinger extends Singer{

    @Override
    public void sing() {
        System.out.println("heng heng ha hei");
    }
}
