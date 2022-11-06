package com.demo.reflection.demo1;

/**
 * 如果只是希望静态代码块被执行，而不去执行其他代码，可以采用Class.forName("全类名");
 */
public class ReflectionDemo2 {

    //字节码文件被加载时候执行一次
    static {
        System.out.println("静态代码块被执行");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 加载该类的字节码文件到内存，那么JVM加载类的时候，类内部的静态代码块就会被执行
        Class.forName("com.demo.reflection.demo1.ReflectionDemo2");

        //这里即使装载的是ReflectionDemo，但是当前类是ReflectionDemo2，所以ReflectionDemo2必定会被装载，类内部的静态代码块就会必然被执行
        Class.forName("com.demo.reflection.demo1.ReflectionDemo");
    }

}

 class ReflectionDemo3 {
    public static void main(String[] args) throws ClassNotFoundException {
        //这里不会装载ReflectionDemo2字节码文件，所以ReflectionDemo2内的静态代码块不会被执行
        Class.forName("com.demo.reflection.demo1.ReflectionDemo");
    }
}