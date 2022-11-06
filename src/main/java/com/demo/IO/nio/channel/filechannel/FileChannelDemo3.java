package com.demo.IO.nio.channel.filechannel;

import org.testng.annotations.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/**
 * 通道主要用于传输数据，从缓冲区的一侧传到另一侧的实体（如文件、套接字...），反之亦然；
 * <p>
 * 通道主要分为两大类，文件（File）通道和套接字（socket）通道；
 * <p>
 * file通道：FileChannel
 * socket通道类：SocketChannel、ServerSocketChannel和DatagramChannel；
 */
public class FileChannelDemo3 {
    public static void main(String[] args) throws IOException {

    }


    /**
     * 从url下载文件
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {

        //从该url下载文件
        String urlStr = "https://....test.jar";
        URL url = new URL(urlStr);

        //通道指向要下载的url, 在stream包一层channel
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());

        //通道指向目的文件
        FileOutputStream fos = new FileOutputStream("testResource\\resources.jar");
        FileChannel channel = fos.getChannel();

        //把字节流从源channel传输到目的channel
        channel.transferFrom(rbc, 0, Long.MAX_VALUE);

    }


    /**
     * 从url下载文件
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        //test jar的下载地址
        String urlStr = "https://....test.jar";
        URL url = new URL(urlStr);

        //此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类
        URLConnection rulConnection = url.openConnection();

        //HttpURLConnection有更多的API.
        HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;

        //从urlConnection获取inputStream
        InputStream inputStream = httpUrlConnection.getInputStream();

        //通道指向要下载的url, 在stream包一层channel
        ReadableByteChannel rbc = Channels.newChannel(inputStream);

        //通道指向目的文件,在stream包一层channel
        FileOutputStream fos = new FileOutputStream("testResource\\resources.jar");
        FileChannel channel = fos.getChannel();

        //把字节流从源channel传输到目的channel
        channel.transferFrom(rbc, 0, Long.MAX_VALUE);
    }


    /**
     * HttpURLconnection处理get请求
     *
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        URL url = new URL("https://www.baidu.com/");
        //得到connection对象。
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //设置请求方式
        connection.setRequestMethod("GET");
        //连接
        connection.connect();
        //得到响应码
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            //得到响应流
            InputStream inputStream = connection.getInputStream();

            //字节流根据指定的编码方式转成字符流，Charset.defaultCharset()=utf-8,这里面包含了解码的过程
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                System.out.println(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        }
    }

    @Test
    public void test4() throws Exception {
        RandomAccessFile ra = new RandomAccessFile("out.txt", "rw");
        FileChannel channel = ra.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = channel.read(buf);
        System.out.println(bytesRead);
    }
}
