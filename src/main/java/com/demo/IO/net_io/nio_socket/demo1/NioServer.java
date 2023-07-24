package com.demo.IO.net_io.nio_socket.demo1;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * https://juejin.cn/post/6976496650453778445
 * Selector：翻译成选择器也好或者多路复用器
 * 在NIO编程中处理一个选择器，一个选择器可以监控很多通道。
 * 所以通过选择器，一个线程可以管理数百、数千、数万甚至更多的通道Channel(io流)，
 * 一个通道代表一条连接通路，通过选择器可以同时监控多个通道的IO（输入输出）状况，选择器和通道的关系是监控和被监控的关系。
 * 在极端情况下（数万个连接），只用一个线程就可以处理所有的通道，这样会大量地减少线程占用的内存空间一级线程之间上下文切换的开销。
 * 通道和选择器之间的关联通过register（注册）的方式完成。
 * <p>
 * IO事件: IO事件不是对通道的IO操作，而是通道处于某个IO操作的就绪状态，表示通道具备执行某个IO操作的条件。
 * 常见的IO事件类型常量定义在SelectionKey类中：
 * OP_ACCEPT：某个ServerSocketChannel服务器连接监听通道，在监听到一个新连接到来时，则会发生“接收就绪”（OP_ACCEPT）事件；可以理解为内核态建立了tcp连接，也就是连接已经存在于全连接队列，所以accept()方法会立刻返回传输数据的用的通信SocketChannel
 * OP_CONNECT：某个SocketChannel传输通道如果完成了和对端的三次握手过程，就会发生“连接就绪”（OP_CONNECT）事件；
 * OP_READ：一个SocketChannel通道有数据可读，就会发生“读就绪”（OP_READ）事件；
 * OP_WRITE: 一个SocketChannel通道等待数据写入，就会发生“写就绪”（OP_WRITE）事件。
 * <p>
 * Selector 能够检测多个注册的通道上是否有事件发生(注意：多个 Channel 以事件的方式可以注册到同一个Selector)，
 * 如果有事件发生， 便获取事件然后针对每个事件进行相应的处理。 这样就可以只用一个单线程去管理多个通道， 也就是管理多个连接和请求。
 */
public class NioServer {

    //类似于BIO中的ServerSocket
    ServerSocketChannel serverSocketChannel;

    @Test
    public void accept() throws Exception {

        System.out.println("服务端启动");

        // ServerSocketChannel可以理解为服务端的监听socket，用来监听客户端的连接请求
        //服务端用一个监听socket来服务所有的客户端连接，监听到客户端A接入请求时accept会产生一个新的传输socket用于和客户端A通信
        //然后又有一个客户端B请求连接，监听socket监听到连接后又产生一个一个新的传输socket用于和客户端B通信
        //所以服务端会有一个监听socket和多个传输socket
        serverSocketChannel = ServerSocketChannel.open();

        // 监听5555端口
        serverSocketChannel.bind(new InetSocketAddress(55551));

        // 设置为非阻塞模式(默认都是阻塞模式的),注册到 Selector 的 Channel 必须要支持非阻塞模式
        serverSocketChannel.configureBlocking(false);

        // 获取选择器实例
        Selector selector = Selector.open();

        // Channel 以事件的方式可以注册到Selector(个人理解通道和事件绑定在一起注册到了selector，个人称之为事件通道实例)
        //第二个参数用于表明Selector对指定通道的感兴趣的事件进行监听，SelectionKey.OP_ACCEPT表示接受 TCP 连接事件
        //如果选择器要监控通道的多种事件，可以用“|”运算符来实现:int key=SelectionKey.OP_READ | SelectionKey.OP_WRITE
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 轮询,通过select()方法，选择器可以不断地监听轮询通道中就绪状态的事件及绑定的通道(个人称之为事件通道实例)
        //一个IO事件发生/事件通道实例（就绪状态达成）后，如果之前在选择器中注册过，就会被选择器选中，也就是会学则已经注册的、并且处于就绪的IO事件/事件通道实例
        //selector.select()>0时候可以简单理解为内核告诉上层应用，有socketChannel已经准备就绪了，也就是可读/可写/可连接了
        //select()个人理解所包含的逻辑护理：首先把所有注册的(需要监控的)文件描述符集合(对应各种socketChannel)从应用空间空间拷贝到内核空间，然后内核会对监控的集合进行遍历，
        //如果有事件发生，就将对应的fd或者socketChannel进行标记，然后把整个集合返回给用户空间，也就是内核只会把已经就绪的fd进行标记，但是不会告诉你具体哪个准备就绪，标记好后把整个fd集合拷贝到
        //用户空间此时selector.select()返回，用户进程需要亲自遍历返回的集合中有哪些fd准备就绪了，selector.selectedKeys()应该就是从前面返回的整个集合选出已经准备就绪的fd的集合
        //select()每调用一次，就会把注册到selector的fd集合重新从用户空间拷贝到内核空间,fd很多时开销就很大,select支持的文件描述符数量太小，默认最大支持1024个
        //select()应该包含了把fd集合重新从用户空间拷贝到内核空间和内核空间拷贝到用户空间
        while (selector.select() > 0) {
            //当客户端连接服务端或者给服务端发消息的时候就是OP_ACCEPT和OP_READ事件就绪
            System.out.println("开始新一轮事件处理");

            //当有读或写等任何注册的事件发生时，可以从 Selector 中获得相应的 SelectionKey，
            //同时从 SelectionKey 中可以找到发生的事件和该事件所发生的具体的 SelectableChannel，以获得客户端发送过来的数据。
            //selector.selectedKeys()获取注册的且就绪的事件(事件通道实例)集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            //遍历所有事件(事件通道实例)
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            //根据具体的IO事件类型执行对应的业务操作
            //hasNext就是如果还有未处理的就绪事件(事件通道实例)
            while (iterator.hasNext()) {
                // SelectionKey就是具体的IO事件
                SelectionKey selectionKey = iterator.next();
                // 如果有连接事件，说明有客户端尝试通过此端口连接到服务端
                if (selectionKey.isAcceptable()) {
                    // 进行可连接的处理逻辑
                    handlerAccept(selectionKey);
                    //传输通道连接成功,也就是客户端和服务端完成了三次握手
                } else if (selectionKey.isConnectable()) {

                } else if (selectionKey.isReadable()) {
                    handlerRead(selectionKey);
                }
                // 处理完成后，需要将选择键从SelectionKey集合中移除，以防止下一次循环时被重复处理,这里并没有从注册表中移除
                iterator.remove();
            }

        }
        serverSocketChannel.close();
    }

    private void handlerAccept(SelectionKey selectionKey) throws IOException {
        System.out.println("处理连接请求");
        //通过SelectionKey个人理解为事件通道实例，可以通过它获得通道的IO事件类型以及发生IO事件所在的通道以及选择器实例，ServerSocketChannel是服务端用来监听连接请求的SocketChannel
        ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
       //其实OP_ACCEPT事件就绪时通过selectionKey.channel()获取的通道或者监听socket就是成员变量serverSocketChannel，因为监听socket只能有一个
        System.out.println(ssc ==serverSocketChannel);
        //ServerSocketChannel设置成了非阻塞模式，即使SelectionKey.OP_ACCEPT事件没有就绪，也会立刻返回null
        //由于SelectionKey.OP_ACCEPT事件可以理解为内核态建立了tcp连接，也就是连接已经存在于全连接队列，所以accept()方法会立刻返回传输数据的用的通信SocketChannel
        SocketChannel socketChannel = ssc.accept();
        System.out.println("客户端"+socketChannel.getRemoteAddress()+"发起连接请求");
        // 设置为非阻塞模式
        socketChannel.configureBlocking(false);
        //把新产生的SocketChannel通道注册到选择器上，并监听可读取事件
        socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
    }

    private void handlerRead(SelectionKey selectionKey) throws Exception {
        System.out.println("读取数据");
        // 获取选择键上的SocketChannel
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        // 设置为非阻塞模式
        //如果 socketChannel.configureBlocking(true). 那么当前socketChannel.read就是阻塞式的，也就是通道中没有数据的时候socketChannel.read会阻塞
        //如果 socketChannel.configureBlocking(false). 那么当前accept就是非阻塞式的，如果通道中没有数据，socketChannel.read会立刻返回0
        socketChannel.configureBlocking(false);
        // 数据读取到缓冲区中
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int len;
        //因为通道设置成了非阻塞，即使通道中没有数据，socketChannel.read也会立刻返回-1, 从而不会阻塞当前线程，
        // 但是由于只有selectionKey.isReadable()时候，才会执行handlerRead, 所以正常情况下通道中应该会有数据
        //客户端发送完一次数据，这里接受后就重新回到selector.select方法，客户端发送第二次消息，那么selector就会监听到，selector.select方法
        //机会返回，然后应用端遍历处理
        while ((len = socketChannel.read(byteBuffer)) > 0) {
            //缓冲区切换成读模式
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(), 0, len));
            //System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit));
            //缓冲区切换成写模式
            byteBuffer.clear();
        }
    }
}
