package com.demo.IO.net_io.bio_socket.demo3.demo2;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * 目标：实现客户端上传任意类型的文件数据给服务端保存起来。
 * 客户端怎么发，服务端就怎么接收
 *
 * @author epanhai
 */
public class UploadFileServer {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8888);
            while (true) {
                Socket socket = ss.accept();
                // 交给一个独立的线程来处理与这个客户端的文件通信需求。
                new ServerReaderThread(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ServerReaderThread extends Thread {

    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 1、得到一个数据输入流读取客户端发送过来的数据
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            // 2、读取客户端发送过来的文件类型
            String suffix = dis.readUTF();
            System.out.println("服务端已经成功接收到了文件类型：" + suffix);
            // 3、定义一个字节输出管道负责把客户端发来的文件数据写出去
            OutputStream os = new FileOutputStream("testResource" + File.separator + UUID.randomUUID() + suffix);
            // 4、从数据输入流中读取文件数据，写出到字节输出流中去
            byte[] buffer = new byte[1024];
            int len;
            //dis.read(buffer)只有在读到流的末尾的时候才会返回-1，当客户端告知输出结束也会返回-1
            // 当没有读到流的末尾的时候就会读取流中的数据，流中没有数据就会阻塞，阻塞的过程中socket连接关闭会产生异常
            while ((len = dis.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            os.close();
            System.out.println("服务端接收文件保存成功！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}