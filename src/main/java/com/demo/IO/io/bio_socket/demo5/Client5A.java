package com.demo.IO.io.bio_socket.demo5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author epanhai
 */
public class Client5A {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 55531);
        Runnable send = () -> {
            Scanner scanner = new Scanner(System.in);
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                while (true) {
                    String msg = scanner.nextLine();
                    writer.write(msg);
                    //这里必须要有newLine()和flush()，否则服务端收不到消息
                    writer.newLine();
                    writer.flush();
                   // socket.shutdownOutput();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };


        Runnable receive = () -> {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg;
                while ((msg=reader.readLine())!=null) {
                    System.out.println(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        new Thread(send).start();
        new Thread(receive).start();
    }
}
