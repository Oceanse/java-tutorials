package com.demo.reflection.demo2;

import com.demo.basic.code_block.construct_block.demo1.Person;
import com.demo.collection_map.model.Product;
import org.testng.annotations.Test;

import java.io.InputStream;

/**
 * https://blog.csdn.net/m0_68064743/article/details/123957150
 *
 * JDK自带三种类加载：启动类加载器，扩展类加载器，应用类加载器
 *
 * 功能：
 *              javac编译                      类加载器装载
 * .java源文件---------------->.class字节码文件----------------->内存
 * 类加器负责把字节码文件加载到内存，
 * 启动类加载器：rt.jar
 * 扩展类加载器：ext/*.jar
 * 应用类加载器：classpath
 *
 *
 *
 *
 */
public class ClassLoaderDemo {


    @Test
    public void ClassLoaderTest() throws ClassNotFoundException {
        Class clz = Product.class;

        //应用程序类加载器(Application ClassLoader， AppClassLoader):这个类加载器负责加载用户类路径(CLASSPATH)下的类库,
        // 一般我们编写的java类都是由这个类加载器加载,也称之为系统类加载器
        //获取Apples的类加载器
        ClassLoader classLoader = clz.getClassLoader();//我们编写的java类由AppClassLoader负责加载
        System.out.println(classLoader);

        //扩展类加载器(Extension ClassLoader， ExtClassLoader)这个类加载器负责加载\lib\ext目录下的类库
        ClassLoader exet = classLoader.getParent();
        System.out.println(exet);

        //启动类加载器(Bootstrap ClassLoader)该加载器无法直接获取.加载java核心类库
        //比如C:\Program Files\Java\jdk1.8.0_201\jre\lib\rt.jar
        ClassLoader bootstrap = exet.getParent();
        System.out.println(bootstrap);


        //启动类加载器无法直接获取
        ClassLoader bootstrap2 = Class.forName("java.lang.Object").getClassLoader();//系统核心类库由(Bootstrap ClassLoader负责加载核心类库
        System.out.println(bootstrap2);

    }


    /**
     * 通过ClassLoader获取文件的绝对路径,ClassLoader是从classpath根路径下开始寻找
     */
    @Test
    public void ClassLoaderTest2(){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        String path = contextClassLoader.getResource("log4j2.xml").getPath();//通过ClassLoader获取文件的绝对路径
        System.out.println(path);
    }


    /**
     * 通过ClassLoader获取文件的输入流，ClassLoader是从classpath根路径下开始寻找
     */
    @Test
    public void ClassLoaderTest3(){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = contextClassLoader.getResourceAsStream("testng/order.xml");
    }


}
