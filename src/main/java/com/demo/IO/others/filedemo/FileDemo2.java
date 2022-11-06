package com.demo.IO.others.filedemo;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author epanhai
 */
public class FileDemo2 {

    @Test
    public void test1() throws IOException {
        File f = new File("testResource/out.txt");
        if (!f.exists()) {
            try {
                //这里createNewFile不能递归创建文件，因此创建文件的前提是文件所在路径要存在
                boolean result =  f.createNewFile();
               if(result){
                   System.out.println("文件创建成功");
               }
            } catch (IOException e) {
                System.out.println("文件创建失败");
                e.printStackTrace();
            }
        } else {
            System.out.println("文件已存在");
        }
    }

    @Test
    public void test1_2() {
        //noneDir不存在
        File f = new File("noneDir/file");
        if (!f.exists()) {
            try {
                //这里createNewFile不能递归创建文件，因此创建文件的前提是文件所在路径要存在
                //这里会产生IOException
                boolean result = f.createNewFile();
                if(result){
                    System.out.println("文件创建成功");
                }
            } catch (IOException e) {
                System.out.println("文件创建失败");
                e.printStackTrace();
            }
        } else {
            System.out.println("文件已存在");
        }
    }


    @Test
    public void test2() {
        //ocean目录不存在
        File f = new File("/Users/ocean/ocean2/ocean3");
        if (!f.exists()) {
            //mkdir不能递归创建目录，但是这里即使创建失败，也不会产生异常
            boolean isCreatedSuccessfully = f.mkdir();
            if(isCreatedSuccessfully){
                System.out.println("创建成功");
            }else{
                System.out.println("创建失败");
            }
        } else {
            System.out.println("dir已存在");
            f.delete();
        }
    }

    @Test
    public void test2_2() {

        File f = new File("C:\\Users\\epanhai\\Desktop\\ocean\\ocean2");
        if (!f.exists()) {
            //mkdirs可以递归创建目录
            boolean isCreatedSuccessfully = f.mkdirs();
            if(isCreatedSuccessfully){
                System.out.println("创建成功");
            }else{
                System.out.println("创建失败");
            }
        } else {
            System.out.println("dir已存在");
            //f2.delete();
        }
        //若file已经存在，再次mkdirs什么也不会做
        f.mkdirs();
    }


    @Test
    public void test3() {
        File f = new File("/Users/ocean");

        //数组中存放着C:\Users\epanhai\Desktop当前目录下所有文件名和目录名，不包含其子目录下的文件和目录
        String[] list = f.list();
        for (String s : list) {
            System.out.println(s);
        }
    }


    @Test
    public void test3_2() {
        File f = new File("C:\\Users\\epanhai\\Desktop");
        //返回File对象数组
        File[] files = f.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }
    }


    /**
     * 递归遍历某个目录内的所有文件,如果是文件则直接打印
     * @param file
     */
    public void myListFile(File file) {
        if(!file.exists()){
            System.out.println("文件或者目录不存在");
            return;
        }
        if (file.isFile()) {
            System.out.println(file.getName());
            return;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                myListFile(f);
            } else {
                System.out.println(f.getName());
            }
        }
    }



    /**
     * 递归遍历某个路径下的所有文件
     */
    @Test
    public void test4() {
        File f = new File("/Users/ocean/study/repo/JavaDemo/src/main/java/com/demo/testNG");
        myListFile(f);

    }



    @Test
    public void test4_2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请您输入合法路径：");
        while (true) {
            String path = scanner.nextLine();
            File f = new File(path);
            if (!f.exists()) {
                System.out.println("您输入的路径不存在，请重新输入");
            } else {
                System.out.println("路径正确");
                myListFile(f);
                return;
            }
        }
    }
}
