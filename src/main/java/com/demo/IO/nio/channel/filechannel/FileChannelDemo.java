package com.demo.IO.nio.channel.filechannel;


import org.testng.annotations.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/** BIO:面向流
 *  NIO:面向缓冲区
 *  要时刻记住：Channel只负责运输数据,不与数据打交道；与数据打交道的是Buffer缓冲区
 *  通道表示打开到IO设备(文件、套接字等)的连接；缓冲区用来缓冲容纳数据
 *  读取文件：文件--->输入通道--->缓冲区--->程序
 *  写入文件：程序--->缓冲区--->输出通道--->文件
 *
 *
 *  Java NIO中的所有I/O操作都基于Channel对象，就像流操作都要基于Stream对象一样；
 *  通道使用起来跟Stream比较像，可以读取数据到Buffer中，也可以把Buffer中的数据写入通道。
 *  当然，也有区别，主要体现在如下两点：
 * 1：一个通道，既可以读又可以写，而一个Stream是单向的（所以分 InputStream 和 OutputStream）
 * 2：通道有非阻塞I/O模式
 *
 * Java NIO中最常用的通道实现是如下几个，可以看出跟传统的 I/O 操作类是一一对应的。
 * FileChannel：读写文件
 * DatagramChannel: UDP协议网络通信
 * SocketChannel：TCP协议网络通信
 * ServerSocketChannel：监听TCP连接
 *
 * 注意：FileChannel不支持非阻塞模式
 *
 * @author epanhai
 */
public class FileChannelDemo {

    /**
     * nio读取本地文件
     * 文件--->文件输入流--->输入通道--->缓冲区--->程序
     * @throws IOException
     */
    @Test
    public void readFile() throws IOException {
        // 1 定义一个文件字节输入流与源文件接通
        FileInputStream fileInputStream = new FileInputStream("pom.xml");

        // 2 需要得到文件字节输入流的文件通道
        FileChannel fileChannel = fileInputStream.getChannel();

        // 3 定义一个缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(128);

        //4 从channel读取数据，然后写入缓冲区，fileChannel.read可以翻译成“从通道读”
        int readCount;
        while((readCount=fileChannel.read(byteBuffer))!=-1){
            // 切换成读模式，读取缓冲区数据；byteBuffer.array()是将缓冲区转化成数组
            byteBuffer.flip();
            System.out.print(new String(byteBuffer.array(), 0, readCount));
            //每当要向缓存区写数据时，就调用clear“切换成写模式”。
            byteBuffer.clear();
        }
    }


    /**
     * 通过RandomAccessFile（文件随机访问）类来获取FileChannel实例
     * @throws IOException
     */
    @Test
    public void readFile2() throws IOException{
        // 通过RandomAccessFile（文件随机访问）类来获取FileChannel实例
        RandomAccessFile  raf = null;
        FileChannel channel = null;
        try {
            raf = new RandomAccessFile ("testResource\\test.txt","rw");
            channel = raf.getChannel();

            // 定义一个字节缓冲区域
            ByteBuffer buffer = ByteBuffer.allocate(10);

            int length;
            //从通道读，然后写入缓冲区，返回的是实际读到的字节数，通道没数据的时候会返回-1
            while ((length = channel.read(buffer)) != -1) {
                // 调用flip,buffer由写入变为读
                buffer.flip();
                byte[] array = buffer.array();
                System.out.print(new String(array, 0, length,"utf-8"));
                //每当要向缓存区写数据时，就调用clear“切换成写模式”。
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            raf.close();
            channel.close();
        }
    }




    /**
     * nio写数据到本地文件
     * 程序--->缓冲区--->输出通道--->文件输出流--->文件
     * @throws IOException
     */
    @Test
    public void writeFile() throws IOException {
        // 1字节输出流通向目标文件
        FileOutputStream fileOutputStream=new FileOutputStream("testResource\\test.txt");

        // 2得到字节输出流对应的通道Channel
        FileChannel channel = fileOutputStream.getChannel();

        // 3分配缓冲区并写入数据
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        byteBuffer.put("rainbow".getBytes(StandardCharsets.UTF_8));

        //4读取缓冲区的数据，然后写入到输出通道， channel.write可以理解成向通道里写
        //要把缓冲区切换成读模式
        byteBuffer.flip();
        while(byteBuffer.hasRemaining()){
            channel.write(byteBuffer);
        }

        channel.close();
        System.out.println("写数据到文件中！");
    }


    /**
     * nio完成文件复制
     * 源文件--->文件输入流--->文件输入通道--->缓冲区--->文件输出通道--->文件输出流--->目的文件
     * 作为文件复制的程序来说，当前的效率不是最高的。
     * 更高效的文件复制可以调用文件通道的transferFrom()或者transferTo方法。
     * @throws IOException
     */
    @Test
    public void copy() throws IOException {
        FileInputStream fis=new FileInputStream("testResource/kobe.jpg");
        FileOutputStream fos = new FileOutputStream("testResource/kobe2.jpg");

        //由文件输入流得到文件的输入通道
        FileChannel inChannel = fis.getChannel();
        //由文件输出流得到文件的输出通道
        FileChannel outChannel = fos.getChannel();

        //创建大小为1024字节的缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(102400);

        //从输入通道读取数据，然后写入到缓冲区；然后再读取缓冲区的数据写入到输入通道
        //inChannel.read返回值表示从通道读到了多少字节, 若通道中没有数据，则返回-1
        //如果缓冲区比较小，那么最开始的几次，缓冲区都会被填满，返回值就是缓冲区的容量，当通道没有数据时候， 返回值是-1
        while(inChannel.read(byteBuffer)!=-1){
            //将Buffer数据写入通道是对Buffer进行读取操作，读取之前要对buffer进行flip操作
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            //在将缓冲区写入通道时，出于性能的原因，操作系统不可能每次都实时地将写入数据落地（或刷新）到磁盘，完成最终的数据保存。
            //在将缓冲区数据写入通道时，要保证数据能写入磁盘，可以在写入后调用一下FileChannel的force()方法
            outChannel.force(true);
            //下次对缓冲区buffer进行写之前进行清空clear操作
            byteBuffer.clear();
        }
    }


}
