package com.demo.concurrent.thread_safe.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyService {

    private Lock lock = new ReentrantLock();
    int ticket=100;

    /**
     * 通过ReentrantLock实现互斥访问；
     * 只有一个线程执行完后，下一个线程才能执行
     */
    public void test() {
        //获取锁
        lock.lock();
        while (ticket>0){
                System.out.println(Thread.currentThread().getName() + ": " + ticket--);
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
        //释放锁
        lock.unlock();
    }
}}

class MyThread implements Runnable {
    private MyService myService;

    public MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.test();
    }
}

public class LockTest {
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread myThread = new MyThread(myService);
        new Thread(myThread).start();
        new Thread(myThread).start();
        new Thread(myThread).start();

    }
}




