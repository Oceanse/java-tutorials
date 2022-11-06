package com.demo.IO.io.stream_reader_writer.input;

import org.testng.annotations.Test;

import java.io.*;


/**
 * In Java, a stream is composed of bytes
 *
 * 流就像是连接内存(程序)和其他文件或者网络的管道，程序和文件的数据交换都要通过流的输入输出来完成
 * 当你要从文件或者网络读取数据的时候，一根管道(流)插到文件里面去，然后文件里面的数据就顺着管道流出来，管道的另一头(内存)就可以读取到数据并进行处理
 * 当你要往文件写入数据时，也是通过一根管道接入进文件，然后来自于管道另一头内存程序的数据写入到文件中
 *
 *
 * 按照流动的方向,以内存或者程序为基准分为输入input和输出output
 * 输入流 InputStream/Reader：把数据从文件或者网络连接总读取到内存中的输入流，程序只能从输入流中读取数据。
 * 输出流 OutputStream/Writer：程序只能往输出流中写入数据， 然后把输出流中的数据写到硬盘设备上
 *
 *          outputstream
 * 内存--------------------->硬盘
 *          writer
 *
 *        inputstream
 * 内存<--------------------硬盘
 *           reader
 *
 *
 * 根据数据的类型分为：
 * 字节流 InputStream/OutputStream：以字节为单位，读写数据的流。
 * 字符流 Reader/Writer：以字符为单位，读写数据的流。
 *
 *
 * 按照流是否直接与特定的地方(如磁盘、内存、设备等)相连分为：
 * 节点流：可以从或向一个特定的地方（节点）读写数据。 如：FileReader、FileWriter、FileInputStream、FileOutputStream
 * 处理流：对已存在的流的连接和封装，通过所封装的流的功能调用实现数据读写，以实现更为丰富的流数据处理，处理流的构造方法总是要带一个其他的流对象做参数，一个流对象经过其他流的多次包装，称为流的链接。
 * 如：BufferedInputStream， BufferedOutputStream， BufferedReader， BufferedWrite
 * 处理流优势：
 * 1 提高了输入输出效率
 * 2 处理流是对已知的节点流的封装，所以我们可以使用相同的处理流api来处理各种不同的节点流
 *
 *
 * 注意：
 *一切数据(文本、图片、音频、视频)底层都是以二进制数字存储，一个一个的字节，传输的时候也是如此。所以字节流可以传输任何数据。所以再操作流的时候要注意，
 * 无论使用什么样的流对象，底层传输始终是二进制数据。
 *
 *
 * FileInputStream:看这个名字就知道用于从文件中读取信息到内存中(obtains input bytes from a file in a file system)
 * FileOutputStream的构造方法
 * 1、 public FileOutputStream(File file)：根据File对象为参数创建对象；如果没有这个文件，会创建该文件;如果有这个文件，会清空这个文件的数据.
 * 2、 public FileOutputStream(String name)： 根据名称字符串为参数创建对象；如果没有这个文件，会创建该文件;如果有这个文件，会清空这个文件的数据.
 * 3、public FileOutputStream(File file, boolean append)；如果没有这个文件，会创建该文件;如果有这个文件，在这个文件后面追加数据.
 * 4、public FileOutputStream(String name, boolean append) ；如果没有这个文件，会创建该文件;如果有这个文件，在这个文件后面追加数据.
 *  推荐使用第2、4种构造方法当你创建一个流对象时，必须传入一个文件路径。该路径下如果没有该文件会抛出FileNotFoundException
 *
 * 常用api:
 *  1、 public void close() ：关闭此输入流并释放与此流相关联的任何系统资源。
 *  2、 public abstract int read()： 从输入流读取数据的下一个字节，读取到文件末尾，返回-1
 *  3、 public int read(byte[] b)： 每次读取b的长度个字节到数组中，返回读取到的有效字节个数，读取到末尾时，返回-1
 *
 *
 * 补充:
 * read()：Reads a byte of data from this input stream. This method blocks if no input is yet available.
 * 对于来自文件的输入流FileInputStream，当读到文件的末尾或者文件没有数据的时候会返回eof(end of file),事实上是-1,
 * 因此对于来自文件的输入流FileInputStream.read方法不会被阻塞
 * @author epanhai*/
public class FileInPutStreamDemo {

    @Test
    public void test1() throws IOException {
        //文件内容是"abc"， 文件采用utf-8编码，根据utf-8编码规则，这里一个字符占1个字节
        File file = new File("testResource/test.txt");

        // opening a connection to an actual file， 可以想象下：fis包含的就是文件内容的编码，用utf-8编码，那么流中的内容就是979899
        FileInputStream fis = new FileInputStream(file);

        int i1, i2, i3, i4;

        //read()简单理解就是从输入流管道中一个字节一个字节的读,读完之后指针向后移动一个字节
        i1 = fis.read();
        i2 = fis.read();
        i3 = fis.read();
        i4 = fis.read();//-1

        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);

        //因为读的是文本文件，因此可以返回每个字节的char(在0~65535范围内，java的char类型编码方式和utf-8兼容的话就不会有乱码)
        System.out.println((char) i1);
        System.out.println((char) i2);
        System.out.println((char) i3);
        System.out.println((char) i4);
        fis.close();//关闭流，释放内存资源
    }


    /**
     * 文件内容是"中", unicode编号是\u4e2d,文件采用utf-8编码， 根据utf-8编码规则，底层编码占3个字节
     * read()方法每次只读一个字节，如果文件里有多字节字符存在时候就会出现乱码
     * 所以read()方法不适合读取多字节字符的文本，否则会出现乱码
     * @throws IOException
     */
    @Test
    public void test1_2() throws IOException {
        FileInputStream fis = new FileInputStream("testResource/test.txt");
        int i1, i2, i3, i4;
        //read(): return the next byte of data, return -1 if the end of the file is reached，可以想象成指针一开始在第一个编码的前面
        //read()从输入流读完一个字节byte,返回读到的这个字节byte，然后指针会向后移动（个人想象：站在位置1，读取位置2的字节）
        //read()简单理解就是从输入流管道中一个字节一个字节的读
        //这里就相当于每次读取三分之一个字符，在解码的时候必然会出现乱码
        i1 = fis.read();
        i2 = fis.read();
        i3 = fis.read();
        i4 = fis.read();
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        System.out.println();

        //返回char，返回乱码
        System.out.println((char) i1);
        System.out.println((char) i2);
        System.out.println((char) i3);
        System.out.println((char) i4);
        fis.close();//关闭流，释放内存资源
    }



    /**
     * 文件内容：abc
     * read()方法每次只读一个字节，有如下缺点：
     * 1 如果文件里有多字节字符的时候就会出现乱码，所以文本文件优先考虑字符流
     * 2 单字节读取效率太低
     *
     * @throws IOException
     */
    @Test
    public void test1_3() throws IOException {
        File file = new File("testResource/test.txt");
        FileInputStream fis = new FileInputStream(file);
        int i;
        //read()返回下一个数据字节；如果已到达文件末尾，则返回 -1。
        while ((i = fis.read()) != -1) {
            System.out.print(i+":"+(char)i+"\n");
        }
        fis.close();//关闭流，释放内存资源
    }


    /**
     * 文件内容：中
     * 文件编码方式：utf-8
     * test.txt采用utf-8编码，中文字符"中国"占用6个字节， 若使用字节流read()一个一个字节读，也就是半个字符，那么会显示乱码(一个字符占用多个字节的时候，读取单个字节并立刻解码会出现乱码)
     * read()方法每次只读一个字节，有如下缺点：
     * 1 如果文件里有多字节字符的时候就会出现乱码，所以文本文件优先考虑字符流
     * 2 单字节读取效率太低,read()方法不确定是从硬盘读取还是从内核态读取数据
     *
     * @throws IOException
     */
    @Test
    public void test1_4() throws IOException {
        File file = new File("testResource/test.txt");
        FileInputStream fis = new FileInputStream(file);
        int i;
        //read()返回下一个数据字节；如果已到达文件末尾，则返回 -1。
        while ((i = fis.read()) != -1) {
            System.out.print(i+":"+(char)i+"\n");
        }
        fis.close();//关闭流，释放内存资源
    }




    /**
     * 文件内容:pom.xml, utf-8编码
     * jvm默认编码/解码方式：utf-8
     * 着三个字节通过字节输入流被读取到内存数组中， 然后转成String,就相当于把这三个字节按照utf-8解码
     * 编码解码都是utf-8,所以不会出现乱码
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {

        FileInputStream fis = new FileInputStream("testResource/test.txt");
        byte[] b = new byte[3];
        //这里每次最多 b.length 个字节的数据读入一个 byte 数组中(缓冲区),这里只能读取文件内容的三个字节
        //返回值是每次实际读取的字节数
        int len = fis.read(b);
        //byte数组转成String相当于解码，源文件采用utf-8编码，这里JVM采用utf-8解码, 所以不会出现乱码
        System.out.println(new String(b, 0, len, "utf-8"));
        //没有指明编码方式时候默认采用jvm默认编码方式：utf-8，和文件编码的方式一致，所以不会出现乱码
        System.out.println(new String(b, 0, len));
        fis.close();//关闭流，释放内存资源
    }




    /**
     * 文件内容是"中", unicode编号是\u4e2d, 文件是根据utf-8编码规则，底层编码占3个字节
     * 指定jvm编码方式：utf-8， 所以这里会出现乱码
     * @throws IOException
     */
    @Test
    public void test2_3() throws IOException {

        FileInputStream fis = new FileInputStream("testResource\\test.txt");
        byte[] b = new byte[3];
        //这里每次最多 b.length 个字节的数据读入一个 byte 数组中(缓冲区),这里只能读取文件内容的三个字节
        int len = fis.read(b);
        //byte数组转成String相当于解码，源文件采用utf-8编码，这里JVM采用utf-8解码, 所以不会出现乱码
        System.out.print(new String(b, 0, len,"utf-8"));
        fis.close();//关闭流，释放内存资源
    }


    /**
     * 文件内容是"中", unicode编号是\u4e2d, 文件是根据utf-8编码规则，底层编码占3个字节
     * 指定jvm编码方式：GB2312
     *所以这里会出现乱码
     * @throws IOException
     */
    @Test
    public void test2_4() throws IOException {

        FileInputStream fis = new FileInputStream("testResource\\test.txt");
        //输入流中将最多 b.length 个字节的数据读入一个 byte 数组中(缓冲区)
        byte[] b = new byte[3];
        //这里相当于读取文件的底层编码到byte数组
        int len = fis.read(b);
        //byte数组转成String相当于解码，指定jvm编码方式：GB2312，因为和文件编码的方式不一致，所以会出现乱码
        System.out.print(new String(b, 0, len,"GB2312"));
        fis.close();//关闭流，释放内存资源
    }


    /**
     * 文件内容：中国
     * 文件编码方式：utf-8
     *  "中国"占用6个字节
     * 采用字节输入流的read(byte[] b)方法读取文件的内容(底层编码)，如果数组的长度大于文件所有字符长度，那么只要文件编码和解码(数组转String)一致不会出现乱码；
     * 如果数组的长度小于文件所有字符长度，即使编码和解码一致，也会有可能出现乱码；
     * 比如一个数组长度为10，文件内容“中国人”是采用utf-8的中文字符，意味着会占用6个字节；那么数组一次只能容纳10个字节，那么对这个数组用utf-8解码的时候就不会出现乱码
     *
     * @throws IOException
     */
    @Test
    public void test2_5() throws IOException {

        //内容"中国"， utf-8编码，占用6字节
        File file = new File("testResource\\test.txt");
        FileInputStream fis = new FileInputStream(file);

        //byte数组长度大于文件所有字符占用的字节数,编码/解码一致时不会出现乱码
        byte[] b = new byte[10];
        //byte[] b = new byte[4]; //byte数组长度小于文件所有字符占用的字节数, 那么数组中可能只包含某个字符的一部分编码，那么即使编码/解码一致也可能会出现乱码
        int len;
        //len可以理解为数组当前的长度，返回实际读取的字节数，流位于文件末尾而没有可用的字节，则返回值 -1
        while ((len = fis.read(b)) != -1) {
            //读取数组中的有效长度
            System.out.print(new String(b, 0, len, "UTF-8"));
        }
        //关闭流，释放内存资源
        fis.close();
    }


    /**
     * 文件内容：中国(多字节字符)
     * 文件编码方式：utf-8
     *  "中国人"占用6个字节
     * 采用字节输入流的read(byte[] b)方法读取文件的内容(底层编码)，如果数组的长度大于文件所有字符长度，
     * 那么只要文件编码和解码(数组转String)一致不会出现乱码；
     * 如果数组的长度小于文件所有字符长度，即使编码和解码一致，也会有可能出现乱码；
     * 比如一个数组长度为5，文件内容“中国人”是采用utf-8的中文字符，意味着一个字符会占用3个字节；数组一次只能容纳5个字节，那么对这个数组用utf-8解码的时候就会出现乱码
     * 字节流处理文本文件容易出现乱码，优先考虑字符流
     *
     * @throws IOException
     */
    @Test
    public void test2_6() throws IOException {

        //内容"中国"， utf-8编码，占用6字节
        File file = new File("testResource\\test.txt");
        FileInputStream fis = new FileInputStream(file);

        //byte数组长度小于文件所有字符占用的字节数,那么数组中可能只包含某个字符的一部分编码，所以即使编码/解码一致时，也可能会出现乱码
        //这里会先读5字节，解码; 然后再读1字节，解码；因此会出现乱码
        byte[] b = new byte[5];
        int len;
        //len可以理解为数组当前的长度，返回实际读取的字节数，流位于文件末尾而没有可用的字节，则返回值 -1
        while ((len = fis.read(b)) != -1) {
            //读取数组中的有效长度
            System.out.print(new String(b, 0, len, "UTF-8"));
        }
        //关闭流，释放内存资源
        fis.close();
    }





    /**
     * available(): returns an estimate of the number of remaining bytes that can be read
     *
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        //文件内容是"abc"
        FileInputStream fis = new FileInputStream("testResource\\test.txt");

        //Returns an estimate of the number of remaining bytes that can be read
        //返回inputstream(文件中)剩余的可读的字节数，文件未读取时是3
        int available = fis.available();
        System.out.println(available);

        //读取第1个字节后，剩余的可读的字节数是2
        System.out.println();
        System.out.println(fis.read());
        System.out.println(fis.available());

        //读取第2个字节后，剩余的可读的字节数是1
        System.out.println();
        System.out.println(fis.read());
        System.out.println(fis.available());

        //读取第3个字节后，剩余的可读的字节数是0
        System.out.println();
        System.out.println(fis.read());
        System.out.println(fis.available());

        fis.close();//先打开的后关闭
    }



    /**
     * 文件内容是"abc"
     * available(): returns an estimate of the number of remaining bytes that can be read
     *
     * @throws IOException
     */
    @Test
    public void test3_2() throws IOException {
        FileInputStream fis = new FileInputStream("testResource\\test.txt");

        //Returns an estimate of the number of remaining bytes that can be read
        //返回inputstream(文件中)剩余的可读的字节数，文件还没开始读取时候就是文件占用的总字节数
        int totalSize = fis.available();

        //totalSize作为缓冲数组的大小
        byte[] b=new byte[totalSize];
        fis.read(b);//不用再循环，但是不合适读取过大的文件，文件过大，那么byte数组就很大，意味着读取文件时占用的内存特别大
        System.out.println(new String(b,"utf-8"));
        fis.close();//先打开的后关闭
    }


    /**
     * 文件内容是"abc"
     * skip(int n): Skips over and discards n bytes of data from the input stream.
     *
     * @throws IOException
     */
    @Test
    public void test4() throws IOException {

        FileInputStream fis = new FileInputStream("testResource/test.txt");

        //Returns an estimate of the number of remaining bytes that can be read
        //返回inputstream(文件中)剩余的可读的字节数，文件未读取时是3
        int available = fis.available();
        System.out.println(available);//3

        //读取第1个字节后，剩余的可读的字节数是2
        System.out.println();
        System.out.println((char)fis.read());
        System.out.println(fis.available());//2

        System.out.println();
        //跳过一个字节
        fis.skip(1);
        System.out.println(fis.available());//1
        System.out.println((char)fis.read());//c
        System.out.println(fis.available());//0

        fis.close();//先打开的后关闭
    }

}
