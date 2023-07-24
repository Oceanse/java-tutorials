package com.demo.IO.net_io.bio_socket.demo2;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author epanhai
 */
public class SocketClient2 {
    public static void main(String[] args) throws IOException {
        connectAndSendMsg();
    }

    /**
     * 服务端只能收到客户端发送的第一条消息，收不到之后的消息
     * 编辑设置成多实例，运行多次此方法模拟多个客户端，
     * 第一个客户端的消息没处理完毕时，其他客户端不能在应用层连接成功(但是内核层面能连接成功)
     * 另外每个客户端每次连接，只能发送一次消息，类似短连接
     * @throws IOException
     */
    public static void connectAndSendMsg() throws IOException {
        Socket socket = new Socket("127.0.0.1", 55531);
        OutputStream outputStream = socket.getOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("客户端说2：");
            String msg = scanner.nextLine();
            printStream.println(msg);
        }
    }
}
