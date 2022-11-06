package com.demo.reflection.demo3_reflection_application.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 目标: 通过解析修改配置文件config.properties中的信息， 实现动态调用任意类的任意方法
 */
public class ReflectDemo4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        //通过类加载获取properties文件的stream
        ClassLoader classLoader = ReflectDemo4.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("config.properties");

        //Properties可解析.properties文件获取全类名、方法名、参数等信息
        Properties properties=new Properties();
        properties.load(resourceAsStream);
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");

        //通过反射调用指定方法
        Class clz=Class.forName(className);
        Object object = clz.newInstance();
        //这里调用无参方法，因此不需要给出参数信息
        Method method = clz.getMethod(methodName);
        method.invoke(object);
    }

}

class SayHi{
    public void say(){
        System.out.println("hello ocean");
    }
}
