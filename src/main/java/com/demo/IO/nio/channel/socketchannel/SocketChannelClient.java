package com.demo.IO.nio.channel.socketchannel;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author epanhai
 */
public class SocketChannelClient {
    /**
     * 测试SocketChannelServer.blockServerSocketChannel() 方法
     * 阻塞式客户端SocketChannel
     * SocketChannel同时处于服务端和客户端。所以，对于一个连接，两端都有一个负责传输的SocketChannel,这里是客户端SocketChannel
     *
     * <p>
     * 1. 获取通道方式1
     * SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
     * <p>
     * 2. 获取通道方式2
     * SocketChannel socketChannel2 = SocketChannel.open();
     * socketChannel2.connect(new InetSocketAddress("127.0.0.1", 9899));
     *
     * @throws IOException
     */
    @Test
    public static void blockSocketChannel() throws IOException, InterruptedException {
        //1. 获取通道方式1
        //SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8888));

        //配置通道读写的阻塞模式,这里配置为阻塞模式
        socketChannel.configureBlocking(true);

        //缓冲区写入内容
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("This message comes from client".getBytes(StandardCharsets.UTF_8));
        //切换成读模式
        byteBuffer.flip();
        //在休眠期间，客户端断没有给服务端发送消息，服务端的传输socket又设置成了阻塞模式，服务端的通道没有数据，所以socketChannel.read(byteBuffer)会在此期间阻塞
        Thread.sleep(20000);
        //把缓冲区的数据写入到通道，服务端的通道有了数据，所以socketChannel.read(byteBuffer)会解除阻塞，正常运行
        socketChannel.write(byteBuffer);
        Thread.sleep(30000);
    }


    /**
     * 非阻塞式客户端SocketChannel
     *
     * @throws IOException
     */
    @Test
    public static void noneBlockSocketChannel() throws IOException, InterruptedException {
        //1. 获取通道方式2
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8888));

        //配置通道读写的阻塞模式,这里配置为阻塞模式
        //当通道中没有数据的时候，将会在此阻塞，后面代码不会被执行
        socketChannel.configureBlocking(false);


        //创建缓冲区并写入数据
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("some data from client".getBytes());

        //把缓冲区中的数据写入通道
        byteBuffer.flip();
        socketChannel.write(byteBuffer);

        //创建缓冲区写入数据,再次把缓冲区中的数据写入通道
        Thread.sleep(1000);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);
        byteBuffer2.put("some data from client2".getBytes());
        byteBuffer2.flip();
        socketChannel.write(byteBuffer2);
    }
}
