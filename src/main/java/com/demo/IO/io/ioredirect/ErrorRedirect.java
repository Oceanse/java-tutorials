package com.demo.IO.io.ioredirect;

import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author epanhai
 */
public class ErrorRedirect {

    /**
     * PrintStream=System.err为“标准错误输出流”；
     * System.err对应的PrintStream默认输出到控制台
     * 输出显示为红色；
     *
     * System.out在JVM和操作系统都具有缓存功能，就是你输出的东西不一定实时输出，有时候会积攒到一定数量才会输出，
     * System.err会实时输出，单独使用的话可能感觉不到，如果两种方式混合使用就会发现了 。
     */
    @Test
    public static void test() {
        System.err.println("这是一条");
        System.err.println("错误信息");
        System.out.println("这是一条正常");
        System.out.println("信息的输出");
    }

    @Test
    public static void test2() throws FileNotFoundException {
        PrintStream printStream=new PrintStream(new FileOutputStream("testResource\\test.txt"));
        System.setErr(printStream);
        System.err.println("这条错误的信息将会被重定向到文件");
    }

    @Test
    public static void test3() throws IOException {
        List<String> paramList = new ArrayList<>();
        paramList.add("cmd");
        paramList.add("/c");
        paramList.add("ping www.baidxssu.com");

        ProcessBuilder processBuilder = new ProcessBuilder(paramList);

        //这里会把进程的标准输出信息重定向到一个目标文件，标准错误信息重定向到另一个目标文件
        processBuilder.redirectOutput(new File("testResource\\test.txt")).redirectError(new File("testResource\\error.txt"));
        processBuilder.start();
    }



    @Test
    public static void test3_2() throws IOException {
        List<String> paramList = new ArrayList<>();
        paramList.add("cmd");
        paramList.add("/c");
        paramList.add("ping www.baidxssu.com");

        ProcessBuilder processBuilder = new ProcessBuilder(paramList);

        //这里会把进程的标准输出信息重定向到一个目标文件，标准错误信息会merge到标准输出信息中(合并标准输出和错误)
        processBuilder.redirectOutput(new File("testResource\\test.txt")).redirectErrorStream(true);
        processBuilder.start();
    }




}
