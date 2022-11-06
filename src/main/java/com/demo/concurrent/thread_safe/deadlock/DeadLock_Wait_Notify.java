package com.demo.concurrent.thread_safe.deadlock;

/*
* 运行的线程执行wait()方法，该线程会释放线程占有的同步锁，JVM会把该线程放入“等待池”中,该线程进入阻塞状态。
* 进入这个状态后，该线程是不能自动唤醒的，必须依靠其他线程调用notify()或notifyAll()方法才能被唤醒，
*
* wait()使当前线程阻塞，前提是 必须先获得锁，一般配合synchronized 关键字使用，
* 即，一般在synchronized 同步代码块里使用 wait()、notify/notifyAll() 方法。
*
* 负责唤醒等待线程的那个线程(我们称为“唤醒线程”)，它只有在获取“该对象的同步锁”(这里的同步锁必须和等待线程的同步锁是同一个)，
* 并且调用notify()或notifyAll()方法之后，才能唤醒等待线程。虽然，等待线程被唤醒；但是，它不能立刻执行，因为唤醒线程还持
* 有“该对象的同步锁”。必须等到唤醒线程释放了“对象的同步锁”之后，等待线程才能获取到“对象的同步锁”进而继续运行。
* */

public class DeadLock_Wait_Notify {

    /**
     * 整个程序中只有A锁和B锁， 两个线程共用相同的A锁和B锁
     * @param args
     */
    public static void main(String[] args) {
        Runnable r1=()->{
            synchronized("A"){
                System.out.println("线程1持有A锁等待B锁");
                try {
                    "A".wait();//该线程释放"A"锁，进入阻塞状态;持有锁，才能调用wait方法释放同步锁并进入阻塞状态；由于没有其他线程唤醒，当前线程就会一直处于阻塞状态
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized ("B"){
                    System.out.println("线程1持有A锁和B锁");
                }
            }
        };

        Runnable r2=()->{
            synchronized("B"){
                System.out.println("线程2持有B锁等待A锁");
                synchronized ("A"){
                    System.out.println("线程2持有A锁和B锁");
                    //当前线程唤醒任意处于阻塞状态的线程，使其有主动握锁的能力；注意这里的锁和wait时释放的锁必须一致
                    "A".notify();
                }
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();




    }
}
