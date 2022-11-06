package com.demo.IO.io.stream_reader_writer.inandout;

import java.io.*;

/**
 * 通过输入流及其read()方法把src内容读到内存中,
 * 然后通过输出流及其write(byte b)把内存中的内容写到指定文件
 * src文件---->输入流---->程序---->输出流-->dest文件
 * @author epanhai
 */
public class IoStream {

    public static void copyBySingleByte(String src, String dest) throws IOException {
        try( //读取文件的底层编码
             FileInputStream in=new FileInputStream(src);
             //把文件的底层编码写入文件中，所以这里两个文件的encoding方式必须一致
             FileOutputStream out = new FileOutputStream(dest)) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        }
    }

    public static void copyByByteArray(String src, String dest){
        byte[] b=new byte[10];
        int len;

        try(FileInputStream fis=new FileInputStream(src);
            FileOutputStream fos =new FileOutputStream(dest)) {
            //边读边写
            while((len=fis.read(b))!=-1){
                //把byte数组内写到另一个文件
                fos.write(b,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        copyBySingleByte("testResource/kobe.jpg","testResource/kobe2.jpg");
        copyByByteArray("testResource/kobe.jpg","testResource/kobe3.jpg");

    }

}
