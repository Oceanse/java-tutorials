package com.demo.IO.otherstream.random_access_file;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * RandomAccessFile，翻译成任意访问比随机访问更加合适，是java Io体系中功能最丰富的文件内容访问类。
 * 特点：
 * 1 程序可以直接跳到文件的任意位置来读取数据;
 * 2 可以在文件的末尾追加写入内容
 * 所以如果我们希望只访问文件的部分内容，那就可以使用RandomAccessFile类。
 * @author epanhai
 */
public class RandomAccessFileDemo {


    /**
     * 从指定位置读取文件
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String filePath="testResource/test.txt";
        RandomAccessFile  raf=null;
        File file;
        try {
            file=new File(filePath);
            //只读模式打开了test.txt文件
            raf= new RandomAccessFile (file,"r");
            // 获取 RandomAccessFile对象文件指针的位置，初始位置为0
            System.out.println("文件指针的初始位置："+raf.getFilePointer());
            //移动文件记录指针的位置，定位到索引为3的位置，后面程序会从索引为3的位置开始读取
            //文件内容是1234567890， 所以最后读到的内容就是4567890
            raf.seek(3);
            System.out.println("文件指针的初始位置："+raf.getFilePointer());
            byte[] b=new byte[7];
            int hasRead=0;
            //循环读取文件
            while((hasRead=raf.read(b))>0){
                //输出文件读取的内容
                System.out.print(new String(b,0,hasRead));
            }
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            raf.close();
        }
    }


    /**
     * 向文件中追加内容
     * @throws IOException
     */
    @Test
    public void test2() throws IOException{
        String filePath="testResource\\test.txt";
        RandomAccessFile raf=null;
        File file;
        try {
            file=new File(filePath);
            // 以读写的方式打开一个RandomAccessFile对象
            raf=new RandomAccessFile(file,"rw");
            //将记录指针移动到该文件的最后
            raf.seek(raf.length());
            //向文件末尾追加内容
            raf.write("追加内容".getBytes(StandardCharsets.UTF_8));
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            raf.close();
        }
    }

}
