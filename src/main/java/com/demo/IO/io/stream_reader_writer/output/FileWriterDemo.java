package com.demo.IO.io.stream_reader_writer.output;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
* java.io.FileWriter类是写出字符到文件的便利类。构造时使用系统默认的字符编码和默认字节缓冲区。
*
* 构造方法
1、 FileWriter(File file)： 创建一个新的 FileWriter，给定要读取的File对象。如果没有这个文件，会创建该文件;如果有这个文件，会清空这个文件的数据.
2、FileWriter(String fileName)： 创建一个新的 FileWriter，给定要读取的文件的名称。如果没有这个文件，会创建该文件;如果有这个文件，会清空这个文件的数据.
3、FileWriter(File file)：创建一个新的 FileWriter，给定要读取的File对象。如果没有这个文件，会创建该文件;如果有这个文件，在这个文件后面追加数据.
4、FileWriter(String fileName, boolean appender)创建一个新的 FileWriter，给定要读取的文件的名称. 如果没有这个文件，会创建该文件;如果有这个文件，在这个文件后面追加数据.
*
* write(int b) 方法，每次可以写出一个字符数据
* write(String string)
*
 * @author epanhai*/
public class FileWriterDemo {

    @Test
    public void testFileWriter(){
        File f = new File("testResource/lyric");
        FileWriter fw=null;
        try {
            try {
                //如果没有这个文件，会创建该文件;如果有这个文件，会清空这个文件的数据.
                fw=new FileWriter(f);
                //Returns the name of the character encoding being used by this stream
                System.out.println(fw.getEncoding());
                //个人理解这里会对括号中的内容按照默认的字符集进行编码，然后写到输出流中，然后从输出流流向文件，文件再按照自己的编码方式解码展示
                fw.write("我爱我的家乡和祖国 !\n");
                fw.write("i love my mom and father!\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fw.close();//关闭流，释放内存资源
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void FileWriterWithAppend(){
        String filePath ="testResource/lyric";
        FileWriter fw=null;
        try {
            try {
                //fos指向被写文件,文件不存在会在工程根目录下被创建; 如果有这个文件，在这个文件后面追加数据
                fw=new FileWriter(filePath,true);
                System.out.println(fw.getEncoding());
                //个人理解这里会对括号中的内容按照默认的字符集进行编码，然后写到输出流中，然后从输出流流向文件，文件再按照自己的编码方式解码展示
                fw.write(new char[]{'G','o','o','d','l','u','c','k','2','0','2','3','\n'});
                char[] contents=new char[]{'x','C','o','m','e',' ','o','n','\n'};
                fw.write(contents,1,contents.length-1);//跳过第一个字符
                fw.write("God bless you!\n");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fw.close();//关闭流，释放内存资源
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    /**
     * 乱码展示
     */
    @Test
    public void test3(){
        //这里文件采用Big5编码
        File f=new File("testResource\\test.txt");
        FileWriter fw=null;
        try {
            try {
                //如果没有这个文件，会创建该文件;如果有这个文件，会清空这个文件的数据.
                fw=new FileWriter(f);

                //Returns the name of the character encoding being used by this stream
                System.out.println(fw.getEncoding());

                //个人理解这里会对括号中的内容按照默认的utf-8字符集进行编码，然后写到输出流中，然后从输出流流向文件，文件再按照自己的编码方式解码展示;
                //由于文件采用的是Big5， 和字节流的编码格式不一致，所以会导致乱码
                fw.write("我爱中国 !");
                fw.write("\n");
                fw.write("我爱妈妈 !");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fw.close();//关闭流，释放内存资源
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
