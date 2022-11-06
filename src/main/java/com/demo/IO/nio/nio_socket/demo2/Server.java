package com.demo.IO.nio.nio_socket.demo2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author epanhai
 * 服务端三属性：选择器，通道，端口
 * 服务端功能：接收一个客户端发送的消息，并转发给其它所有的客户端
 */
public class Server {

    /**
     * 通道
     */
    private ServerSocketChannel serverSocketChannel;

    /**
     * 选择器
     */
    private Selector selector;

    /**
     * 监听端口
     */
    private int port;

    public Server() throws IOException {
        this.serverSocketChannel = ServerSocketChannel.open();
        this.selector = Selector.open();
        this.port = 55531;
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel.configureBlocking(false);
    }

    public static void main(String[] args) throws IOException {
        Server server=new Server();

        server.listen();
    }

    /**
     * 服务端接收一个客户端发送的消息，并转发给其它所有的客户端
     * @throws IOException
     */
    private void listen() throws IOException {
        while (selector.select()>0){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    //这个serverSocketChannel和成员变量serverSocketChannel指向的是同一个ServerSocketChannel对象
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
                    //socketChannels是传输socket
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    //SelectionKey.OP_READ是“读就绪”事件，表示通道中有数据可读，
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    //"读就绪"事件发生时，也就是通道中有数据可时
                }else if(selectionKey.isReadable()){
                    try {
                        //selectionKey可以理解为绑定了事件及对应的通道，这里通过selectionKey获取相应的通道(传输socket)
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        socketChannel.configureBlocking(false);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //从通道读取数据写到缓冲区中，此期间若客户端关闭(下线)会产生异常
                        socketChannel.read(byteBuffer);
                        //缓冲区切换为读模式
                        byteBuffer.flip();
                        String Message = new String(byteBuffer.array(), 0, byteBuffer.limit());
                        //服务端向其它客户端转发消息
                        groupSend(Message, socketChannel);
                    }catch (Exception e){
                        //从selector被移除
                        selectionKey.cancel();
                        //关闭channel
                        serverSocketChannel.close();
                        e.printStackTrace();
                    }

                }
                //处理完毕，移除当前事件
                iterator.remove();
            }
        }
    }

    /**
     * 群发：发给所有的客户端，除了发送方之外
     * @param message
     * @param socketChannel
     * @throws IOException
     */
    private void groupSend(String message, SocketChannel socketChannel) throws IOException {
        //通过选择其获取所有的注册且就绪的SelectionKey(事件通道实例)
        Set<SelectionKey> selectionKeys = selector.keys();
        for (SelectionKey selectionKey : selectionKeys) {
            //通过SelectionKey获取对应的通道(传输socket)
            SocketChannel channel = (SocketChannel)selectionKey.channel();
            //排除发送方自己
            if(channel!=socketChannel){
                ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                byteBuffer.put(message.getBytes(StandardCharsets.UTF_8));
                byteBuffer.flip();
                //发送消息
                socketChannel.write(byteBuffer);
            }
        }


    }
}
