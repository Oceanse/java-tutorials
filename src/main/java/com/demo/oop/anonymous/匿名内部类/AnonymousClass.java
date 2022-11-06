package com.demo.oop.anonymous.匿名内部类;

public class AnonymousClass {

    public void test(){
        int ticket=1;

        //java1.8之前规定，局部内部类引用了同一级别的局部变量的时候，该局部变量必须是final;
        //java1.8后，局部内部类引用了同一级别的局部变量的时候，该局部变量可以不用显式的声明final,但是其本质仍然是final变量
        Runnable task=new Runnable() {
            @Override
            public void run() {
                System.out.println(ticket);
            }
        };
    }



    public void test2(){
        int ticket=1;

        //java1.8之前规定，局部内部类引用了同一级别的局部变量的时候，该局部变量必须是final;
        //java1.8后，局部内部类引用了同一级别的局部变量的时候，该局部变量可以不用显式的声明final,但是其本质仍然是final变量
        Runnable task=new Runnable() {
            @Override
            public void run() {
                //System.out.println(ticket++); //编译报错
            }
        };
    }



    public void test3(){
        //lambda表达式内部引用了同一级别的局部变量的时候，该局部变量可以不用显式的声明final,但是其本质仍然是final变量
        int ticket=1;
        Runnable task=()->{
            System.out.println(ticket);
        };
    }

    public void test4(){
        //lambda表达式内部引用了同一级别的局部变量的时候，该局部变量可以不用显式的声明final,但是其本质仍然是final变量
        int ticket=1;
        Runnable task=()->{
            //System.out.println(ticket++);//编译报错
        };
    }
}
