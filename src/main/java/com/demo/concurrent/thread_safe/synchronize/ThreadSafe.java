package com.demo.concurrent.thread_safe.synchronize;


/**
 * 同步编程模型：t1线程和t2线程执行，只有t1线程执行结束后t2才能执行
 * 异步编程模型：t1线程执行t1的，t2线程执行t2的，谁也不等谁
 * <p>
 * 线程同步的使用条件：多个线程共同修改同一共享数据时(不涉及到修改时不存在线程安全问题)
 * 当多个线程在操作同一个共享数据时，一个线程对多条语句只执行了一部分，
 * 还没有执行完，另一个线程参与进来执行,导致共享数据的错误
 * 由此我们想到一个解决的思路：将操作共享数据的代码行作为一个整体，
 * 同一时间只允许一个线程执行，执行过程中其他线程不能参与执行
 * <p>
 * synchronized (对象锁){//锁可以是任意Java对象，但是要唯一的
 * //同步代码块
 * }
 * 由对象充当同步监视器，也称之为锁,锁对于所有线程必须要保证唯一,也就是所有线程对象共享一把锁
 * 同一时刻，只能一个线程对象拥有锁，然后将synchronized的代码块锁住，拥有锁的线程才能去执行
 * 拥有锁的线程在执行同步代码块期间，其它线程都不能执行这段代码
 * 拥有锁的线程执行完代码就会释放锁；其他的线程要等待这个线程将锁释放后才可以获取到这个锁想
 * 然后下一个握住锁的线程才可以去执行同步代码块
 * <p>
 * 对多条操作共享数据的语句，只能让一个线程都执行完，在执行过程中，其他线程不可以参与执行
 *
 * @author epanhai
 */
public class ThreadSafe implements Runnable {


    /**
     * 多个线程的共享变量
     */
    int ticket = 100;

    /**
     * 由对象充当同步监视器，也称之为锁,锁对于所有线程必须要保证唯一
     * 多个线程对象共用一个Runnable接口实现类,所以object是唯一的
     */
    Object lock = new Object();


    @Override
    public void run() {
        sellTicket3();
    }


    public void sellTicket() {
        while (true) {
            //对下面代码块加锁，线程体执行到这就会握住这把锁，锁定同步代码块，直达执行完毕才会释放锁，然后下个线程执行到这握住锁。。。。。
            //this是指Thread_safe对象，这里多个线程共享同一个Thread_safe对象，所以this对象可以保证锁的唯一性，因此这里可以用this
            //若是采用继承继承Thread方式创建多线程，不能用this
            //由对象充当同步监视器，也称之为锁，哪个线程获取锁，哪个线程就执行大括号里被同步的代码块
            synchronized (this) {
                if (ticket <= 0) {
                    return;
                }
            synchronized (lock) {
                if (ticket > 0) {
                    try {
                        //当前线程休眠（释放cpu）20 ms, 其它线程就会执行此段代码，若没有采取同步等安全措施，容易导致线程安全问题(大概率出现负数票)
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //这一句和下面ticket--执行时候存在时间差，若没有采取同步等安全措施，可能会出现重票
                    System.out.println(Thread.currentThread().getName() + "票号" + ticket);
                    ticket--;
                }
            }
        }
    }}


    public void sellTicket2() {
        while (ticket > 0) {

            synchronized (lock) {
                if (ticket <= 0) {
                    return;
                }
                try {
                    //当前线程休眠（释放cpu）20 ms, 其它线程就会执行此段代码，若没有采取同步等安全措施，容易导致线程安全问题(大概率出现负数票)
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //这一句和下面ticket--执行时候存在时间差，若没有采取同步等安全措施，可能会出现重票
                System.out.println(Thread.currentThread().getName() + "票号" + ticket);
                ticket--;
            }
        }
    }

    /**
     * 同步方法, 锁是当前对象且是唯一的，所有线程共享同一把锁
     * 方法声明时使用,放在范围操作符(public等)之后,返回类型声明(void等)之前.这时线程获得的是成员锁,
     * 即一次只能有一个线程进入该方法,其他线程要想在此时调用该方法,只能排队等候,当前线程执行完该方法后,别的线程才能进入.
     * 同步方法
     */
    public synchronized void sellTicket3() {
        while (ticket>0) {
            try {
                //当前线程休眠（释放cpu）20 ms, 其它线程就会执行此段代码，若没有采取同步等安全措施，容易导致线程安全问题(大概率出现负数票)
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //这一句和下面ticket--执行时候存在时间差，若没有采取同步等安全措施，可能会出现重票
            System.out.println(Thread.currentThread().getName() + "票号" + ticket);
            ticket--;
        }
    }


    public static void main(String[] args) {

        //创建三个线程
        ThreadSafe window = new ThreadSafe();
        Thread thread = new Thread(window);
        Thread thread2 = new Thread(window);
        Thread thread3 = new Thread(window);

        //启动三个线程
        thread.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");


        thread.start();
        thread2.start();
        thread3.start();
    }
}
