package com.demo.IO.nio.channel.socketchannel;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class DatagramChannelDemo {


    /**
     * 服务端接收udp数据包
     *
     * @throws IOException
     */
    @Test
    public void receiveDatagram() throws IOException, InterruptedException {
        //UDP 不存在真正意义上的连接，这里的连接是向特定地址用 read 和 write 接收发送数据包
        DatagramChannel receiveChannel = DatagramChannel.open();
        InetSocketAddress receiveAddress = new InetSocketAddress(9999);
        receiveChannel.bind(receiveAddress);

        //通道设置成非阻塞模式
        receiveChannel.configureBlocking(false);

        ByteBuffer receiveBuffer = ByteBuffer.allocate(512);

        while (true) {
            receiveBuffer.clear();
            //receive(ByteBuffer dst), 从Channel读取数据写入到buffer中
            //如果receiveChannel设置成了非阻塞模式，当receiveChannel中没有数据的时候就会立刻返回null
            //如果receiveChannel设置成了阻塞模式，当receiveChannel中没有数据的时候就会阻塞
            //返回值：非阻塞模式下，当receiveChannel中没有数据的时候就会立刻返回null，有数据则返回数据包的源地址(发送方地址)
            //       阻塞模式下，当receiveChannel中没有数据的时候就会阻塞，有数据则返回数据包的源地址(发送方地址)
            SocketAddress sendAddress = receiveChannel.receive(receiveBuffer);

            //通道中有数据：
            if(sendAddress==null){
                System.out.println("通道中没有数据，请等待");
                Thread.sleep(1000);
            //通道中没有数据：
            }else{
                //读写反转，转换为写操作
                receiveBuffer.flip();
                //输出发来信息的ip地址
                System.out.print(sendAddress + "发来消息：");
                System.out.println(Charset.forName("UTF-8").decode(receiveBuffer));
            }
        }
    }


    /**
     * 客户端发送udp数据包
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void sendDatagram() throws IOException, InterruptedException {

        DatagramChannel clientChannel = DatagramChannel.open();
        InetSocketAddress destAddress = new InetSocketAddress("127.0.0.1", 9999);
        clientChannel.connect(destAddress);

        while (true) {
            clientChannel.send(ByteBuffer.wrap("这是来自客户端的消息".getBytes("UTF-8")), destAddress);
            System.out.println("客户端发送数据包");
            Thread.sleep(1000);
        }
    }




}
