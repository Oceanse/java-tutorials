package com.demo.basic.method.recursion;

import java.io.File;

/**
 * @author epanhai
 */
public class Recursion5 {

    /**
     * 遍历文件
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
}

