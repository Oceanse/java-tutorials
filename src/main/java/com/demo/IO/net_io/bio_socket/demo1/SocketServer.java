package com.demo.IO.net_io.bio_socket.demo1;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * BIO： 同步阻塞式的I/O通信
 * 服务端处理单个客户端消息请求，客户端多次发送，服务端多次接收
 * 服务端是单线程的，用一个线程进行处理连接请求和数据传输请求，因此服务端一次只能与一个客户端进行消息通信，只能处理一个客户端连接通信请求；
 * 当服务端与一个客户端进行连接通信的时候，如果我们启动第二个客户端尝试连接此服务端，内核级别是可以连接成功的，可以通过netstat -napt查看到tcp连接，
 * 但是服务端不能收到客户端发送的消息，因为服务端用来通信的socket是通过serverSocket.accept()获取的，
 * 但是由于服务端的accept方法只能执行一次，所以客户端不能通过accept从内核中取走这个连接，也就是不能为第二个客户端生成通信的socket
 *
 * socket.accept()、socket.read()、socket.write() 三个主要函数都是【同步 阻塞】的
 *
 *
 * @author epanhai
 */
public class SocketServer {



    /**
     * socket.accept()导致阻塞发生,也就是只有客户端的socket和server端的socket链接成功后才会解除block
     * inputStream.read()/inputStream.read(bytes)/bufferedReader.readLine()都是阻塞式的，当流中没有数据的时候就会阻塞等待
     *
     * @throws Exception
     */
    @Test
    public void serverByReadByteArray() throws IOException {
        //启动server sockets, 监听10085
        //ServerSocket可以理解为服务端的监听socket，用来监听客户端的连接请求
        //服务端用一个监听socket来服务所有的客户端连接，监听到客户端A接入请求时会通过accept产生一个新的传输socket1用于和客户端A通信
        //然后又有一个客户端B请求连接，监听socket监听到连接后又通过accept产生一个一个新的传输socket2用于和客户端B通信
        //所以服务端会有一个监听socket和多个传输socket/通信socket
        //ServerSocket(int port) 构造函数包含了绑定bind和监听listen两个过程，所以在这里服务端已经处于监听状态
        ServerSocket serverSocket = new ServerSocket(10085);
        System.out.println("server等待连接的到来。。。");

        //阻塞，模拟即使不执行accept, 客户端和服务端也能在内核级别完成tcp连接；
        //计算机网络中可以有下层没有上层，所以即使没有accept,tcp连接依然可以存在于内核层
        //System.in.read();

        //accept()是应用从内核全连接队列中取走连接，最开始的时候，队列是空的，因此客户端连接之前，当前线程会在此阻塞，直到有客户端发起连接请求，内核全连接队列出现一个连接，accept就会取走这个连接，返回新的通信socket
        //当有客户请求连接并建立好新的连接后，accept()返回的一个新的套接字1(文件描述符fd1)，也就是传输socket(通信socket),用来和客户端1通信
        //当又有客户请求连接并建立好新的连接后，accept()返回的一个新的套接字2(文件描述符fd2)，也就是传输socket(通信socket),用来和客户端2通信
        //应用层进程通过新的通信socket或者文件描述符和客户端通信
        //因为后面服务端在主线程中循环读取客户端的消息，所以accept()只能被执行一次，意味着此服务端在应用程序只处理一个客户端的消息请求
        Socket socket = serverSocket.accept();

        //获取客户端地址
        SocketAddress clientAddress = socket.getRemoteSocketAddress();
        System.out.println("server被客户端 " + clientAddress + " 成功连接，等待客户端发送数据。。。");


        //建立好连接后，从socket中获取输入流,输入流
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        //read(byte b[])是从管道流中读取数据并存储到缓冲数组，此方法是阻塞的，This method blocks until input data is available
        // 如果客户端迟迟不发送数据，管道流中就没有数据，线程将会阻塞在这里
        //注意这里是while循环，在socket连接不关闭的情况下，客户端如果只发送了一次消息，那么在第二次循环的时候，管道流中又没有了数据，线程又会阻塞在这里
        //在socket的inputStream中， inputStream.read()或者inputStream.read(byte[] dest)永远不会返回-1，
        //管道流中只要没有数据，read方法会阻塞，所以while ((len = inputStream.read(bytes)) != -1)相当于死循环
        //个人理解：read(byte b[])或者readLine()是应用进程从内核空间读取数据到用户空间
        while ((len = inputStream.read(bytes)) != -1) {
            System.out.print(new String(bytes, 0, len, "UTF-8"));
        }
    }


    /**
     * socket.accept()导致阻塞发生,也就是只有客户端的socket和server端的socket链接成功后才会解除block
     * inputStream.read()/inputStream.read(bytes)/bufferedReader.readLine()都是阻塞式的，当流中没有数据的时候就会阻塞等待
     *
     * @throws Exception
     */
    @Test
    public void serverByReadLine() throws IOException {
        ServerSocket serverSocket = new ServerSocket(55532);
        System.out.println("服务端等待客户端的连接到来。。。");
        //accept()是阻塞的，客户端连接之前，当前线程会在此阻塞，直到有客户端发起连接请求
        Socket socket = serverSocket.accept();
        System.out.println("客户端" + socket.getRemoteSocketAddress() + " 发起连接请求");
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String message;

        //bufferedReader可以想象成一个管道，只要客户端发消息，管道中就会有新的数据注入
        //管道流中没有数据时候bufferedReader.readLine()会阻塞，等待数据的到来
        //bufferedReader.readLine()要求客户端发送消息的时候要带有换行符，如果管道流中的数据没有换行符，readLine()就会阻塞
        //bufferedReader.readLine()永远不会返回null
        while ((message = bufferedReader.readLine()) != null) {
            System.out.println("服务端收到： " + message);
        }
        bufferedReader.close();
    }

}
