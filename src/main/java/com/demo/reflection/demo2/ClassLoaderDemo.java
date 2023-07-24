package com.demo.reflection.demo2;

import com.demo.basic.code_block.construct_block.demo1.Person;
import com.demo.collection_map.model.Product;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * https://blog.csdn.net/m0_68064743/article/details/123957150
 * https://juejin.cn/post/6967697259962040328
 *
 * Classloader是一个负责加载classes的对象，ClassLoader类是一个抽象类，给定类的二进制名称（全限定名），
 * 尝试定位或者产生一个class的元数据信息（class静态常量池中的元数据）存放到方法区中的运行时常量池以及字符串常量池中,
 * 并且创建一个class实例对象指向该方法区内存的地址
 *
 * JDK自带三种类加载：启动类加载器(Bootstrap ClassLoader)，扩展类加载器(Extention ClassLoader)，应用类加载器(Appclass Loader)
 *
 * 功能：
 *              javac编译                      类加载器装载
 * .java源文件---------------->.class字节码文件----------------->内存
 * 类加器负责把字节码文件加载到内存，
 * 启动类加载器：最顶层的加载类，主要加载核心类库，%JRE_HOME%\lib下的rt.jar、resources.jar、charsets.jar等。
 * 扩展类加载器：加载目录%JRE_HOME%\lib\ext目录下的jar包和class文件。还可以加载-D java.ext.dirs选项指定的目录。
 * 应用类加载器：加载当前应用的classpath的所有类。
 *
 *
 *
 *
 */
public class ClassLoaderDemo {


    @Test
    public void ClassLoaderTest() throws ClassNotFoundException {
        Class clz = Product.class;

        //应用程序类加载器/好像也叫系统型类加载器(Application ClassLoader， AppClassLoader):这个类加载器负责加载用户类路径(CLASSPATH)下的类库,
        // 一般我们编写的java类都是由这个类加载器加载,也称之为系统类加载器
        //获取Apples的类加载器
        ClassLoader appClassLoader = clz.getClassLoader();//我们编写的java类由AppClassLoader负责加载
        System.out.println(appClassLoader);

        //应用程序类加载器AppClassLoader
        ClassLoader appClassLoader2 = ClassLoader.getSystemClassLoader();
        System.out.println(appClassLoader2);

        //扩展类加载器(Extension ClassLoader， ExtClassLoader)这个类加载器负责加载\lib\ext目录下的类库
        ClassLoader extensionClassLoader = appClassLoader.getParent();
        System.out.println(extensionClassLoader);

        //启动类加载器(Bootstrap ClassLoader)该加载器无法直接获取.加载java核心类库
        //比如C:\Program Files\Java\jdk1.8.0_201\jre\lib\rt.jar
        ClassLoader bootstrap = extensionClassLoader.getParent();
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

