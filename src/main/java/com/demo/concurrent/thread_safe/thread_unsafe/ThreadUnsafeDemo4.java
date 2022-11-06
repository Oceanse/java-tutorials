package com.demo.concurrent.thread_safe.thread_unsafe;

public class ThreadUnsafeDemo4 implements Runnable {

    /**
     *  三个线程对象会抢占式执行这段线程体
     *   这里synchronized修饰的同步代码块作用域太小，依然存在线程安全问题
     */
    @Override
    public void run() {
        //如果最后还剩一张票ticket=1，三个线程可能执行完while(Ticket.ticket>0)，然后都失去cpu阻塞在这一步，然后依次执行，所以会打印出1,0，-1，两张错票
        while(Ticket.ticket>0){
            synchronized (this) {
                System.out.println(Thread.currentThread().getName()+"票号"+Ticket.ticket);
                Ticket.ticket--;
            }
        }
    }

    public static void main(String[] args) {
        //创建三个线程
        ThreadUnsafeDemo4 t=new ThreadUnsafeDemo4();
        Thread thread=new Thread(t);
        Thread thread2=new Thread(t);
        Thread thread3=new Thread(t);

        //启动三个线程
        thread.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");


        thread.start();
        thread2.start();
        thread3.start();
    }
}
