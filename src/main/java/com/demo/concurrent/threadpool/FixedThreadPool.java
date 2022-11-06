package com.demo.concurrent.threadpool;

import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 定长线程池，可控制线程最大并发数
 * 核心线程数等于最大线程数，所以线程全部创建完成后就会一直存在于线程池，所以使用完毕必须手动关闭线程池(否则会一直在内存中)
 * 如果线程池的数量达到了指定数量，并且有线程是空闲的，则取出空闲线程执行任务
 * 如果没有线程是空闲的，则将任务缓存到队列（队列长度为Integer.MAX_VALUE）
 * 当线程空闲的时候，按照FIFO的方式进行处理
 *
 * @author epanhai
 */
public class FixedThreadPool {

    @Test
    public void test() throws InterruptedException {
        /**
         * 如果线程池的当前大小还没有达到基本大小(poolSize < corePoolSize)，
         * 当有新的任务提交时候，就启动一个新的线程处理新提交的任务，即使线程池有空闲的线程；
         * 5个线程处理5个任务，这里每当有一个新请求，从线程池中启动一个新的线程处理新提交的任务，所以这里5个任务和5个线程一一对应
         * */
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int index = i;
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getId() + "执行任务" + index);
            };
            //任务数量等于核心线程数，所以线程和任务一一对应
            executorService.execute(task);
        }
        executorService.shutdown();//手动关闭线程池，否则核心线程会一直在等待任务，处于阻塞状态

    }

    @Test
    public void test2() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //任务数量>线程数量,
        // 如果线程池的当前大小还没有达到基本大小(poolSize < corePoolSize),会启动新的线程处理新提交的任务
        // 如果线程池的当前大小poolSize > corePoolSize=maximumPoolSize, 会任务缓存到任务队列，当有线程空闲下来时，会从队列取走任务进行处理
        for (int i = 0; i < 10; i++) {
            final int index = i;
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getId() + "执行任务" + index);
            };
            //从线程池取出一个新线程处理task
            executorService.execute(task);

        }
        executorService.shutdown();//关闭线程池

    }

    @Test
    public void test3() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //(ThreadPoolExecutor)executorService.getActiveCount();

    }


}
