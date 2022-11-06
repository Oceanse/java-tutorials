package com.demo.IO.io.ioredirect;

import org.testng.annotations.Test;

import java.io.*;
import java.util.Scanner;

/**
 * @author epanhai
 */
public class StandInRedirect {

    /**
     * FileInputStream： 来自文件的输入流
     * @throws FileNotFoundException
     */
    @Test
    public static void test() throws FileNotFoundException {
        //获取输入流
        InputStream input = new FileInputStream("testResource\\test.txt");
        //扫描输入流
        Scanner sc=new Scanner(input);

        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
    }


    /**
     * 来自键盘输入的标准输入流
     * InputStream=System.in, 来自控制台的输入流
     * Scanner的输入流来自键盘输入时候，如果输入流中没有数据，hasNextLine()会阻塞，等待数据的输入
     */
    @Test
    public static void test2(){
        //InputStream=System.in是标准输入流，默认从键盘输入
        InputStream input = System.in;
        Scanner sc=new Scanner(input);
        String info;
        while(sc.hasNextLine()){
            info= sc.nextLine();
            if("exit".equals(info)){
                System.exit(0);
            }else{
                System.out.println(info);
            }
        }
    }



    @Test
    public static void test2_2() throws IOException {
        //System.in是来自控制台的输入流
        //System.in是标准输入流，默认从键盘输入；
        //这里获取输入流中的第一个字符
        int i=System.in.read();
        System.out.println((char)i);
    }



    public static void test2_3() throws IOException {

        //将字节输入流转换成字符输入流
        InputStreamReader isr = new InputStreamReader(System.in);

        //构建缓冲字符输入流BufferedReader(Reader in)
        BufferedReader br = new BufferedReader(isr);
        System.out.println("please input the data: ");
        String info;
        while((info=br.readLine())!=null){

            if("exit".equals(info)){
                System.exit(0);
            }else{
                System.out.print(info);
            }
        }
    }


    /**
     * 重定向输入流
     *  Scanner的输入流来自文件输入流时，如果输入流中没有数据，hasNextLine()会直接返回false
     */
    @Test
    public static void test3() throws FileNotFoundException {
        //获取输入流
        InputStream input = new FileInputStream("testResource/test.txt");
        //标准输入流重定向
        System.setIn(input);
        //这里System.in是FileInputStream
        InputStream in = System.in;

        Scanner sc=new Scanner(in);
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
    }


    public static void main(String[] args) throws IOException {
        test2_3();
    }
}
