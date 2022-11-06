package com.demo.IO.resource_operation;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author epanhai
 */
public class Traverse {

    /**
     * 递归遍历某个目录内的所有文件,如果是文件则直接打印
     * @param file
     */
    public void myListFile(File file) {
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
    public void test() {
        File f = new File("C:\\Users\\epanhai\\git\\myproject\\JavaDemo");
        myListFile(f);
    }



    /**
     * Files.walk在 Java 中遍历文件。
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        Path path = Paths.get("/Users/ocean/study/repo/JavaDemo/src/main/java/com/demo/IO");
        //不指明深度，会遍历所有层次(目录级别数)的文件目录,包括path对应的根目录
        Stream<Path> walk = Files.walk(path);
        List<Path> collect = walk.collect(Collectors.toList());
        collect.forEach(System.out::println);
    }


    /**
     * Files.walk在 Java 中遍历文件。
     * @throws IOException
     */
    @Test
    public void test2_2() throws IOException {
        Path path = Paths.get("/Users/ocean/study/repo/JavaDemo/src/main/java/com/demo/IO");
        //指明深度1，只会遍历当前根目录下(目录级别数)的文件目录,包括path对应的根目录
        Stream<Path> walk = Files.walk(path,1);
        List<Path> collect = walk.collect(Collectors.toList());
        collect.forEach(System.out::println);
    }


}
