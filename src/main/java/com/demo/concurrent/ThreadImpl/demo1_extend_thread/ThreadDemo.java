package com.demo.concurrent.ThreadImpl.demo1_extend_thread;
/*程序(program)是为完成特定任务、用某种语言编写的一组指令的集合。即指一段静态的代码，静态对象，具有静态性

进程(process)是程序的一次执行过程，或是正在运行的一个程序，具有动态性：有它自身的产生、存在和消亡的过程。如：运行中的QQ，java程序运行时可以理解为一个进程
进程之间内存资源不共享，不同的进程不能互相访问对方的资源


线程(com.demo.thread)，进程可进一步细化为线程，是一个进程内部的一条执行路径，每个进程可以有多条执行路径。

JAVA虚拟机的一项任务就是负责线程的调度,线程调度是指按照特定机制为多个线程分配CPU的使用权.
java虚拟机采用抢占式调度模型，系统会给每个可执行的线程一个小时间段来处理任务；当该时间段用完后，系统就会剥夺该线程所占用的资源，让其他线程获得执行的机会。在选择下一个线程时，系统会考虑线程的优先级。
是指优先让可运行池中优先级高的线程占用CPU，如果可运行池中的线程优先级相同，那么就随机选择一个线程，使其占用CPU

线程包含两个要素：线程对象和线程体,线程对象是Thread的子类或者Thread， 线程对象执行线程体，线程体就是线程对象中方法的方法体
当执行java命令，java虚拟机自动创建一个线程对象，这个线程就是主线程，它的线程体就是main()的方法体，这个线程会调用执行main方法
一个线程体可能被多个线程执行，但是main线程体只会被主程序调用

线程生命周期：从线程对象被创建到这个对象被销毁的整个过程，需要经过新建（New）、就绪（Runnable）、运行（Running）、阻塞（Blocked）和死亡（Dead）五种状态

新建(new)：      当程序使用new关键字创建了一个线程之后，该线程就处于一个新建状态（初始状态），此时它和其他Java对象一样，仅仅由Java虚拟机为其分配了内存，并初始化了其成员变量值。
                此时的线程对象没有表现出任何线程的动态特征，程序也不会执行线程的线程执行体。
就绪(Runnable)： 当线程对象调用了start()方法之后，该线程处于就绪状态，处于这个状态的线程并没有开始运行，它只是表示该线程可以运行了，至于该线程何时开始运行，取决于JVM里线程调度器的调度
运行 (Running):  如果处于就绪状态的线程获得了CPU资源，就开始执行run方法的线程执行体，则该线程处于运行状态
阻塞 (Blocked):  阻塞状态是线程因为某种原因放弃CPU使用权，并且不再参与cpu的争抢，暂时停止运行，此时的线程处于挂起状态； 阻塞结束后线程会进入就绪状态，等待cpu分配时间片
                当发生如下情况时，线程将会进入阻塞状态，阻塞结束后，线程重新进入就绪状态；线程从阻塞状态只能进入就绪状态，无法直接进入运行状态
　     　       线程调用sleep()方法主动放弃所占用的处理器资源，线程进入阻塞状态；调用sleep()方法的线程经过了指定时间可解除阻塞
　         　   线程调用了一个阻塞式IO方法，在该方法返回之前，该线程被阻塞；线程调用的阻塞式IO方法已经返回可解除阻塞
　　            线程试图获得一个同步监视器，但该同步监视器正被其他线程所持有，线程进入阻塞状态；线程成功地获得了试图取得的同步监视器可解除阻塞
　              线程在等待某个通知（notify），线程正在等待某个通知时，线程进入阻塞状态；其他线程发出了这个通知可解除阻塞
                当前正在执行的线程被阻塞之后，其他线程就可以获得执行的机会。被阻塞的线程会在合适的时候重新进入就绪状态，
                注意是就绪状态而不是运行状态。也就是说，被阻塞线程的阻塞解除后，必须重新等待线程调度器再次调度它
死亡(Dead):     线程会以以下三种方式之一结束，结束后就处于死亡状态:
                run()方法执行完成，线程对象被垃圾回收机制回收，线程正常结束。
                线程抛出一个未捕获的Exception或Error。
                直接调用该线程的stop()方法来结束该线程——该方法容易导致死锁，通常不推荐使用。


两个线程并发执行是这样：比如线程A：for(int i=0;i<10;i++){ System.out.println(i)}
                         线程B: for(int i=0;i<10;i++){ System.out.println(i)}
 线程A，B执行完需可能要很多时间片；A可能先抢占时间片，执行完一部分，然后B抢到时间片，B执行完一部分；然后A抢到...两个线程随机交替执行，对外呈现出多线程并发执行

 对于单核cpu来说，任何时刻只有一个线程处于运行状态，当其中一个线程发出IO请求，cpu在等待内存IO时(线程调用了一个阻塞式IO方法, 在该方法返回资源之前，比如说监听system.in，但是尚且没有收到键盘的输入，
 该线程被阻塞，另外io也要花时间例如去数据库查数据，去抓网页，读写文件等)，cpu就会处于空闲状态
 此时如果采用多线程，其它线程就会抢到cpu时间片，这样就提高了cpu的利用率；cpu会在多个线程之间高速切换


*/


//这个案例中主线程执行主线程体，两个子线程执行相同的线程体，
// 但是子线程启动(start)之前,整个程序还是单个线程
// 两个仔细线程start后，三个线程并发执行
public class ThreadDemo extends Thread {

    /**
     * 把run()方法的主体称为线程体，线程对象执行线程体
     */
    @Override
    public void run() {//重写Thread的run方法
        for (int i = 0; i < 100; i++) {
            /*
              当前线程体可能被多个线程对象执行，Thread.currentThread()
            * 返回当前正在执行该线程体的线程对象的引用,哪个线程对象正执行该线程体，
            * 就返回这个线程体对象的引用
            * */
            System.out.println(Thread.currentThread().getName() + "优先级：" + Thread.currentThread().getPriority() + ":   " + i);
        }
    }

    public static void main(String[] args) {
        //当执行java命令，java虚拟机自动创建一个线程对象，这个线程就是主线程，这个线程会调用执行main方法
        //可以把这个main()理解为类似run方法的特殊的线程体，只不过这个线程体只有主线程才会调用
        //main方法整个方法体都是主线程的线程体


        //创建两个线程对象(两个子线程处于新建状态，此时只是两个普通的java对象)，两个执行相同的线程体(可能存在线程安全问题)
        //可以创建多个线程类，从而有多个run方法，然后创建不同类的线程对象执行不同的线程方法
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.setName("线程1");//给线程设置名字(线程对象的引用和线程名字是不同的，线程对象保存的是线程对象的堆地址，线程名字是这个对象的成员变量，可以理解为这个对象的名字）
        threadDemo.setPriority(5);//给线程设置优先级，优先级越高，获取cpu片段的几率越大；必须在start()之前设置

        ThreadDemo threadDemo2 = new ThreadDemo();
        threadDemo2.setName("线程2");
        threadDemo2.setPriority(6);

        //返回当前正在执行该线程体的线程对象的引用,这里t指向JVM自动创建的主线程对象
        Thread t = Thread.currentThread();
        t.setName("主线程");
        t.setPriority(4);


        /*
        创建启动线程:
        ThreadDemo threadDemo=new ThreadDemo();
        ThreadDemo threadDemo2=new ThreadDemo();
        threadDemo.start();
        threadDemo2.start();
        开启两个线程(start)进入就绪状态，这里进入就绪状态个人理解为几乎是同时完成，然后他们站在同一起跑线上，
        等待cpu(或者说)调度,cpu调度是随机的，所以执行时间也是随机的，所以threadDemo2可能早于threadDemo执行
        * */

        //到这里才会启动子线程1和2，在这之前都是单线程，只有一个主线程； 这两个线程和主线程后面的代码是异步执行的
        threadDemo.start();//通过Thread对象的start()方法来启动这个线程
        threadDemo2.start();

        //主线程,这里的代码不需要等到上面的代码执行完再执行，这里的主线程和上面的子线程并发执行
        //所以对于threadDemo.start()，threadDemo2.start()，以及下面主线程，他们没有明确的先后执行顺序
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "优先级：" + Thread.currentThread().getPriority() + ":   " + i);
        }
    }
}
