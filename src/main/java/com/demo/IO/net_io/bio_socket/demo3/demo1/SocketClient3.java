package com.demo.IO.net_io.bio_socket.demo3.demo1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author epanhai
 */
public class SocketClient3 {
    public static void connectAndSendMessageToServer() throws IOException {
        //要连接的服务端IP地址和端口,与服务端建立连接
        Socket socket = new Socket("127.0.0.1", 55531);
        OutputStream outputStream = socket.getOutputStream();
        try (PrintStream printStream = new PrintStream(outputStream)) {
            Scanner scanner = new Scanner(System.in);
            //客户端可以发送多条消息
            while (true) {
                System.out.print("客户端"+socket.getLocalPort()+"说: ");
                //这里scanner.nextLine()是阻塞的，scanner中有数据的时候才会解除阻塞
                String message = scanner.nextLine();
                printStream.println(message);
            }
        }

    }


    public static void connectAndSendMessageToServer2() throws IOException {
        //要连接的服务端IP地址和端口,与服务端建立连接
        Socket socket = new Socket("127.0.0.1", 55531);
        OutputStream outputStream = socket.getOutputStream();
        try (PrintStream printStream = new PrintStream(outputStream)) {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
            //客户端可以发送多条消息
            while (true) {
                System.out.print("客户端"+socket.getLocalPort()+"说: ");
                //这里bufferedReader.readLine()是阻塞的，bufferedReader中有数据的时候才会解除阻塞
                String message = bufferedReader.readLine();
                printStream.println(message);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        connectAndSendMessageToServer();
    }
}