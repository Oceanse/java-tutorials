package com.demo.IO.net_io.bio_socket.demo1;

import org.testng.annotations.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * BIO： 同步阻塞式的I/O通信，服务器实现模式为一个连接一个线程，即客户端有连接请求时服务器端就需要启动
 * 一个线程进行处理
 * <p>
 * 客户端只发送消息，服务端端只接收消息
 *
 * @author epanhai
 */
public class SocketClient {


    /**
     * 客户端连接服务端后可以解除服务端"serverSocket.accept()"的监听连接阻塞
     * 客户端发送数据后可以解除服务端"socket.getInputStream()"的接收数据流阻塞
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public static void connectToServer() throws IOException, InterruptedException {
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 10085);

        //client socket
        Socket socket = new Socket();

        //客户端也可以绑定端口，不绑定的话系统会随机分配
        socket.bind(new InetSocketAddress(55534));

        //客户端发起连接，那么服务端的accept方法就会收到连接请求，解除阻塞
        //linux下通过netstat -napt可以观察到tcp连接的socket四元组
        socket.connect(serverAddress, 1000);

        if (socket.isConnected()) {
            System.out.println("客户端成功连接上服务端");
            //这里连接上server之后如果马上通过socket.close()关闭socket连接，那么服务端socket也会关闭
            //模拟连接上服务端后却不发送数据，并且不关闭socket连接，那么服务端inputStream.read(bytes)将会一直处于阻塞状态
            //sleep就是为了保持socket连接不会断掉，然后观察服务端的阻塞状态，在此期间，服务端将会在循环读取数据流的地方 "inputStream.read(bytes) "阻塞
            //如果不sleep，当前方法执行完就退出，socket也会关闭,服务端socket也会关闭
            Thread.sleep(10000);
        }
    }


    /**
     * 客户端连接服务端后可以解除服务端"serverSocket.accept()"的监听连接阻塞
     * 客户端发送数据后可以解除服务端"socket.getInputStream()"的接收数据流阻塞
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public static void connectToServer2() throws IOException, InterruptedException {

        //client socket: 这一步就会包含连接服务端的动作,linux下通过netstat -napt可以观察到tcp连接的socket四元组
       // 客户端发起连接，那么服务端的accept方法就会收到连接请求，解除阻塞
        Socket socket = new Socket("localhost",10085);

        if (socket.isConnected()) {
            System.out.println("客户端成功连接上服务端");
            //这里连接上server之后如果马上通过socket.close()关闭socket连接，那么服务端socket也会关闭
            //模拟连接上服务端后却不发送数据，并且不关闭socket连接，那么服务端inputStream.read(bytes)将会一直处于阻塞状态
            //sleep就是为了保持socket连接不会断掉，然后观察服务端的阻塞状态，在此期间，服务端将会在循环读取数据流的地方 "inputStream.read(bytes) "阻塞
            //如果不sleep，当前方法执行完就退出，socket也会关闭,服务端socket也会关闭
            Thread.sleep(10000);
        }
    }



    /**
     * 客户端构造socket方式1：
     * 客户端连接服务端后可以解除服务端"serverSocket.accept()"的监听连接阻塞
     * 客户端发送数据后可以解除服务端"socket.getInputStream()"的接收数据流阻塞
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public static void connectAndSendMessage() throws IOException, InterruptedException {
        InetSocketAddress serverAddress = new InetSocketAddress("localhost", 10085);

        //client socket
        Socket socket = new Socket();

        //客户端发起连接，那么服务端的accept方法就会收到连接请求，解除阻塞
        socket.connect(serverAddress, 1000);

        if (socket.isConnected()) {
            System.out.println("客户端成功连接上服务端");
        }

        // 建立连接后获得输出流,向server端发送消息,服务端流管道就产生数据，inputStream.read(bytes)就会解除阻塞
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("This message comes from client".getBytes("UTF-8"));

        //如果不sleep，当前方法执行完就退出，socket也会关闭,服务端socket也会关闭
        //sleep就是为了保持socket连接不会断掉，然后观察服务端的阻塞状态
        //在此期间，服务端将会在循环读取数据流的地方 "inputStream.read(bytes) "阻塞
        Thread.sleep(10000);
    }


    /**
     * 客户端构造socket方式2：
     * 客户端连接服务端后可以解除服务端"serverSocket.accept()"的监听连接阻塞
     * 客户端发送数据后可以解除服务端"socket.getInputStream()"的接收数据流阻塞
     *
     * @throws IOException
     */
    @Test
    public static void connectAndSendMessage2() throws IOException, InterruptedException {
        //要连接的服务端IP地址和端口,与服务端建立连接,这一步就会包含连接的动作
        Socket socket = new Socket("127.0.0.1", 10085);

        // 建立连接后获得输出流,向server端发送消息
        OutputStream outputStream = socket.getOutputStream();
        new BufferedOutputStream(outputStream).write("This message comes from client".getBytes("UTF-8"));


        //如果不sleep，当前方法执行完就退出，socket也会关闭,服务端socket也会关闭
        //sleep就是为了保持socket连接不会断掉，然后观察服务端的阻塞状态
        //在此期间，服务端将会在循环读取数据流的地方 "inputStream.read(bytes) "阻塞
        Thread.sleep(10000);
    }


    /**
     * 客户端通过Scanner扫描键盘输入给服务端连续发送多次消息
     * @throws IOException
     */
    public static void connectAndSendMessage3() throws IOException {
        //要连接的服务端IP地址和端口,与服务端建立连接
        Socket socket = new Socket("127.0.0.1", 10085);
        OutputStream outputStream = socket.getOutputStream();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("客户端说： ");
                //这里scanner.nextLine()是阻塞的，scanner中有数据的时候才会解除阻塞
                //nextLine()方法返回的是Enter键之前的所有字符，它是可以得到带空格的字符串的
                String message = scanner.nextLine();
                //BufferedWriter的write(String str)一定要输出换行标识，并且调用flush()刷新缓冲区(强制写入socket通道)，否则会造成接收端bufferedReader.readLine()一直阻塞
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
    }


    public static void connectAndSendMessage4() throws IOException {
        //要连接的服务端IP地址和端口,与服务端建立连接
        Socket socket = new Socket("127.0.0.1", 10085);
        OutputStream outputStream = socket.getOutputStream();
        try (PrintStream printStream = new PrintStream(outputStream)) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("客户端说： ");
                //这里scanner.nextLine()是阻塞的，scanner中有数据的时候才会解除阻塞
                //nextLine()方法返回的是Enter键之前的所有字符，它是可以得到带空格的字符串的
                String message = scanner.nextLine();
                //通过printStream向socket发送数据，println会输出换行符
                printStream.println(message);
                printStream.flush();
            }
        }
    }


    public static void connectAndSendMessage5() throws IOException {
        //要连接的服务端IP地址和端口,与服务端建立连接
        Socket socket = new Socket("127.0.0.1", 10085);
        OutputStream outputStream = socket.getOutputStream();
        try (PrintStream printStream = new PrintStream(outputStream)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("客户端说： ");
            //nextLine()方法返回的是Enter键之前的所有字符，它是可以得到带空格的字符串的
            String message = scanner.nextLine();
            //这里print没有写入换行符，所以服务端的readLine()方法会因为读取不到换行符而一直等待阻塞
            //但是当sleep 10s后，也就是客户端程序结束时，发送的信息会被服务端读取到
            printStream.print(message);
            printStream.flush();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        connectAndSendMessage5();
    }

}

