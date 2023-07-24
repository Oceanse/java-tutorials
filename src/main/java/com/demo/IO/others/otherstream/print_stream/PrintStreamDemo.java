package com.demo.IO.others.otherstream.print_stream;

import org.testng.annotations.Test;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * PrintStream(OutputStream out)
 */
public class PrintStreamDemo {
    @Test
    public static void connectAndSendMessageToServer() throws IOException {
        //要连接的服务端IP地址和端口,与服务端建立连接
        Socket socket = new Socket("127.0.0.1", 55532);
        OutputStream outputStream = socket.getOutputStream();
        try (PrintStream printStream = new PrintStream(outputStream)) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("客户端说： ");
                //这里scanner.nextLine()是阻塞的，scanner中有数据的时候才会解除阻塞
                String message = scanner.nextLine();
                printStream.println(message);
            }
        }
    }

    @Test
    public void writeToFile() throws FileNotFoundException {
        FileOutputStream fileOutputStream=new FileOutputStream("out.txt");
        PrintStream printStream=new PrintStream(fileOutputStream);
        printStream.println(97);
        printStream.println('a');
        printStream.println("今天是2022.10.6");
    }



    public static void log(String message) throws FileNotFoundException {
        FileOutputStream fileOutputStream=new FileOutputStream("out.txt",true);
        PrintStream printStream=new PrintStream(fileOutputStream);
        Date time=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd HH:mm:ss SSS");
        String logTime = sdf.format(time);
        printStream.println(logTime+": "+message);
    }

    public static void main(String[] args) throws FileNotFoundException {
        log("打好java基础");
        log("正在学习io");
    }

}
