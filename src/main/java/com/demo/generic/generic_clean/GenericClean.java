package com.demo.generic.generic_clean;


import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * java中的泛型是提供给javac编译器使用的，只在编译阶段有效。
 * 当编译器编译完带有泛形的java源程序后，生成的 class字节码文件中是不包含泛型信息的，也就是JVM看不到泛型
 * 也就是泛型信息会在进入到运行时阶段前会被擦出，以此使程序运行效率不受到影响，这个过程称之为“擦除”。
 * 如在代码中定义的 List<Integer> 和 List<String> 等类型，在编译之后都会变成 List。JVM 看到的只是 List，
 * 因此泛型附加的类型信息对 JVM 来说是不可见的
 *
 * 泛型擦除目的：跟低版本保持兼容，因为JDK1.5之前没有泛型，也就是1.5之前的字节码文件都是没有泛型的，为了保证之前的类文件和现在的类文件兼容，
 * java就选择了泛型擦除这一暴力方式，这种方式也给java使用者带来了许多不方便，具体表现为：
 * 1 泛型类型不支持基本类型，因为类型擦除后会转化为Object类型，但是Object类型又不能存储基本数据类型
 *
 * 2 运行时只能对原始类型进行类型检查，而无法对带泛型的类型进行检查，因为运行时候泛型类型已经被擦除
 * if(obj instanceof List){}//编译通过
 * if(obj instanceof List<T>){}//编译报错
 *
 * 3 不能实例化类型参数，因为运行时候不知道T的类型
 * T t=new T();//编译报错
 *java的泛型机制是妥协后的一种产物
 *
 *
 * @author epanhai
 */
public class GenericClean {
    @Test
    public void testClassObjectWithGeneric() {
        ArrayList<String> stringArrayList = new ArrayList<String>();
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        System.out.println(stringArrayList.getClass());
        System.out.println(integerArrayList.getClass());
        System.out.println(stringArrayList.getClass() == integerArrayList.getClass());//指向同一个Class对象，代表同一个字节码文件ArrayList.class
    }


    /**
     * 类泛型类型擦除：编译器自动完成了从 Generic Java 到普通 Java 的翻译，Java 虚拟机运行时对泛型基本一无所知。
     * <p>
     * class<T> Animal{
     * T t;
     * public T getT{
     * return t;
     * }
     * }
     * <p>
     * 无限制类型擦除后(生成字节码之后)：
     * <p>
     * class Animal{
     * Object t;
     * <p>
     * public Object getT(){
     * return t;
     * }
     * }
     */
    @Test
    public void testGenericField() throws NoSuchFieldException {
        Demo<String> demo = new Demo<>();
        String t = demo.t;
        Class demoClass = demo.getClass();
        System.out.println(demoClass.getName());//com.demo.generic.generic_clean.Demo

        Field field = demoClass.getDeclaredField("t");
        System.out.println(field.getName() + ":" + field.getType().getName());//t:java.lang.Object
    }


    /**
     * 类泛型类型擦除：编译器自动完成了从 Generic Java 到普通 Java 的翻译，Java 虚拟机运行时对泛型基本一无所知。
     * <p>
     * class<T extends Number> Animal{
     * T t;
     * public T getT{
     * return t;
     * }
     * }
     * <p>
     * 限制类型擦除后(生成字节码之后)：
     * <p>
     * class Animal{
     * Number t;
     * <p>
     * public Number getT(){
     * return t;
     * }
     * }
     */
    @Test
    public void testExtendsGeneric() throws NoSuchFieldException {
        Demo2<Double> demo = new Demo2<>();
        //demoClass代表字节码文件的一个对象
        Class demoClass = demo.getClass();
        System.out.println(demoClass.getName());//com.demo.generic.generic_clean.Demo2

        Field field = demoClass.getDeclaredField("t");
        System.out.println(field.getName() + ":" + field.getType().getName());//t:java.lang.Number

    }


    /**
     * 方法泛型类型擦除：编译器自动完成了从 Generic Java 到普通 Java 的翻译，Java 虚拟机运行时对泛型基本一无所知。
     * <p>
     * class Animal{
     * public <T extends Number> T getT(T t){
     * return t;
     * }
     * public <E> E getE(E e){
     * return e;
     * }
     * }
     * 限制类型擦除后(生成字节码之后)：
     * <p>
     * class Animal{
     * <p>
     * public Number getT(Number t){
     * return t;
     * }
     *
     * public Object getE(Object e){
     * return e;
     * }
     * }
     * <p>
     * <p>
     * 无限制类型擦除后(生成字节码之后)则是在运行时期把泛型类型替换成Object
     */
    @Test
    public void testGenericMethod() throws NoSuchFieldException {
        Demo demo = new Demo();
        //demoClass代表字节码文件的一个对象
        Class demoClass = demo.getClass();
        Method[] declaredMethods = demoClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName() + ":");
            System.out.println("返回值类型--->" + declaredMethod.getReturnType().getSimpleName());
            System.out.println("参数类型--->" + Arrays.toString(declaredMethod.getParameterTypes()));
        }
    }


    /**
     * interface Demo3<T>{
     * T infoT(T t);
     * }
     * <p>
     * class Demo4 implements Demo3<Integer>{
     *
     * @Override
     * public Integer infoT(Integer integer) {
     * return integer;
     * }
     * }
     * <p>
     * 无限制类型擦除后：
     * <p>
     * interface Demo3{     //泛型接口的泛型被全部替换成Object
     * Object infoT(Object t);
     * }
     * class Demo4 implements Demo3<Integer>{
     * <p>
     * public Integer infoT(Integer integer) { //这里保持实现类的原有方法
     * return integer;
     * }
     * <p>
     * //因为上面接口的方法返回值以及参数是Object类型，所以这里需要新增一个桥接方法，保持接口和类的实现关系
     * @Override public Object infoT(Object integer) {
     * return info((Integer) integer);
     * }
     * }
     * }
     */
    @Test
    public void test5() throws NoSuchFieldException {
        Demo4 demo = new Demo4();
        //demoClass代表字节码文件的一个对象
        Class demoClass = demo.getClass();
        Method[] declaredMethods = demoClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName() + ":");
            System.out.println("返回值类型--->" + declaredMethod.getReturnType().getSimpleName());
            System.out.println("参数类型--->" + Arrays.toString(declaredMethod.getParameterTypes()));
        }
    }


}

class Demo<T> {
    T t;

    public <E> E getE(E e) {
        return e;
    }

    public <E extends Collection> void showE(E e) {
        System.out.println(e);
    }
}


class Demo2<T extends Number> {
    T t;
}

interface Demo3<T> {
    T infoT(T t);
}

class Demo4 implements Demo3<Integer> {
    @Override
    public Integer infoT(Integer integer) {
        return integer;
    }
}