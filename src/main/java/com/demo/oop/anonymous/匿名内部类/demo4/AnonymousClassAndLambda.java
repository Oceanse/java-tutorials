package com.demo.oop.anonymous.匿名内部类.demo4;

/**
 * 匿名内部类在函数型接口的前提下可以用lambda表达式替换，这样代码会更加简洁
 */
public class AnonymousClassAndLambda {

    public void test(){
        /**
         * 匿名内部类形式创建接口实现类
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

        /**
         * lambda表达式形式创建接口实现类
         */
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }



    public void testFinalUnderLambdaAndAnonymousClass(){
        int ticket=1;

        //java1.8之前规定，局部内部类引用了同一级别的局部变量的时候，该局部变量必须是final;
        //java1.8后，局部内部类引用了同一级别的局部变量的时候，该局部变量可以不用显式的声明final,但是其本质仍然是final变量
        //匿名内部类
        Runnable task=new Runnable() {
            @Override
            public void run() {
                //System.out.println(ticket++); //编译报错
            }
        };
        new Thread(task).start();


        //lambda表达式内部引用了同一级别的局部变量的时候，该局部变量可以不用显式的声明final,但是其本质仍然是final变量
        int ticket2=1;
        Runnable task2=()->{
            //System.out.println(ticket2++);//编译报错
        };
        new Thread(task2).start();
    }
    }

