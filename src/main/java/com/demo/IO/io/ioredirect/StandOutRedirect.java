package com.demo.IO.io.ioredirect;

import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author epanhai
 */
public class StandOutRedirect {
    /**
     * 打印流PrintStream是一个包装流(处理流)，当它包装的是FileOutputStream，那么信息就会打印到FileOutputStream指向的文件
     * PrintStream功能强大，当需要输出文本内容时候，通常会将输出流包装成打印流PrintStream，然后输出
     * @throws FileNotFoundException
     */
    @Test
    public static void test() throws FileNotFoundException {

        //打印流PrintStream可以包装FileOutputStream，也是一个包装流或者处理流，因此这里的PrintStream对象流向一个文件
        //直接使用打印流PrintStream打印"good luck"到文件
        PrintStream printStream=new PrintStream(new FileOutputStream("testResource\\test.txt"));

        //向打印流写入信息
        //个人理解这里会对括号中的内容按照默认的字符集进行编码，然后写到打印流，然后从打印流流向文件，文件再按照自己的编码方式解码展示
        printStream.print("good");
        printStream.println("luck");
        printStream.println(new Object());
    }


    /**
     * 打印流PrintStream
     * @throws FileNotFoundException
     */
    @Test
    public static void test1_2() throws FileNotFoundException {

        //打印流PrintStream可以包装FileOutputStream，也是一个包装流或者处理流，因此这里的PrintStream对象流向一个文件
        //直接使用打印流PrintStream打印"good luck"到文件
        //文件采用Big5编码，和输入的内容的默认字节流编码格式(utf-8)不一致，所以会产生生乱码
        PrintStream printStream=new PrintStream(new FileOutputStream("testResource\\test.txt"));

        //向打印流写入信息
        //个人理解这里会对括号中的内容按照默认的字符集进行编码，然后写到打印流，然后从打印流流向文件，文件再按照自己的编码方式解码展示
        printStream.print("你好");
        printStream.println("中国");
    }



    /**
     * 标准输出流，默认打印输出到控制台
     * @throws FileNotFoundException
     */
    @Test
    public static void test2() throws FileNotFoundException {

        //System.out是标准输出流， 默认打印输出到控制台
        //out是System类的静态常量， 类型是PrintStream；System和PrintStream是组合关系，个人理解：System类中的PrintStream是指向控制台的，因此打印流会打印到控制台
        //这里的PrintStream会输出到控制台
        PrintStream out = System.out;
        out.println("output info to console");
    }


    /**
     * 重定向标准输出流到文件
     * @throws FileNotFoundException
     */
    @Test
    public static void test3() throws FileNotFoundException {

        //打印"water"到文件
        //文件存在则覆盖，不存在则创建
        PrintStream printStream=new PrintStream(new FileOutputStream("testResource/test.txt"));
        //个人理解，这里相当于System设置了一个新的PrintStream对象(上面的对象)，新的PrintStream对象流向一个文件(原先的PrintStream对象流向到控制台)
        System.setOut(printStream);
        //向新的打印流PrintStream(重定向后的标准输出流)写入信息
        System.out.println("output info to a file");
    }


    /**
     * 重定向进程的标准输出流
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        List<String> paramList = new ArrayList<>();
        paramList.add("cmd");
        paramList.add("/c");
        paramList.add("ping www.baidu.com");

        ProcessBuilder processBuilder = new ProcessBuilder(paramList);

        //这里会把进程的标准输出信息重定向到一个目标文件
        processBuilder.redirectOutput(new File("testResource\\test.txt"));
        processBuilder.start();
    }

}
