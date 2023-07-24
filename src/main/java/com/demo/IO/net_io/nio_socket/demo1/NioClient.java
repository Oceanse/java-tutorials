package com.demo.IO.net_io.nio_socket.demo1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author epanhai
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress("localhost",55551));
        socketChannel.configureBlocking(false);
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        Scanner scanner=new Scanner(System.in);
        while (true){
            System.out.print("客户端说：");
            String msg = scanner.nextLine();
            //像缓冲区写入数据
            byteBuffer.put(msg.getBytes(StandardCharsets.UTF_8));
            //切换成读模式
            byteBuffer.flip();
            //读取缓冲区的数据到通道
            socketChannel.write(byteBuffer);
            //切换成写模式
            byteBuffer.clear();
        }
    }
}
