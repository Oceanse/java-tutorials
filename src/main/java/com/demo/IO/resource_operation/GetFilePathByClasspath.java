package com.demo.IO.resource_operation;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author epanhai
 */
public class GetFilePathByClasspath {

    /**
     * maven工程的classpath：target/classes
     * @throws IOException
     */
    @Test
    public void getFromClassPath() throws IOException {

        //Resource是org.springframework.core.io.Resource
        Resource resource = new ClassPathResource("com/demo/annotation/TestDemo.class");
        String  path = resource.getFile().getPath();
        System.out.println(path);

        Resource resource2 = new ClassPathResource("log4j2.xml");
        String  path2 = resource2.getFile().getPath();
        String content = new String(Files.readAllBytes(Paths.get(path2)));
        System.out.println(content);
    }

}
