package com.demo.IO.io.bio_socket.demo3.demo1;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端需要处理很多个客户端的消息通信请求应该如何处理呢，此时我们就需要在服务端引入子线程，
 * 也就是说客户端每发起一个请求，服务端就创建一个新的线程来处理这个客户端的请求，
 * 这样就实现了一个客户端一个线程的模型，
 *
 * 同步和异步(synchronous/asynchronous)：
 * 同步是一种可靠的有序运行机制，当我们进行同步操作时，后续的任务是等待当前调用返回，才会进行下一步；
 * 而异步则相反，其他任务不需要等待当前调用返回，通常依靠事件、回调等机制来实现任务间次序关系。
 * 个人理解：同步和异步强调的是两个对象或者两个动作的前后顺序
 *
 *阻塞与非阻塞（blocking/non-blocking):
 * 在进行阻塞操作时，当前线程会处于阻塞状态，无法从事其他任务(退出了cpu时间片的争夺)，只有满足条件时才能重新恢复到就绪状态
 * 非阻塞则是不管 IO 操作是否结束，直接返回，相应操作在后台继续处理。
 *
 * 同步阻塞 I/O：
 * 用户线程发起 read 调用后就阻塞了，让出 CPU。内核等待网卡数据到来，把数据从网卡拷贝到内核空间，接着把数据拷贝到用户空间，再把用户线程叫醒。
 *
 *
 * @author epanhai
 */
public class SocketServer3 {


    /**
     * 一个主线程监听了众多客户端的连接请求
     * 用多个子线程为多个连接进行数据传输
     * 存在的问题：
     * 1 每个Socket接收到，都会创建一个线程，线程的竞争、切换上下文消耗影响cpu性能；
     * 2 每个线程都会占用内存栈空间资源；
     * 3 并不是每个socket都进行IO操作，某个线程中，客户端可能只是连接但没发送数据，对应子线程就会处于阻塞状态，因此可能会存在很多无意义的线程处理
     * 4 客户端的并发访问增加时，服务端将呈现1:1的线程开销，访问量越大，系统将发生线程栈溢出，线程创建失败，
     * 最终导致进程宕机或者僵死，从而不能对外提供服务。
     *
     * @throws IOException
     */
    @Test
    public static void serve() throws IOException {
        //注册端口
        ServerSocket serverSocket = new ServerSocket(55531);
        System.out.println("服务端等待客户端的连接请求...");
        while (true) {
            //开始在这里暂停等待接收客户端的连接,得到一个端到端的Socket管道
            Socket socket = serverSocket.accept();
            System.out.println("客户端 " + socket.getRemoteSocketAddress() + " 连接成功");

            //第一个客户端连接建立后，这里会启动第一个子线程，启动之后子线程就异步运行，然后主线程马上返回到while开始的地方继续监听，处于阻塞状态
            //第一个客户端连接建立后，这里会启动第二个子线程，启动之后子线程就异步运行，然后主线程马上返回到while开始的地方继续监听，处于阻塞状态
            //服务端为每个连接提供一个独立的线程来进行数据传输
            //到此为止，一共有1个主线程和2个子线程，而其中的某个子线程中客户端可能并没有发送数据，子线程就会处于阻塞状态，所以可能会存在无意义的子线程
            new Thread(() -> {
                try {
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String message;
                    //bufferedReader.readLine()是阻塞式的，如果当前客户端不发送数据，当前线程就会阻塞在这里，
                    // 后面的客户端都不能进行连接,
                    // 长连接：while替换if意味着一次连接可以接收多次客户端发送的消息
                    while ((message = bufferedReader.readLine()) != null) {
                        System.out.println("服务端收到消息： " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


    /**
     * 用一个主线程监听了众多客户端的连接请求
     * 用多个子线程为多个连接进行数据传输
     * 线程池为每个连接提供一个独立的线程来进行数据传输，使用线程池可以限制数据传输的线程数量
     * @throws IOException
     */
    @Test
    public static void serve2() throws IOException {
        // 监听指定的端口
        int port = 55531;
        ServerSocket server = new ServerSocket(port);
        System.out.println("server将一直等待连接的到来");

        //如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
        //线程池为每个连接提供一个独立的线程来进行数据传输，使用线程池可以限制数据传输的线程数量
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        while (true) {
            //这里实际上用一个线程监听了众多客户端的连接请求
            Socket socket = server.accept();
            System.out.println("客户端"+socket.getRemoteSocketAddress()+"发起连接");

            //创建任务
            Runnable runnable = () -> {
                try {
                    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String message;
                    //bufferedReader.readLine()是阻塞式的，如果当前客户端不发送数据，当前线程就会阻塞在这里，
                    // 后面的客户端都不能进行连接, 另外这里使用if意味着服务端只能接收一次客户端的消息
                    while ((message = bufferedReader.readLine()) != null) {
                        System.out.println("服务端收到消息： " + message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            //提交任务
            //为每个连接提供一个独立的线程来进行数据传输，使用线程池可以限制数据传输的线程数量
            threadPool.submit(runnable);
        }
    }
}
