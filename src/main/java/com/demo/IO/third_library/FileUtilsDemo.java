package com.demo.IO.third_library;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/**
 *  <artifactId>commons-io</artifactId>
 *  FileUtils支持很多文件操作，如
 *
 *   文件写入
 *   读取文件
 *   创建目录
 *   拷贝文件和目录
 *   删除文件和目录
 *   从URL转换
 *   基于统配和过滤查看文件和目录
 *   比较文件内容
 *  文件的更新时间
 *  检查校验码
 */
public class FileUtilsDemo {


    @Test
    public void test() throws IOException {

        //获取临时目录
        System.out.println(FileUtils.getTempDirectory());
        //获取用户主目录
        System.out.println(FileUtils.getUserDirectory());
        //Construct a file from the set of name elements
        File file = FileUtils.getFile(FileUtils.getUserDirectory(), "git");
        System.out.println("file.isDirectory() = " + file.isDirectory());

        //创建文件，如果文件存在则更新时间；如果不存在，创建一个空文件
        //上级目录不存在，可以递归创建
        FileUtils.touch(new File("C:\\Users\\epanhai\\nondir\\nondir2","newfile.txt"));
        //FileUtils.touch(new File("newfile.txt"));

        //boolean isSame=FileUtils.contentEquals(new File("test.txt"),new File("test2.txt"));
        //System.out.println(isSame);

        //第一个参数是文件，第二个是参数目录，第三个参数是否更新时间,newdir不存在则会自动创建
        FileUtils.copyFileToDirectory(new File("newfile.txt"),new File("newdir"),true);

    }




    @Test
    public void test2() throws IOException {
        //获取文件输入流,stream从文件流向程序
        InputStream in = FileUtils.openInputStream(new File("pom.xml"));
        //获取文件输入和输出的文件流拷贝到执行文件中
        Files.copy(in, Paths.get("test2.txt"), StandardCopyOption.REPLACE_EXISTING  );


        //OutputStream out = FileUtils.openOutputStream(new File("out.txt"));//获取文件输出流,stream从程序流向文件
        //out.write("hello beijing".getBytes());//源文件中有内容会被覆盖


        //获取文件输出流,stream从程序流向文件，追加的形式添加内容
        OutputStream out2 = FileUtils.openOutputStream(new File("out.txt"),true);
        //在源文件内容后面追加新内容
        out2.write("ocean no.1".getBytes());

    }

    @Test
    public void test3() throws IOException {

        File file=new File("test.txt");
        String contents="good luck";

        System.out.println(Charset.defaultCharset());

        //String写入File对象中,覆盖源文件内容，file不存在则创建
        FileUtils.writeStringToFile(file,contents, Charset.defaultCharset());

    }

    @Test
    public void test3_2() throws IOException {

        File file=new File("test.txt");
        String contents="good luck";

        System.out.println(Charset.defaultCharset());

        //String写入File对象中,覆盖源文件内容，file不存在则创建
        FileUtils.write(file,contents, Charset.defaultCharset());

    }

    @Test
    public void test4() throws IOException {
        //结果只和文件内容有关，和文件名以及文件路径没有关系
        System.out.println(FileUtils.checksumCRC32(new File("out.txt")));
        System.out.println(FileUtils.checksumCRC32(new File("test.txt")));
        System.out.println(FileUtils.checksumCRC32(new File("C:\\Users\\epanhai\\Desktop\\aaa.txt")));
    }

}
