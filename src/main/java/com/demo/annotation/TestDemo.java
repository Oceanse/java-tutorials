package com.demo.annotation;


import org.testng.annotations.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 常见元注解：
 * @author epanhai
 * @Retention：英文意为保留期或者存活时间的意思 它的取值如下：
 * RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行语法检查时会被用到，编译成字节码文件时它将被丢弃忽： 比如@Override
 * RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
 * RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
 *
 * @Target指定了注解运用的地方，比如只能张贴到方法上、类上、方法参数上等等； 它的取值如下：
 * ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 * ElementType.CONSTRUCTOR 可以给构造方法进行注解
 * ElementType.FIELD 可以给成员变量进行注解，但是不能用在局部变量上
 * ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 * ElementType.METHOD 可以给方法进行注解
 * ElementType.PACKAGE 可以给一个包进行注解
 * ElementType.PARAMETER 可以给一个方法内的参数进行注解
 * ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
 *
 * @Documented： 顾名思义，这个元注解肯定是和文档有关。它的作用是能够将注解中的元素包含到 Javadoc 中去。
 *
 * 注解使用：@注解名(k1=v1,k2=v2,k3=v3), eg： @JTest("name"="loadTest", priority=1)
 *
 *
 *  内置注解:
 *  @Deprecated: 这个元素是用来标记过时的元素，想必大家在日常开发中经常碰到。编译器在编译阶段遇到这个注解时会发出提醒警告，
 *  告诉开发者正在调用一个过时的元素比如过时的方法、过时的类、过时的成员变量。
 *  @Override: 提示子类要复写父类中被 @Override 修饰的方法
 *  @SuppressWarnings: 编译器去忽略注解中声明的警告。
 *  @FunctionalInterface: 函数式接口注解,就是只有一个抽象方法的普通接口，Runnable 就是一个典型的函数式接口
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@interface ClassAnnotation {
    //注解的属性也叫做成员变量，注解只有成员变量，没有方法，但是成员变量是以““无形参的方法”形式来声明",下面代码定义了id,desc 2个属性
    //在使用@AnnoDemo注解的时候，必须给所有成员变量进行赋值:  @AnnoDemo()
    int id();
    String desc();
}



/**
 * @author epanhai
 * 在使用@AnnoDemo注解的时候，一般来说，必须给所有成员变量进行赋值；
 * 但是成员变量可以又默认值，这时候使用注解的时候可以不用对这个成员进行初始化
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@interface MethodAnnotation {
    int id() default 1;
    String[] hobby();
}



/**
 * @author epanhai
 * 如果一个注解内仅仅只有一个名字为 value 的属性时，应用这个注解时可以直接接属性值填写到括号内。
 * @注解名(value), eg： @JTest("value"="loadTest")可以简写为： @JTest("loadTest")
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@interface MethodAnnotation2 {
    String value();
}



/**
 * @author epanhai
 * 一个注解没有任何属性,那么在应用这个注解的时候，括号都可以省略。
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@interface MethodAnnotation3 {
}



/**
 * @author epanhai
 * 属性注解
 */
@Target(value = ElementType.FIELD)
@interface FieldAnnotation {
    String type();
    int value();
}








/**
 * @author epanhai
 * 使用@AnnoDemo注解的时候，必须给所有成员变量进行赋值
 */
@ClassAnnotation(id = 1,desc = "first")
public class TestDemo {

    /**
     * 可以给成员变量进行注解，但是不能用在局部变量上
     */
    @FieldAnnotation(type="int",value = 10)
    private int id;


    /**
     * 类注解
     */
    @Test
    public void testAnnotation() {
        ClassAnnotation annotation = TestDemo.class.getAnnotation(ClassAnnotation.class);
        System.out.println(annotation);
        int id=annotation.id();
        String desc = annotation.desc();
        System.out.println("id=" + id);
        System.out.println("desc=" + desc);
    }


    /**
     * MethodAnnotation的成员 变量id具有默认值，所以可以不用初始化；
     * 但是desc必须初始化
     */
    @Test
    @MethodAnnotation(hobby = {"sports", "music"})
    public void testAnnotation2() throws NoSuchMethodException {
        Method testAnnotation2 = TestDemo.class.getDeclaredMethod("testAnnotation2");
        MethodAnnotation annotation = testAnnotation2.getAnnotation(MethodAnnotation.class);
        int id = annotation.id();
        String[] hobbies = annotation.hobby();
        System.out.println(annotation);
        System.out.println(id);
        System.out.println(Arrays.toString(hobbies));
    }


    @Test
    @MethodAnnotation2("valueDemo")
    public void testAnnotation3() throws NoSuchMethodException {
        Method testAnnotation3 = TestDemo.class.getDeclaredMethod("testAnnotation3");
        MethodAnnotation2 annotation = testAnnotation3.getAnnotation(MethodAnnotation2.class);
        String desc = annotation.value();
        System.out.println(annotation);
        System.out.println(annotation.value());
    }


    @Test
    @MethodAnnotation3
    public void testAnnotation4() throws NoSuchMethodException {
        Method testAnnotation4 = TestDemo.class.getDeclaredMethod("testAnnotation4");
        MethodAnnotation3 annotation = testAnnotation4.getAnnotation(MethodAnnotation3.class);
        System.out.println(annotation);
    }


    @Deprecated
    public static void deprecatedMethod(){
    }


    public static void testAnnotation5(){
        deprecatedMethod();
    }

    public static void testAnnotation6(){
        //属性注解不能用在局部变量上
        //@FieldAnnotation(type="int",length = 10)
         int id;
    }



}
