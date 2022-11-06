package com.demo.concurrent.Sleep_Join_Yield;

public class ThreadSleep3 extends Thread {

    @Override
    public void run() {

        try {
            //Thread中的run方法没有抛异常，所以这里不能throws异常
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("sleep is interrupted");
            e.printStackTrace();
        }
        for (int i = 0; i <100 ; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        //子线程执行线程体时因为sleep会阻塞1s，阻塞的过程中OS会切换到主线程，并且这个过程中只有一个主线程在执行；
        //1s之后子线程重新进入就绪状态，如果主线程已经执行完毕，那么会只有一个子线程在执行；
        //如果1s后主线程还在执行，那么会主线程和子线程会交替执行；
        ThreadSleep3 threadDemo=new ThreadSleep3();
        threadDemo.setName("子线程");
        threadDemo.start();

        //主线程代码
        Thread.currentThread().setName("主线程");
        for (int i = 0; i <100 ; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }


    }
}
