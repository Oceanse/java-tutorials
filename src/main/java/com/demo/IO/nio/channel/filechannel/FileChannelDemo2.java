package com.demo.IO.nio.channel.filechannel;

import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo2 {

    /**
     * 分散读取（Scatter ）:是指把Channel通道的数据读入到多个缓冲区中去
     *
     * 聚集写入（Gathering ）是指将多个 Buffer 中的数据“聚集”到 Channel。
     * @throws IOException
     */
    @Test
    public void scatter_gathering() throws IOException{
        //1. 获取输入通道
        FileInputStream fis = new FileInputStream("testResource\\test.txt");
        FileChannel inChannel = fis.getChannel();

        //2. 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(4);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //3. 读取通道中的数据，然后写入到多个缓冲区中
        ByteBuffer[] bufs = {buf1, buf2};
        inChannel.read(bufs);

        //把所有的缓冲区设置为读模式
        for (ByteBuffer byteBuffer : bufs) {
            byteBuffer.flip();
        }

        //读取两个缓冲区的内容
        System.out.println("(bufs[0]: "+new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("(bufs[1]: "+new String(bufs[1].array(), 0, bufs[1].limit()));

        //获取输出通道
        FileOutputStream fos = new FileOutputStream("test2.txt");
        FileChannel outChannel = fos.getChannel();

        //读取多个缓冲区的数据，然后写入到通道
        outChannel.write(bufs);
    }


    /**
     * transferFrom: Transfers bytes into this channel's file from the given readable byte channel.
     *
     * @throws IOException
     */
    @Test
    public void transferFrom() throws IOException {
        FileInputStream fis=new FileInputStream("testResource/kobe.jpg");
        FileOutputStream fos = new FileOutputStream("testResource/kobe2.jpg");
        //由文件输入流得到文件的输入通道
        FileChannel inChannel = fis.getChannel();
        //由文件输出流得到文件的输出通道
        FileChannel outChannel = fos.getChannel();
        //transferFrom(ReadableByteChannel src, long position, long count)
        outChannel.transferFrom(inChannel,0,inChannel.size());
    }


    /**
     * transferTo: Transfers bytes from this channel's file to the given writable byte channel.
     * @throws IOException
     */
    @Test
    public void transferTo() throws IOException {
        FileInputStream fis=new FileInputStream("testResource/kobe.jpg");
        FileOutputStream fos = new FileOutputStream("testResource/kobe3.jpg");
        //由文件输入流得到文件的输入通道
        FileChannel inChannel = fis.getChannel();
        //由文件输出流得到文件的输出通道
        FileChannel outChannel = fos.getChannel();
        //transferTo(long position, long count,WritableByteChannel target)
        inChannel.transferTo(0,inChannel.size(),outChannel);
    }

}
