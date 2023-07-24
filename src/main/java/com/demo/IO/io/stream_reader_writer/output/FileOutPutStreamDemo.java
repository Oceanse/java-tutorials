package com.demo.IO.io.stream_reader_writer.output;

import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;


/**
 In Java, a stream is composed of bytes

 * 流就像是连接内存(程序)和其他文件或者网络的管道，程序和文件的数据交换都要通过流的输入输出来完成
 * 当你要从文件或者网络读取数据的时候，一根管道(流)插到文件里面去，然后文件里面的数据就顺着管道流出来，管道的另一头(内存)就可以读取到数据并进行处理
 * 当你要往文件写入数据时，也是通过一根管道接入进文件，然后来自于管道另一头内存程序的数据写入到文件中
 *
 *
 * 按照流动的方向，以内存或者程序为基准，分为输入input 和输出output ，即流向内存是输入流，流出内存的输出流。
 * 输入流 InputStream/Reader：把数据从其他设备上读取到内存中的流。
 * 输出流 OutputStream/Writer：把数据从内存中写出到其他设备上的流。
 *
 *
 *        outputstream
 * 内存--------------------->硬盘
 *          write
 *
 *       inputstream
 * 硬盘-------------------->内存
 *          read
 *
 * 根据数据的类型分为：
 * 字节流 InputStream/OutputStream：以字节为单位，读写数据的流。
 * 字符流 Reader/Writer：以字符为单位，读写数据的流。
 *
 *
 * 注意：
 *一切数据(文本、图片、音频、视频)底层都是以二进制数字存储，一个一个的字节，传输的时候也是如此。所以字节流可以传输任何数据。
 * 所以再操作流的时候要注意，无论使用什么样的流对象，底层传输始终是二进制数据。


 * FileOutputStream:文件输出流，用于将内存中的数据写到文件的输出流
 * 继承关系：FileOutputStream--->OutputStream
 *
 * 写入数据的原理：java程序--->jvm-->OS--->os调用写数据方法--->写入到文件
 *
 * FileOutputStream构造方法，当你创建一个流对象时，必须直接或者间接传入一个文件路径,这个路径就是文件写入的目的地：
 * 1、 public FileOutputStream(File file)：根据File对象为参数创建对象；如果没有这个文件，会创建该文件;如果有这个文件，会清空这个文件的数据.
 * 2、 public FileOutputStream(String name)： 根据名称字符串为参数创建对象；如果没有这个文件，会创建该文件;如果有这个文件，会清空这个文件的数据.
 * 3、public FileOutputStream(File file, boolean append)；如果没有这个文件，会创建该文件;如果有这个文件，在这个文件后面追加数据.
 * 4、public FileOutputStream(String name, boolean append) ；如果没有这个文件，会创建该文件;如果有这个文件，在这个文件后面追加数据.
 * 推荐使用第2、4种构造方法：
 *
 *
 * OutputStream:
 * 1、 public void close() ：关闭此输出流并释放与此流相关联的任何系统资源(占用内存资源)。
 * 2、 public void flush() ：刷新此输出流并强制任何缓冲的输出字节被写出。
 * 3、 public void write(byte[] b)：将 b.length个字节从指定的字节数组写入此输出流。
 * 4、 public void write(byte[] b, int off, int len) ：从指定的字节数组写入 len字节，从偏移量 off开始输出到此输出流。 也就是说从off个字节数开始读取一直到len个字节结束
 * 5、 public abstract void write(int b) ：将指定的字节输出流。
 * @author epanhai
 */
public class FileOutPutStreamDemo {

    /**
     * C:\Users\epanhai\nondir\nondir2存在的话，
     * new FileOutputStream(new File("C:\\Users\\epanhai\\nondir\\nondir2\\out.txt"))会创建一个空文件
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        new FileOutputStream(new File("C:\\Users\\epanhai\\nondir\\nondir2\\out.txt"));
    }


    /**
     * public FileOutputStream(File file)：根据File对象为参数创建对象；如果没有这个文件，会创建该文件;如果有这个文件，会清空这个文件的数据.
     * public void write(byte[] b)：将 b.length个字节从指定的字节数组写入此输出流。
     */
    @Test
    public void test() {

        File f = new File("testResource/lyric");
        FileOutputStream fos = null;
        try {
            //fos指向被写文件, 文件不存在会在工程根目录下被创建, 但前提是文件所在的父目录必须存在
            //如果文件存在，会清空这个文件的数据
            //FileOutputStream(File file) throws FileNotFoundException 文件字节输出流指向的文件可能不存再，所以可能报FileNotFoundException
            fos = new FileOutputStream(f);

            //通过FileOutputStream向文件写入数据
            //把指定的字节/字节数组写入到输出流，然后输出流流向文件;
            //字节数组的本质就是编码，也就是把编码写入到了文件，文件会根据自己的编码方式进行解码展示
            fos.write(new byte[]{71,79,68});//GOD
            fos.write(10); //换行符的ASCII码值为10
            fos.write("You raise me up to more than I can be".getBytes(Charset.defaultCharset()));
            fos.write(10);//再次写入换行符，方便后面追加内容
            //强制写到硬盘，OutputStream的flush方法是空实现，FileOutputStream没有重写父类OutputStream的flush方法，所以FileOutputStream.flush()实际是多余的
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();//关闭流，释放内存资源
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * public FileOutputStream(File file, boolean append)；如果没有这个文件，会创建该文件;如果有这个文件，在这个文件后面追加数据.
     * public void write(byte[] b, int off, int len) ：从偏移量 off开始输出到此输出流,也就是说从off个字节数开始读取一直到len个字节结束
     */
    @Test
    public void test2() {
        String filePath ="testResource/lyric";
        //fos指向被写文件,文件不存在会在工程根目录下被创建; 如果有这个文件，在这个文件后面追加数据
        try (FileOutputStream fos = new FileOutputStream(filePath, true)) {
            String lyric="You raise me up,so I can stand on mountains";
            //从指定的字节数组的一部分内容写入此输出流，然后输出流流向文件
            fos.write(lyric.getBytes(), 0, lyric.length());
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }





}
