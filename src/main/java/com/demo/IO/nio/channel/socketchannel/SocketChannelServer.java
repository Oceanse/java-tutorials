package com.demo.IO.nio.channel.socketchannel;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * NIO中的N可以理解为Non-blocking，不单纯是New。它支持面向缓冲的，基于通道的I/O操作方法。
 * NIO提供了与传统BIO模型中的 Socket 和 ServerSocket 相对应的 SocketChannel 和 ServerSocketChannel 两种不同的套接字通道实现，两种通道都支持阻塞和非阻塞两种模式。
 * 注意：FileChannel不支持非阻塞模式
 * <p>
 * ServerSocket和ServerSocketChannel可以理解为是监听socket, 负责连接的监听(监听器),它本身从不传输数据, 服务端一般只需要一个实例即可
 * Socket和SocketChannel是传输socket, 负责传送数据通信的，服务端会为每个连接生成一个新的传输socket
 * ServerSocket和ServerSocketChannel仅应用于服务端，而Socket和SocketChannel同时处于服务端和客户端。所以，对于一个连接，两端都有一个负责传输的SocketChannel。
 * <p>
 * ServerSocket调用accept()会返回Socket类型对象
 * ServerSocketChannel调用accept()会返回SocketChannel类型对象
 * <p>
 * 对于低负载、低并发的应用程序，可以使用同步阻塞I/O来提升开发速率和更好的维护性
 * 对于高负载、高并发的（网络）应用，应使用 NIO 的非阻塞模式来开发。
 *
 * @author epanhai
 */
public class SocketChannelServer {


    public static void main(String[] args) throws IOException, InterruptedException {
        noneBlockServerSocketChannel();
        //blockServerSocketChannel();
    }

    /**
     * 非阻塞式服务端socketChannel,可以实现一个服务器和多个客户端请求，但是每个客户端发送完消息后，连接就会关闭，弱项再次发送消息只能重新建立连接，也就是短连接
     * 监听socket(通道)设置为非阻塞模式，serverSocketChannel调用accept()是非阻塞的，没有连接传入时会立刻返回null
     * 传输socket(通道)设置为非阻塞模式，socketChannel调用socketChannel.read(byteBuffer)是非阻塞的，没有连接传入时会立刻返回0
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public static void noneBlockServerSocketChannel() throws IOException, InterruptedException {
        //监听socket, ServerSocketChannel类比bio中的ServerSocket，只不过增加了通道的语义，可以在非阻塞模式下运行，负责连接的监听。
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //Binds the channel's socket to a local address and configures the socket to listen for connections.
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));

        //监听socket(通道)设置为非阻塞模式，ServerSocketChannel调用accept()是非阻塞的
        serverSocketChannel.configureBlocking(false);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("This message comes from server".getBytes(StandardCharsets.UTF_8));

        //监听有没有连接传入
        while (true) {
            System.out.println("Waiting for connections");
            //Accepts a connection made to this channel's socket.等待连接传入,有连接传入的时候返回SocketChannel类型对象，负责连接的数据传输；
            //如果 serverSocketChannel.configureBlocking(true). 那么当前accept就是阻塞式的，也就是没有连接传入时候，会一直阻塞卡在这里；
            //如果 serverSocketChannel.configureBlocking(false). 那么当前accept就是非阻塞式的，如果没有连接传入时会立刻返回null
            SocketChannel socketChannel = serverSocketChannel.accept();

            //没有连接传入时：
            if (socketChannel == null) {
                System.out.println("No connection");
                Thread.sleep(1000);
            }
            //有连接传入时： 可以用telnet localhost 8888测试；这里客户端连接发送数据后，socket连接就会关闭，想要再次发送数据，只能重新连接
            else {
                System.out.println("Connection is coming from: " + socketChannel.getRemoteAddress());
                //socketChannel.read(byteBuffer)是从通道读取数据，然后写道缓冲区
                //如果 socketChannel.configureBlocking(true). 那么当前socketChannel.read就是阻塞式的，也就是通道中没有数据的时候socketChannel.read会阻塞
                //如果 socketChannel.configureBlocking(false). 那么当前accept就是非阻塞式的，如果通道中没有数据，socketChannel.read会立刻返回0
                //传输socket(通道)默认是阻塞式的，除非显式设置socketChannel.configureBlocking(false)
                //readCount是实际从通道读取到的字节数量
                //客户端向通道发了两次数据，服务端只能读取到其中一次的数据
                int readCount = socketChannel.read(byteBuffer);
                System.out.println("readCount = " + readCount);
                //缓冲区设置成读模式
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                //缓冲区设置成写模式
                byteBuffer.clear();
                socketChannel.close();
            }
        }
    }


    /**
     * 阻塞式服务端socketChannel
     * 监听socket(通道)设置为阻塞模式，serverSocketChannel调用accept()是阻塞的，没有连接传入时会一直阻塞卡在这里；
     * 传输socket(通道)设置为阻塞模式，socketChannel调用socketChannel.read(byteBuffer)是阻塞的，通道中没有数据的时候socketChannel.read会阻塞卡在这里
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public static void blockServerSocketChannel() throws IOException, InterruptedException {
        //Opens a server-socket channel,获得一个套接字传输通道；
        //ServerSocketChannel类比bio中的ServerSocket，只不过增加了通道的语义，可以在非阻塞模式下运行，负责连接的监听。
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //Binds the channel's socket to a local address and configures the socket to listen for connections.
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));

        //设置通道为阻塞模式，ServerSocketChannel调用accept()是阻塞的
        serverSocketChannel.configureBlocking(true);

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        System.out.println("Waiting for connections");
        //Accepts a connection made to this channel's socket.等待连接传入,有连接传入的时候返回SocketChannel类型对象，负责连接的数据传输；
        //如果 serverSocketChannel.configureBlocking(true). 那么当前accept就是阻塞式的，也就是没有连接传入时候，会一直阻塞卡在这里；
        //如果 serverSocketChannel.configureBlocking(false). 那么当前accept就是非阻塞式的，如果没有连接传入时会立刻返回null
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("Connection is coming from: " + socketChannel.getRemoteAddress());
        //socketChannel.read(byteBuffer)是从通道读取数据，然后写道缓冲区
        //如果 socketChannel.configureBlocking(true). 那么当前socketChannel.read就是阻塞式的，也就是通道中没有数据的时候socketChannel.read会阻塞卡在这里
        //如果 socketChannel.configureBlocking(false). 那么当前accept就是非阻塞式的，如果通道中没有数据，socketChannel.read会立刻返回0
        //传输socket(通道)默认是阻塞式的，除非显式设置socketChannel.configureBlocking(false)
        //readCount是实际从通道读取到的字节数量
        int length;
        while((length=socketChannel.read(byteBuffer))!=-1){

        }
        int readCount = socketChannel.read(byteBuffer);
        System.out.println("readCount = " + readCount);
        //缓冲区设置成读模式
        byteBuffer.flip();
        System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
        //缓冲区设置成写模式
        byteBuffer.clear();
        socketChannel.close();
    }


}
