package com.demo.IO.io.stream_reader_writer.input;

import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;

/**
 * FileReader(字符输入流)： 读取存储在用户计算机上的文本文件（或原始数据缓冲区）的内容到内存中
 * <p>
 * 继承关系： FileReader----->InputStreamReader(字节->字符转换流)------->Reader
 * <p>
 * FileReader
 * 内存<-------------------文件
 * <p>
 * 构造函数：
 * FileReader(File file): Creates a new FileReader, given the File to read from.
 * FileReader(String fileName): Creates a new FileReader, given the name of the file to read from.
 * <p>
 * 常用方法：
 * public int read() throws IOException ： 读取单个字符，返回一个int型变量代表读取到的字符
 * public int read(char [] c)： 读取字符到c数组，返回读取到字符的个数
 *
 * 实际上， FileReader在类内部实现过程中也是利用了InputStreamReader完成字节流到字符流的转化，只不过转化时采用的字符集为系统默认的字符集。
 */
public class FileReaderDemo {


    /**
     * Reader 类是 Java 的 I/O 中读字符的父类，而 InputStream 类是读字节的父类，InputStreamReader 类就是关联字节到字符的桥梁，
     * 它负责在 I/O 过程中处理读取字节到字符的转换，而具体字节到字符的解码实现它由 StreamDecoder 去实现，
     * 在 StreamDecoder 解码过程中必须由用户指定 Charset 编码格式。值得注意的是如果你没有指定 Charset，将使用本地环境中的默认字符集
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {

        System.out.println(Charset.defaultCharset().name());

        File file = new File("testResource\\test.txt");
        //文件采用utf-8编码
        //通过FileReader发现，这里实际上将文件关联的FileInputStream按照默认的字符集(utf-8)转换成InputStreamReader(可理解成字符流)，
        //因此 new FileReader(file)包含着解码的过程，默认使用utf-8解码，所以文件的编码也要是utf-8
        //fr中可以理解为包含着一个个字符的流
        FileReader fr = new FileReader(file);


        //read(): Reads a single character. 本质是通过InputStreamReader中的read()读取单个字符
        //返回读到的字符的数值形式
        System.out.println(fr.read());
        System.out.println(fr.read());
        System.out.println(fr.read());
        System.out.println(fr.read());
    }


    @Test
    public void test1_2() throws IOException {
        File file = new File("testResource\\test.txt");
        //new FileReader(file)包含着解码的过程，fr中可以理解为包含着一个个字符的流
        FileReader fr = new FileReader(file);

        //返回字符编码对应的字符,byte--->char， 使用utf-8编码
        System.out.println((char) fr.read());
        System.out.println((char) fr.read());
        System.out.println((char) fr.read());
    }


    @Test
    public void test1_3() throws IOException {
        File file = new File("testResource\\test.txt");
        FileReader fr;
        //统计字符个数；
        int num = 0;
        int i;
        fr = new FileReader(file);
        //read()返回下一个字符的整数；如果已到达文件末尾，则返回 -1；
        //read()方法是一个字符一个字符地读取的
        while ((i = fr.read()) != -1) {
            System.out.print((char) i+" ");
            num++;
        }
        System.out.println("\n" + "字符个数: " + num);
        fr.close();//关闭流，释放内存资源
    }



    /**
     * 文件内容中国，utf-8编码，每个字符占用3个字节
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        FileReader fr = null;
        try {
            //通过FileReader发现，这里实际上将文件关联的FileInputStream按照默认的encoding转换成InputStreamReader(可理解成字符流)，
            // 包含着解码的过程，默认使用utf-8解码
            fr = new FileReader("testResource\\test.txt");

            //Reads characters into an array
            char[] b = new char[10];
            //中国占用2个字符，因此数组剩余的8个空间会是char类型变量的默认值0
            fr.read(b);
            System.out.println(new String(b));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println();
                System.out.println("closing....");
                fr.close();//关闭流，释放内存资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 文件内容是： 中国人最牛，
     * utf-8编码，每个字符占用3个字节
     *
     * @throws IOException
     */
    @Test
    public void test2_2() throws IOException {
        FileReader fr = null;
        try {
            fr = new FileReader("testResource\\test.txt");
            char[] b = new char[4];
            int len;
            //len是实际读取到的字符数量，一般会是数组的长度，但是如果文件的最后剩余的字符数量小于数组长度，
            //此时的len将会小于数组长度，数组的最后几位将会是上次读到的字符,new String(char[] xx)时候会出现异常情况
            //第一次循环char[] b={'中','国','人','最'};    new String(b)=中国人最
            //第二次循环char[] b={'牛',‘国’,'人,'最'};    new String(b)=牛国人最
            while ((len = fr.read(b)) != -1) {
                //读取数组中的有效长度
                System.out.println(new String(b));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println();
                System.out.println("closing....");
                fr.close();//关闭流，释放内存资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 文件内容是： 中国人最牛，
     * utf-8编码，每个字符占用3个字节
     *
     * @throws IOException
     */
    @Test
    public void test2_3() throws IOException {
        FileReader fr = null;
        try {
            fr = new FileReader("testResource\\test.txt");
            char[] b = new char[4];
            int len;
            //len表示读取数组中的有效长度,也就是读取到的字符数量
            //第一次循环char[] b={'中','国','人','最'};  len=4, new String(b,0,4)=中国人最
            //第二次循环char[] b={'牛',‘国’,'人,'最'};  len=1,    new String(b,0,1)=牛
            while ((len = fr.read(b)) != -1) {
                System.out.print(new String(b, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println();
                System.out.println("closing....");
                fr.close();//关闭流，释放内存资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 文件内容：中国人，采用GB2312编码；
     * new FileReader(file)会默认采用utf-8进行解码，因此解码后的字符流会存在乱码，所以后面read()的时候会产生乱码
     *解决该问题的办法是，放弃使用FileReader，改用InputStreamReader，在获取InputStreamReader对象时，显示指定合适的字符集。
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {
        File file = new File("testResource\\test.txt");
        FileReader fr;
        //统计字符个数；
        int num = 0;
        int i;
        //这里会默认采用utf-8进行解码，和文件的编码方式不同，因此解码后的字符流会存在乱码，所以后面read()的时候会产生乱码
        fr = new FileReader(file);
        //Reader流里面的read()方法是一个字符一个字符地读取的
        while ((i = fr.read()) != -1) {
            System.out.print((char) i);
            num++;
        }
        System.out.println("\n" + "字符个数: " + num);
        fr.close();//关闭流，释放内存资源
    }


    /**
     * 文件内容：中国人，采用GB2312编码；
     * new FileReader(file)会默认采用utf-8进行解码，因此解码后的字符流会存在乱码，所以后面read()的时候会产生乱码
     *解决该问题的办法是，放弃使用FileReader，改用InputStreamReader，在获取InputStreamReader对象时，显示指定合适的字符集。
     * @throws IOException
     */
    @Test
    public void test3_2() throws IOException {
        File file = new File("testResource/test.txt");
        FileReader fr;
        //统计字符个数；
        int num=0;
        int len = 0;
        char[] chars=new char[10];
        //这里会默认采用utf-8进行解码，和文件的编码方式不同，因此解码后的字符流会存在乱码，所以后面read()的时候会产生乱码
        fr = new FileReader(file);
        while ((len = fr.read(chars)) != -1) {
            System.out.print(new String(chars,0,len));
        }
        fr.close();//关闭流，释放内存资源
    }



}
