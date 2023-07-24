package com.demo.IO.nio.buffer;

import org.testng.annotations.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;


/**
 * 在NIO中并不是以流的方式来处理数据的，而是以buffer缓冲区和Channel管道配合使用来处理数据
 * 要时刻记住：Channel不与数据打交道，它只负责运输数据,与数据打交道的是Buffer缓冲区
 * 数据<--->buffer<--->Channel
 * <p>
 * Buffer的三个核心属性：Capacity(容量，总数量)， position(指针)， limit(可读写数量)
 *
 * Capacity(容量): 缓冲区能够容纳的数据元素的最大数量。容量在缓冲区创建时被设定，并且永远不能被改变。
 * <p>
 * <p>
 * position：可以理解为指针或者为索引，指向下一个要被读或写的元素的索引，
 * 新创建一个Buffer对象时，position初始值为0。
 * 位置会自动由相应的get( )和put( )函数更新。
 * 写数据： position初始值为0,当你写数据到Buffer中时，position会往后移动到下一个可写的位置, position最大可为capacity–1.
 * 个人测试发现：position最大有效值为capacity–1，当数据写满缓冲区时候，position=capacity，只是此时的position处不能写入数据
 * 读数据：position初始值为0，从position处读取数据后 ,position向前移动到下一个可读的位置，position最大可为capacity–1.
 * 个人测试发现：position最大有效值为capacity–1，当缓冲区数据读完后，position=capacity，只是此时的position处不能读取数据
 * 当Buffer从写模式切换到读模式，position会被重置为0,表示从0开始读取数据， 从Buffer的position处读取数据后，position移动到下一个可读的位置。
 * <p>
 * <p>
 * Limit：缓冲区的第一个不能被读或写的元素，或者说可供读写的最大位置/边界位置或者缓冲区可操作数据的大小；
 * 写模式下：limit=capacity， limit表示你最多能往Buffer里写多少数据。
 * 读模式下：limit表示你最多能从Buffer读取多少数据，也就是之前写入的数据量
 * 当切换Buffer到读模式时，limit会被设置成写模式下的position值，写模式下的position值就是缓冲区元素的数量
 * 换句话说，你能读到之前写入的所有数据（limit被设置成已写数据的数量，这个值在写模式下就是position）
 * <p>
 * 在对Buffer进行读/写操作前，我们可以调用Buffer类提供的一些辅助方法来正确设置 position 和 limit 的值，主要有如下几个：
 * <p>
 * filp():每当要从缓存区的时候读取数据时，就调用filp()“切换成读模式”。
 * 此时：position变成0,limit变成了position的位置(上次写完的位置)；
 * limit是限制读到哪里，而position是从哪里读
 * <p>
 * clear():每当要向缓存区写数据时，就调用clear“切换成写模式”。
 * 之后，回到初始状态，即 limit 等于 capacity，position重置0。
 * 通常在重新对Buffer进行写入操作前调用。
 * 注意：clear并没有清除数据
 *
 * mark/reset: 标记当前位置,通过mark方法标记当前一个特点的position,之后可以通过reset方法恢复到这个position
 *
 * <p>
 * rewind(): 仅仅将 position置0。一般是在重新读取Buffer数据前调用;
 * 比如要读取同一个Buffer的数据写入多个通道时会用到。
 *
 * 0=<mark<=position<=limit<=capacity
 */
public class BufferDemo {

    /**
     * 创建缓冲区
     * 缓冲区创建时默认是写模式的，也就是position=0，limit=capacity
     */
    @Test
    public void test() {
        // 创建一个1024字节的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //capacity=1024
        System.out.println("capacity--->" + byteBuffer.capacity());
        //position=0
        System.out.println("position--->" + byteBuffer.position());
        //limit=1024
        System.out.println("limit--->" + byteBuffer.limit());
    }

    @Test
    public void test2() {
        // 创建一个1024字节的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 看一下初始时3个核心变量的值
        //capacity=1024
        System.out.println("初始时-->capacity--->" + byteBuffer.capacity());
        //position=0
        System.out.println("初始时-->position--->" + byteBuffer.position());
        //limit=1024
        System.out.println("初始时-->limit--->" + byteBuffer.limit());
        System.out.println("--------------------------------------");


        // 添加一些数据到缓冲区中
        String s = "Java3y";
        byteBuffer.put(s.getBytes());


        // 看一下初始时3个核心变量的值
        //capacity=1024
        System.out.println("put完之后-->capacity--->" + byteBuffer.capacity());
        //position=6
        System.out.println("put完之后-->position--->" + byteBuffer.position());
        //limit=1024
        System.out.println("put完之后-->limit--->" + byteBuffer.limit());
        //[pos=6 lim=1024 cap=1024]
        System.out.println("put完之后-->mark--->" + byteBuffer.mark());
        System.out.println("--------------------------------------");


        //filp()为“切换成读模式”;每当要从缓存区的时候读取数据时，就调用filp()“切换成读模式”。
        // 设置 limit 为 position 的值，然后 position 置为0。
        byteBuffer.flip();
        // 看一下初始时4个核心变量的值
        //capacity=1024
        System.out.println("flip完之后-->capacity--->" + byteBuffer.capacity());
        //position=0
        System.out.println("flip完之后-->position--->" + byteBuffer.position());
        //limit=6
        System.out.println("flip完之后-->limit--->" + byteBuffer.limit());
        //[pos=0 lim=6 cap=1024]
        System.out.println("flip完之后-->mark--->" + byteBuffer.mark());
        System.out.println("--------------------------------------");


        //clear():每当要向缓存区写数据时，就调用clear“切换成写模式”。
        byteBuffer.clear();
        // 看一下初始时4个核心变量的值
        //capacity=1024
        System.out.println("clear完之后-->capacity--->" + byteBuffer.capacity());
        //position=0
        System.out.println("clear完之后-->position--->" + byteBuffer.position());
        //limit=1024
        System.out.println("clear完之后-->limit--->" + byteBuffer.limit());
        //[pos=0 lim=1024 cap=1024]
        System.out.println("clear完之后-->mark--->" + byteBuffer.mark());
    }







    /**
     * 向buffer添加数据
     * clear():每当要向缓存区写数据时，就调用clear“切换成写模式”。
     * 写模式：the position is set to zero, the limit is set to the capacity
     * 注意：缓冲区创建时默认是写模式的
     */
    @Test
    public void writeToBuffer() {
        // 创建一个1024字节的缓冲区，默认就是写模式
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //添加3个字节数据
        byteBuffer.put("nio".getBytes(StandardCharsets.UTF_8));

        //capacity=1024
        System.out.println("capacity--->" + byteBuffer.capacity());
        //position=3，应该是表示即将进行读写的字节的位置
        System.out.println("position--->" + byteBuffer.position());
        //limit=1024
        System.out.println("limit--->" + byteBuffer.limit());

    }


    /**
     * 缓冲区创建时默认是写模式的
     * 向buffer添加数据
     */
    @Test
    public void writeToBuffer2() {
        // 创建一个3字节的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);

        // 看一下初始时3个核心变量的值
        //capacity=3
        System.out.println("初始时-->capacity--->" + byteBuffer.capacity());
        //position=0
        System.out.println("初始时-->position--->" + byteBuffer.position());
        //limit=3
        System.out.println("初始时-->limit--->" + byteBuffer.limit());
        System.out.println("--------------------------------------");


        // 添加一些数据到缓冲区中
        String s = "a";
        byteBuffer.put(s.getBytes());
        //capacity=3
        System.out.println("a-->capacity--->" + byteBuffer.capacity());
        //position=1
        System.out.println("a-->position--->" + byteBuffer.position());
        //limit=3
        System.out.println("a-->limit--->" + byteBuffer.limit());
        System.out.println("--------------------------------------");

        // 添加一些数据到缓冲区中
        String s2 = "b";
        byteBuffer.put(s2.getBytes());
        //capacity=3
        System.out.println("b-->capacity--->" + byteBuffer.capacity());
        //position=2
        System.out.println("b-->position--->" + byteBuffer.position());
        //limit=3
        System.out.println("b-->limit--->" + byteBuffer.limit());
        System.out.println("--------------------------------------");


        // 添加一些数据到缓冲区中
        String s3 = "c";
        byteBuffer.put(s3.getBytes());
        System.out.println("c-->capacity--->" + byteBuffer.capacity());
        //position=3, 数据加满缓冲区后，position=capacity=limit, 此时的position实际上是不可写的
        System.out.println("c--->position--->" + byteBuffer.position());
        System.out.println("c-->limit--->" + byteBuffer.limit());
        System.out.println("--------------------------------------");
    }


    /**
     * 从buffer读取数据
     * 调用filp()切换成“读模式”，position重置为0，limit是缓冲区可读字节的边界索引,但不包括limit，可读范围[0,limit)
     * byteBuffer.get(byte[] dst), 每次从buffer读取单个字节，position增1
     * 读取的过程limit不会变，表示读边界位置或者缓冲区可读取数据的大小；
     */
    @Test
    public void readFromBuffer() {
        // 创建一个1024字节的缓冲区，默认就是写模式
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //添加3个字节数据
        byteBuffer.put("nio".getBytes(StandardCharsets.UTF_8));

        //capacity=1024
        System.out.println("before flip-->capacity--->" + byteBuffer.capacity());
        //position=3
        System.out.println("before flip-->position--->" + byteBuffer.position());
        //limit=1024
        System.out.println("before flip-->limit--->" + byteBuffer.limit());

        //每当要从缓存区的时候读取数据时，就调用filp()“切换成读模式”
        byteBuffer.flip();

        //capacity=1024
        System.out.println("after flip-->capacity--->" + byteBuffer.capacity());
        //position=0
        System.out.println("after flip-->position--->" + byteBuffer.position());
        //limit=3，上次写模式下的position值； 读模式下，limit就是缓冲区可读字节的边界索引
        System.out.println("after flip-->limit--->" + byteBuffer.limit());
    }


    /**
     * 从buffer读取数据
     * 调用filp()“切换成读模式”,
     * byteBuffer.get(), 每次从buffer读取单个字节，position增1
     * 读取的过程limit不会变,表示读边界位置或者缓冲区可读取数据的大小；
     */
    @Test
    public void readFromBuffer2() {
        // 创建一个1024字节的缓冲区，默认就是写模式
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);

        //添加3个字节数据
        byteBuffer.put("nio".getBytes(StandardCharsets.UTF_8));

        //每当要从缓存区的时候读取数据时，就调用filp()“切换成读模式”
        byteBuffer.flip();

        System.out.println("position初始值：" + byteBuffer.position());
        System.out.println("limit初始值：" + byteBuffer.limit());
        System.out.println();

        char c1 = (char) byteBuffer.get();
        System.out.println("c1=" + c1);
        //position是即将读取的下个字节的索引，读完第一个字节后position=1，说明下个即将读取的字节索引是1
        System.out.println("读取第1个字节后position：" + byteBuffer.position());
        System.out.println("读取第1个字节后limit：" + byteBuffer.limit());
        System.out.println();

        char c2 = (char) byteBuffer.get();
        System.out.println("c2=" + c2);
        System.out.println("读取第2个字节后position：" + byteBuffer.position());
        System.out.println("读取第1个字节后limit：" + byteBuffer.limit());
        System.out.println();

        char c3 = (char) byteBuffer.get();
        System.out.println("c3=" + c3);
        System.out.println("读取第3个字节后position：" + byteBuffer.position());
        System.out.println("读取第1个字节后limit：" + byteBuffer.limit());
    }

    /**
     * 从buffer读取数据
     * 调用filp()“切换成读模式”，position重置为0
     * byteBuffer.get(byte[] dst), 每次从buffer读取单个字节，position增1
     * 读取的过程limit不会变，表示读边界位置或者缓冲区可读取数据的大小；
     */
    @Test
    public void readFromBuffer3() {
        // 创建一个1024字节的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //添加3个字节数据
        byteBuffer.put("nio".getBytes(StandardCharsets.UTF_8));

        //每当要从缓存区的时候读取数据时，就调用filp()“切换成读模式”, position重置为0
        byteBuffer.flip();

        System.out.println("position初始值：" + byteBuffer.position());
        System.out.println("limit初始值：" + byteBuffer.limit());
        System.out.println();

        //读取缓冲区的数据到数组,长度为限制位置即可,limit就是缓冲区可读写的字节数量
        byte[] arrayCache = new byte[byteBuffer.limit()];
        //相当于一次性读完缓冲区的所有数据
        byteBuffer.get(arrayCache);
        System.out.println(new String(arrayCache));

        //buffer读取完后，position=limit
        System.out.println("读取字节后position：" + byteBuffer.position());
        System.out.println("读取字节后limit：" + byteBuffer.limit());
    }





    /**
     * 先写后读
     */
    @Test
    public void bufferReadAndWrite() {
        // 创建一个1024字节的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(6);

        //关于缓冲区hasRemaining： whether there are any elements between the current position and the limit
        //等价于limit - position是否等于0
        //对于写来说，就是还剩多少空间可写
        byteBuffer.put("nio".getBytes(StandardCharsets.UTF_8));
        System.out.println("写入3个字节后是否还有剩余空间: " + byteBuffer.hasRemaining());//true
        System.out.println("写入3个字节后的剩余空间: " + byteBuffer.remaining());//3

        byteBuffer.put("phy".getBytes(StandardCharsets.UTF_8));
        System.out.println("写入6个字节后是否还有剩余空间: " + byteBuffer.hasRemaining());//false
        System.out.println("写入6个字节后的剩余空间: " + byteBuffer.remaining());//0

        //filp()“切换成读模式”, position重置为0
        //对于读来说，就是还剩多少空间可读，等价于limit
        System.out.println();
        byteBuffer.flip();
        System.out.println("切换成读模式后是否还有剩余空间: " + byteBuffer.hasRemaining());//true
        System.out.println("切换成读模式后后的剩余空间: " + byteBuffer.remaining());//6

        //从buffer读取数据
        System.out.println();
        System.out.print((char) byteBuffer.get());
        System.out.print((char) byteBuffer.get());
        System.out.print((char) byteBuffer.get());
        System.out.println("读取3个字节后是否还有剩余空间: " + byteBuffer.hasRemaining());//true
        System.out.println("读取3个字节后的剩余空间: " + byteBuffer.remaining());//3
        System.out.print((char) byteBuffer.get());
        System.out.print((char) byteBuffer.get());
        System.out.print((char) byteBuffer.get());
        System.out.println("读取6个字节后是否还有剩余空间: " + byteBuffer.hasRemaining());//false
        System.out.println("读取6个字节后的剩余空间: " + byteBuffer.remaining());//0
    }


    /**
     * 先写后读
     */
    @Test
    public void bufferReadAndWrite2() {
        // 创建一个1024字节的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("God bless you".getBytes(StandardCharsets.UTF_8));

        byteBuffer.flip();
        //等价于while(position!=limit)，是否还有未读元素
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
    }



    /**
     * 先写后读
     */
    @Test
    public void bufferReadAndWrite3() {
        // 创建一个1024字节的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("God bless you".getBytes(StandardCharsets.UTF_8));

        byteBuffer.flip();
        //等价于while(position!=limit)，是否还有未读元素
        System.out.println("第一次读：");
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }

        System.out.println();
        System.out.println("第二次读：");
        //rewind(): 仅仅将 position置0,一般是在重新读取Buffer数据前调用,如果不调用rewind,那么第二次读不到任何数据
        byteBuffer.rewind();
        while (byteBuffer.hasRemaining()) {
            System.out.print((char) byteBuffer.get());
        }
    }


    /**
     * @throws InterruptedException
     */
    public static void markAndReset() throws InterruptedException {
        //初始化一个长度为48的buffer
        ByteBuffer buffer = ByteBuffer.allocate(6);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数据：");
        while (true) {
            System.out.println("buffer.mark(): " + buffer.mark());
            try {
                //阻塞式
                String numString = scanner.nextLine();
                byte num = Byte.parseByte(numString);
                //put后缓冲区的position会增1
                buffer.put(num);
            } catch (Exception e) {
                System.out.println("您输入的数据有误，重新输入!");
                //position恢复到之前mark的位置
                buffer.reset();
                continue;
            }

            //ByteBuffer写满就推出
            if (!buffer.hasRemaining()) {
                System.out.println("ByteBuffer已经写满");
                //切换到读模式
                buffer.flip();
                byte[] arrayCache = new byte[buffer.limit()];
                buffer.get(arrayCache);
                System.out.println("缓冲区内容：" + Arrays.toString(arrayCache));
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        markAndReset();
    }
}

