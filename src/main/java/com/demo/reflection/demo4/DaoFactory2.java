package com.demo.reflection.demo4;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class DaoFactory2 {
    static Properties properties;

    static {
        InputStream resourceAsStream = DaoFactory2.class.getClassLoader().getResourceAsStream("bean.properties");
        properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param beanId
     * @param tClass 通过这个参数可以在调用方法时候指明T的具体类型
     * @return
     * @param <T>
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T getBean(String beanId, Class<T> tClass) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String className = properties.getProperty("beanId");
        T t = (T) Class.forName(className).getConstructor().newInstance();
        return t;
    }

}
