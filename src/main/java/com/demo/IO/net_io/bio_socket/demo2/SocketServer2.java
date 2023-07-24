package com.demo.IO.net_io.bio_socket.demo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这里想通过while死循环(本质上还是一个线程)实现一个服务端可以接收多个客户端的连接发送消息请求；
 *
 * 缺点1：由于监听serverSocket.accept()和读取数据流bufferedReader.readLine()都是阻塞式的，
 * 所以只有前一个客户端成功连接并发送消息后，下一个客户端才能发起连接；
 * 如果第一个客户端发起连接，但是不发送数据，那么后面其它的客户端都不能和服务端进行通信(服务端处于阻塞状态收不到消息)
 * 也就是多个之间的客户端连接/发送消息是同步的；
 *
 * 缺点2：一个客户端连接成功后只能发送一次消息，想要发送第二次消息就需要重新建立第二次连接，可以理解成所谓的短连接
 *
 * @author epanhai
 */
public class SocketServer2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(55531);
        while(true){
            System.out.println("服务端等待客户端的连接请求...");
            //只有前一个客户端的消息处理完后，才能回到这里的accept接受新的客户端请求（应用层面）
            Socket socket = serverSocket.accept();
            System.out.println("客户端 "+socket.getRemoteSocketAddress()+" 连接成功");
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String message;
            //bufferedReader.readLine()是阻塞式的，如果当前客户端不发送数据，当前线程就会阻塞在这里， 后面的客户端都不能进行连接
            //短连接：if意味着一次连接只能接收一次客户端的消息,也就是客户端如果想发送第二次消息必须要重新发起新的连接
            if ((message=bufferedReader.readLine())!=null){
                System.out.println("服务端收到消息： "+message);
            }
        }

    }
}
