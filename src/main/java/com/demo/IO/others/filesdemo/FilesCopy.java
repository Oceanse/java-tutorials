package com.demo.IO.others.filesdemo;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author epanhai
 */
public class FilesCopy {


    @Test
    public void test() throws IOException {

        URI uri = URI.create("http://www.baidu.com/");
        URL url = uri.toURL();
        InputStream in = url.openStream();

        //相对路径在工程的根目录下才有效
        //这里默认StandardCopyOption.REPLACE_EXISTING=false; 所以赋值前要确保target指向的文件不存在,不然target文件存在的话会产生FileAlreadyExistsException
        //testResource目录存在，out.txt文件不存在
        Path target = Paths.get("testResource/output.txt");

        if (Files.exists(target)) {
            Files.delete(target);
        }
        Files.copy(in, target);
    }


    @Test
    public void test2() throws IOException {

        URI u = URI.create("http://www.baidu.com/");
        InputStream in = u.toURL().openStream();

        //testResource/test.txt存在
        Path target = Paths.get("testResource /output.txt");

        //这里target文件存在的话会产生FileAlreadyExistsException
        Files.copy(in, target);
    }


    @Test
    public void test3() throws IOException {

        URI u = URI.create("http://www.baidu.com/");
        InputStream in = u.toURL().openStream();

        //相对路径在工程的根目录下才有效
        //nonedir目录不存在
        //NoSuchFileException
        Path target = Paths.get("nonedir/output.txt");

        //复制的过程会产生target对应的文件output.txt,但是不会createdir，所有路径中的dir一定要存在
        Files.copy(in, target);
    }



    @Test
    public void test4() throws IOException {
        URI u = URI.create("http://www.baidu.com/");
        InputStream in = u.toURL().openStream();

        //testResource\test.txt存在
        Path target = Paths.get("testResource\\output.txt");

        //如果设置了REPLACE_EXISTING，则会覆盖已有的文件，而不会产生FileAlreadyExistsException
        Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
    }


    /**
     * 类似于linux mv： Move or rename a file to a target file.
     *
     * @throws IOException
     */
    @Test
    public void test6() throws IOException {

        Path path = Paths.get("testResource/out.txt");
        Path path2 = Paths.get("testResource/out2.txt");

        //如果设置了REPLACE_EXISTING，则会覆盖已有的文件
        Files.move(path, path2, StandardCopyOption.REPLACE_EXISTING);
    }


}
