package com.demo.IO.io.stream_reader_writer.inandout;

import org.testng.annotations.Test;

import java.io.*;

/**
 * 对文本文件等进行读写优先考虑字符流
 * @author epanhai
 */
public class IoReaderWriter {

    @Test
    public void test() throws IOException {
        FileReader fr = null;
        FileWriter fw = null;

        try {
            fr = new FileReader("xanadu.txt");
            fw = new FileWriter("characteroutput.txt");

            int c;
            while ((c = fr.read()) != -1) {
                fw.write(c);
            }
            //使用finally块来关闭物理资源，保证关闭操作总是会被执行。
            //关闭每个资源之前首先保证引用该资源的引用变量不为null。
            //finally块代码十分臃肿，程序的可读性降低。
        } finally {
            if (fr != null) {
                fr.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
    }


    /**
     *  Java7新增了自动关闭资源的try语句。它允许在try关键字后紧跟一对圆括号,
     *  里面可以声明、初始化一个或多个资源，try语句会在程序结束时自动关闭（数据库连接、网络连接等）等资源
     */
    @Test
    public void test2(){
        try(FileReader fr = new FileReader("pom.xml");
            FileWriter fw = new FileWriter("testResource/test.txt")) {
            int len;
            char[] b=new char[10];
            while ((len = fr.read(b)) != -1) {
                fw.write(b,0,len);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    @Test
    public void test3() throws IOException{
        File src = new File("pom.xml");
        File dest = new File("testResource\\test.txt");

        FileReader fr = new FileReader(src);
        //fos指向被写文件,如果有这个文件，在这个文件后面追加数据.
        FileWriter fw = new FileWriter(dest, true);

        char[] b = new char[10];
        int len;
        while ((len = fr.read(b)) != -1) {
            fw.write(b, 0, len);
        }
        fw.close();//后打开先关闭
        fr.close();//先打开后关闭
    }

}
