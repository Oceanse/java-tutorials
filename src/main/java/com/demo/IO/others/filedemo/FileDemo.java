package com.demo.IO.others.filedemo;

import org.testng.annotations.Test;

import java.io.File;


/**
 * java.io.File类：文件和目录路径名的抽象表示形式,内存里面的一个磁盘文件或者目录的引用
 * 一个File对象代表硬盘中实际存在的一个文件或者目录(可能存在或者不存在)。
 * File类构造方法不会给你检验这个文件或文件夹是否真实存在，因此无论该路径下是否存在文件或者目录，都不影响File对象的创建。
 * File 能新建、删除、重命名文件和目录，访问文件的属性(文件名，绝对路径，文件目录属性，大小,修改时间等),
 * 但 File 不能访问文件内容本身。如果需要访问文件内容本身，则需要使用输入/输出流。
 */
public class FileDemo {


    /**
     * File对象的创建
     * @param args
     */
    public static void main(String[] args) {

        //通过将给定的 文件相对路径名 来创建新的 File实例,相对路径一定处在工程根目录之下
        File file = new File("testResource\\out.txt");

        //通过将给定的 文件绝对路径名 来创建新的 File实例。
        File file2 = new File("C:\\Users\\EPANHAI\\Documents\\git\\JavaDemo\\testResource\\out.txt");

        //通过将给定的 目录相对路径名 来创建新的 File实例,相对路径一定处在工程根目录之下
        File file3 = new File("src\\main\\java");

        //通过将给定的 目录绝对路径名 来创建新的 File实例。
        File file4 = new File("C:\\Users\\EPANHAI\\Documents\\git\\JavaDemo\\testResource");


        //public File(String parent, String child) ：从父路径名字符串和子路径名字符串创建新的 File实例。
        File file5 = new File("C:\\Users\\EPANHAI\\Documents\\git", "JavaDemo\\src\\main\\java\\io\\javaIO\\java7\\file\\FileTest.java");

        //public File(File parent, String child) ：从父抽象路径名和子路径名字符串创建新的 File实例。
        File file6 = new File(file3, "io\\javaIO\\java7\\file\\FileTest.java");

    }

    /**
     * 文件不存在
     */
    @Test
    public void test1() {
         // windows操作系统中，文件路径的分隔符是反斜杠（“\”）
        //  在linux操作系统中，文件的分隔符是斜杠（“/”）
        File f = new File("noneDir/test.txt");
        //路径末级文件名=test.txt
        System.out.println(f.getName());
        //路径末级文件名之上的全部路径=noneDir
        System.out.println(f.getParent());
        //绝对路径=/Users/ocean/study/repo/JavaDemo/noneDir/test.txt
        System.out.println(f.getAbsolutePath());
        //文件是否存在,false
        System.out.println(f.exists());
        //f不存在，非目录
        System.out.println(f.isDirectory());
        //f不存在，非文件
        System.out.println(f.isFile());
        //f是否是绝对路径
        System.out.println(f.isAbsolute());
        //获取文件大小,f不存在,文件大小是0
        System.out.println(f.length());
    }


    /**
     * 相对路径-文件
     */
    @Test
    public void test1_2() {
        // windows操作系统中，文件路径的分隔符是反斜杠（“\”）
        //  在linux操作系统中，文件的分隔符是斜杠（“/”）
        File f = new File("testResource/goodLuck.txt");
        //路径末级文件名=test.txt
        System.out.println(f.getName());
        //路径末级文件名之上的全部路径=testResource
        System.out.println(f.getParent());
        //绝对路径
        System.out.println(f.getAbsolutePath());
        System.out.println(f.exists());
        System.out.println(f.isDirectory());
        System.out.println(f.isFile());
        System.out.println(f.isAbsolute());
        //获取文件大小,文件内容是abc,utf-8编码，所以文件大小是3B
        System.out.println(f.length());
    }


    /**
     * 绝对路径-文件
     */
    @Test
    public void test2() {
        File f = new File("/Users/ocean/study/repo/JavaDemo/test.txt");
        System.out.println(f.getName());
        System.out.println(f.getParent());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.exists());
        System.out.println(f.isAbsolute());
        System.out.println(f.isDirectory());
        System.out.println(f.isFile());
        //获取文件大小
        System.out.println(f.length());
    }


    /**
     * 相对路径-目录
     */
    @Test
    public void test3() {
        File f = new File("src/main/java");
        //路径末级文件名
        System.out.println(f.getName());
        //路径末级文件名之上的全部路径
        System.out.println(f.getParent());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.exists());
        System.out.println(f.isAbsolute());
        System.out.println(f.isDirectory());
        System.out.println(f.isFile());
        //The return value is unspecified if this pathname denotes a directory.
        System.out.println(f.length());

    }

    /**
     * 绝对路径-目录
     */
    @Test
    public void test4() {
        File f = new File("/Users/epanhai/git");
        //路径末级文件名
        System.out.println(f.getName());
        //路径末级文件名之上的全部路径
        System.out.println(f.getParent());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.exists());
        System.out.println(f.isAbsolute());
        System.out.println(f.isDirectory());
        System.out.println(f.isFile());
        //The return value is unspecified if this pathname denotes a directory.
        System.out.println(f.length());
    }


    /**
     *  Linux系统下分隔符是/
     *   windows系统下分隔符是\,然后前面加上转义字符\,就变成了\\
     */
    @Test
    public void test5() {
        //返回分隔符的String形式
        String separator = File.separator;
        //返回分隔符char形式
        char separatorChar = File.separatorChar;
        System.out.println(separator);
        System.out.println(separatorChar);

        String path = File.separator + "Users"+File.separator+"ocean";
        String path2 = File.separatorChar + "Users"+File.separator+"ocean";
        System.out.println(path);
        System.out.println(path2);
        System.out.println(new File(path).exists());
        System.out.println(new File(path2).exists());
    }


}
