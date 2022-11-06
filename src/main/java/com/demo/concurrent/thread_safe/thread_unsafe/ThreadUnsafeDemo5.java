package com.demo.concurrent.thread_safe.thread_unsafe;

/**
 * 懒汉式单例模式：当有多个线程时候可能存在线程安全问题，导致产生多个对象
 * */
public class ThreadUnsafeDemo5 {

    private static ThreadUnsafeDemo5 t = null;

    private ThreadUnsafeDemo5() {
        System.out.println("新建一个Thread_Safe_Cause6对象");
    }


    /**
     * 可能会有多个进程进入到if里面，然后阻塞住
     * @return
     */
    public static ThreadUnsafeDemo5 getInstance() {
        if (t == null) {
            t = new ThreadUnsafeDemo5();
        }
        return t;
    }

    /**
     * 线程安全做法
     * @return
     */
    public static ThreadUnsafeDemo5 getInstanceSafe() {
        synchronized ("") {//线程安全
            if (t == null) {
                t = new ThreadUnsafeDemo5();
            }
        }
        return t;
    }

}


class Test {
    public static void main(String[] args) {
        Runnable r = () -> {
            ThreadUnsafeDemo5.getInstance();
        };
        Runnable r2 = () -> {
            ThreadUnsafeDemo5.getInstanceSafe();
        };

        for (int i = 0; i < 10; i++) {
            new Thread(r2).start();
        }
    }
}
