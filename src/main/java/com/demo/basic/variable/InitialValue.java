package com.demo.basic.variable;

import org.testng.annotations.Test;
import java.util.Date;
import static java.lang.System.out;


/**
 * 全局变量(成员变量和静态变量)具有默认初始值
 * 局部变量没有默认初始值，必须显式初始化
 */
public class InitialValue {

    //全局变量(成员变量和静态变量)具有默认初始值
    byte b;//0
    short s ;//0
    int i;//0
    long l;//0L
    float f;//0.0F
    double d;//0.0
    boolean flag;//false
    char c;// '\u0000'  这种格式是一个char字符unicode码的写法;它只是看起来时空格，但是它与空格、空字符串、NULL和"null"都不同，所以无法找到能够描述它的符号

    //null是一种特殊的引用类型变量值或者引用类型字面值,牙科椅作为任意引用类型变量的变量值
    Object o;//null
    Runnable r;//null
    String[] names;//null

    static Date date;//null


    @Test
    public void test(){
        out.println(b);
        out.println(s);
        out.println(i);
        out.println(l);
        out.println(f);
        out.println(d);
        out.println(flag);
        out.println(c);
        out.println((int)c);
        out.println();



        //引用变量对应的栈存储空间默认值为null, null可以理解为一种特殊的值； 不会开辟堆空间；
        out.println(o);
        out.println(r);
        out.println(names);
        out.println(date);
    }



    public void test2(){
        //局部变量不会自动初始化，使用局部变量i之前必须要手动初始化
        double weight=135;
        if(weight==135){
            out.println("Perfect weight for me");
        }

       //局部变量不会自动初始化，使用局部变量hobby之前必须要手动初始化
        //String str=null会对引用变量str(存在栈内存)赋值为null,但是不会开辟堆空间，str完成了初始化
        String[] hobby=null;
        if(hobby==null){
            out.println("Life is boring");
        }
    }
}
