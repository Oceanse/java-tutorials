package com.demo.IO.io.buffer;

import org.testng.annotations.Test;

import java.io.*;

/**
 * java.io.BufferedReader--->java.io.Reader--->java.lang.Object
 * BufferedReader(成带有缓冲区的字符输入流): Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
 *
 * 构造函数：
 * public BufferedReader(Reader in) 参数通常是FileReader和InputStreamReader(字节->字符转换流)
 * 常用方法：
 * public String readLine() throws IOException：Reads a line of text
 */
public class BufferedReaderDemo {

    /**
     * 这里文件编码方式是utf-8, jvm默认字符集是utf-8,所以不会出现乱码
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {

        //字符流本质是对文件进行解码后的字符流，因此new FileReader包含着解码的过程，fr可以理解为解码后的字符流，当然字符流中也会包含换行符
        //文件采用utf-8编码，这里默认采用utf-8解码，所以不会出现乱码
        FileReader fr = new FileReader("testResource\\test.txt");

        //缓冲流br的本质类似fr字符流
        BufferedReader br = new BufferedReader(fr);

        //读取字符流中的第一行，也就是文件的第一行，但是不会读取文本的换行符
        System.out.println(br.readLine());
        //读取字符流中的第二行，也就是文件的第二行
        System.out.println(br.readLine());
        //读取字符流中的第三行，也就是文件的第三行
        System.out.println(br.readLine());

        //关闭时候只需要关闭最外层的包装流(装饰者模式)
        br.close();//关闭流，释放内存资源
    }


    /**
     * 这里文件编码方式是GB2312, jvm默认字符集是utf-8,所以会出现乱码
     * @throws IOException
     */
    @Test
    public void test1_2() throws IOException {

        //这里会默认采用utf-8进行解码，和文件的编码方式GB2312不同，因此解码后的字符流会存在乱码，所以后面read()的时候会产生乱码
        FileReader fr = new FileReader("testResource\\test.txt");

        //缓冲流:将字符输入流包装成带有缓冲区的字符输入流，本质也是字符流
        BufferedReader br = new BufferedReader(fr);

        System.out.println(br.readLine());
        System.out.println(br.readLine());
        System.out.println(br.readLine());

        //关闭时候只需要关闭最外层的包装流(装饰者模式)
        br.close();//关闭流，释放内存资源
    }

    /**
     * readLine方法不会读取文本的换行符
     * 文本内容：
     * abc
     * def
     *
     * ghi
     * 输出：abcdefghi
     * @throws IOException
     */
    @Test
    public void test1_3() throws IOException {

        //字符流本质是对文件进行解码后的字符流，因此new FileReader包含着解码的过程，fr可以理解为解码后的字符流，当然字符流中也会包含换行符
        //文件采用utf-8编码，这里默认采用utf-8解码，所以不会出现乱码
        FileReader fr = new FileReader("testResource/test.txt");

        //缓冲流br的本质类似fr字符流
        BufferedReader br = new BufferedReader(fr);

        //读取字符流中的第一行，也就是文件的第一行，但是不会读取文本的换行符
        System.out.print(br.readLine());
        //读取字符流中的第二行，也就是文件的第二行，但是不会读取文本的换行符
        System.out.print(br.readLine());
        //读取字符流中的第三行，也就是文件的第三行，但是不会读取文本的换行符
        System.out.print(br.readLine());
        System.out.print(br.readLine());

        //关闭时候只需要关闭最外层的包装流(装饰者模式)
        br.close();//关闭流，释放内存资源
    }

    /**
     * BufferedReader 读取文本
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        //fr的本质是解码后的字符流，文件采用utf-8编码，这里默认采用utf-8解码，所以不会出现乱码
        FileReader fr = new FileReader("testResource\\test.txt");

        //缓冲流：将字符输入流包装成带有缓冲区的字符输入流，本质也是字符流
        BufferedReader br = new BufferedReader(fr);

        String readContent;
        //Reads a line of text
        while ((readContent = br.readLine()) != null) {
            System.out.println(readContent);
        }
        //关闭时候只需要关闭最外层的包装流(装饰者模式)
        br.close();//关闭流，释放内存资源
    }


    /**
     * FileInputStream---> InputStreamReader ---------->BufferedReader
     * FileInputStream--->InputStreamReader的过程是字节流转化成字符流的过程，是解码的过程，可以指定编码格式
     * @throws IOException
     */
    @Test
    public void test3() throws IOException {

        //字节输入流
        FileInputStream fis = new FileInputStream("pom.xml");
        //InputStreamReader类是从字节流到字符流的桥接器,这里的isr可以理解成是字符流
        //桥接器的一大好处就是在对字节流进行解码的时候可以指定编码集
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
        //字符流包装成缓冲流
        BufferedReader br = new BufferedReader(isr);

        String readContent;
        //Reads a line of text
        while ((readContent = br.readLine()) != null) {
            System.out.println(readContent);
        }
        //关闭时候只需要关闭最外层的包装流(装饰者模式)
        br.close();//关闭流，释放内存资源
    }
}
