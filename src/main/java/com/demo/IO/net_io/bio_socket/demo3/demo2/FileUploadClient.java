package com.demo.IO.net_io.bio_socket.demo3.demo2;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * 目标：实现客户端上传任意类型的文件数据给服务端保存起来。
 * 客户端怎么发，服务端就怎么接收
 *  socket.shutdownOutput()：告诉服务端数据发送完毕
 * @author epanhai
 */
public class FileUploadClient {
    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("testResource/kobe.jpg")) {
            //  1、请求与服务端的Socket链接
            Socket socket = new Socket("127.0.0.1", 8888);
            //  2、把字节输出流包装成一个数据输出流
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            //  3、先发送上传文件的后缀给服务端
            dos.writeUTF(".png");
            //  4、把文件数据发送给服务端进行接收
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0) {
                dos.write(buffer, 0, len);
            }
            dos.flush();
            //这里要告诉服务端数据发送完毕，否则服务端的while((len = dis.read(buffer)) > 0)会在收到消息后还在阻塞等待，
            //阻塞的过程中，如果客户端忽然关闭，服务端的相应线程会产生异常
            //socket.shutdownOutput()相当于给服务端发送了EOF(-1),服务端 while ((len = dis.read(buffer)) > 0) 就会跳出循环
            socket.shutdownOutput();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
