package com.demo.IO.io.buffer;

import org.testng.annotations.Test;

import java.io.*;


/**
 * 带缓冲区的字符输出流：
 *
 * 构造函数：
 * BufferedWriter(Writer out)
 * 常见：BufferedWriter(FileWriter fileWriter)  BufferedWriter(OutputStreamWriter fileWriter)
 * @author epanhai
 */
public class BufferedWriterDemo {

    @Test
    public static void test() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("testResource/test.txt"));
        //个人理解这里会对括号中的内容按照默认的字符集进行编码，然后写到输出流中，然后从输出流流向文件，文件再按照自己的编码方式解码展示
        bw.write("今天是2020.05.17");
        bw.newLine();//Writes a line separator
        bw.write("Come on!");

        bw.flush();
        bw.close();
    }


    /**
     * FileOutputStream---->OutputStreamWriter---->BufferedWriter
     * @throws IOException
     */
    @Test
    public static void test2() throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("testResource\\test.txt")));
        //个人理解这里会对括号中的内容按照默认的字符集进行编码，然后写到输出流中，然后从输出流流向文件，文件再按照自己的编码方式解码展示
        bw.write("今天是2020.05.17");
        bw.newLine();//Writes a line separator
        bw.write("Come on!");

        bw.flush();
        bw.close();
    }
}
