package com.demo.reflection.demo1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 如果只是希望静态代码块被执行，而不去执行其他代码，可以采用Class.forName("全类名");
 */
public class ReflectionDemo2 {

    //字节码文件被加载时候执行一次
    static {
        System.out.println("静态代码块被执行");
    }

}

 class ReflectionDemo3 {
    
    public static void main(String[] args) throws ClassNotFoundException {
        //Class.forName是将字节码加载到内存，会初始化静态属性和静态代码块， 所以ReflectionDemo2内的静态代码块会被执行
        Class.forName("com.demo.reflection.demo1.ReflectionDemo2");
    }
}

class ReflectionDemo4{


    /**
     * 反射有时候会影响执行速度
     * @param args
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        testWithoutReflect();
        testWithReflect();
    }

    public static void testWithoutReflect(){
        long start = System.currentTimeMillis();
        Cat cat=new Cat();
        for (int i = 0; i < 600000000; i++) {
            cat.hi();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }

    public static void testWithReflect() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        long start = System.currentTimeMillis();
        Class<Cat> catClass = Cat.class;
        Cat cat = catClass.getConstructor().newInstance();
        Method method = catClass.getMethod("hi");
        for (int i = 0; i < 600000000; i++) {
            method.invoke(cat);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}


class Cat{
    public Cat() {
    }

    public void hi(){
        //System.out.println("恭喜发财");
    }
}