package com.demo.IO.io.bio_socket.demo5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

/**
 * 群发：服务端接收消息并向所有客户端转发消息
 * @author epanhai
 */
public class Server5 {

    /**
     * 在线用户集合
     */
    public static HashMap<Integer, Socket> onlineUser=new HashMap<>();


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(55531);
        while (true){
            //服务端监听连接
            Socket socket = serverSocket.accept();
            //用户上线时候会把用户加入到在线用户集合
            onlineUser.put(socket.getPort(),socket);
            System.out.println("用户"+socket.getPort()+"上线");
            System.out.println("当前在线人数: "+ onlineUser.size());

            //服务端接收消息并向所有客户端转发消息，这里的socket可以理解为要发送消息的socket连接
            new Thread(new MessageProcessor(socket)).start();
        }
    }
}

class MessageProcessor implements Runnable{

    private Socket socket;

    public MessageProcessor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //服务端接收消息
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            //服务端把接收到的消息群发给所有其他人，管道流没有数据的时候会阻塞，如果客户端下线，socket连接就会关闭，这里会产生异常
            while ((msg=bufferedReader.readLine())!=null){
                System.out.println("服务端收到客户端"+socket.getPort()+"消息："+msg);
                //socket.getPort()是发送方的端口
                groupSent(msg,socket.getPort());
            }
        } catch (IOException e) {
            //当有用户下线的时候，会产生异常，在线用户就移除此用户
            Server5.onlineUser.remove(socket.getPort());
            System.out.println("用户"+socket.getPort()+"下线");
            System.out.println("当前在线人数: "+ Server5.onlineUser.size());
            e.printStackTrace();
        }
    }


    /**
     *  群发
     * @param msg
     * @param senderPort 发送方端口，也就是哪个客户端发送的
     * @throws IOException
     */
    public void groupSent(String msg,int senderPort) throws IOException {
        //获取所有在线用户的端口
        Set<Integer> ports = Server5.onlineUser.keySet();
        for (Integer port : ports) {
            //给全部的在线用户发送消息，除了发送方自己之外
            if(port!=senderPort){
                PrintStream printStream = new PrintStream(Server5.onlineUser.get(port).getOutputStream());
                printStream.println(senderPort+"发送消息说："+msg);
            }
        }
    }

}