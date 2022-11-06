package com.demo.IO.resource_operation;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFileContents {

    @Test
    public void readFileContents() throws IOException {

        //Resource是org.springframework.core.io.Resource
        Resource resource = new ClassPathResource("com/demo/annotation/TestDemo.class");
        String  path = resource.getFile().getPath();
        System.out.println(path);

        String content = new String(Files.readAllBytes(Paths.get(path)));
        System.out.println(content);
    }


    @Test
    public void readFileContents2() throws IOException {

        InputStream inputStream = new FileInputStream("pom.xml");

        //InputStreamReader类是从字节流到字符流的桥接器,做的逻辑是根据指定的编码方式把字节流转成字符流，inputStreamReader就是解码后的字符流
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str;
        //可以按行读取数据，推测理解为把上面的解码的数据按行读取
        while((str=bufferedReader.readLine())!=null){
            System.out.println(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
    }
}
