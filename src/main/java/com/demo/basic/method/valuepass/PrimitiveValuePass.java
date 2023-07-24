package com.demo.basic.method.valuepass;

import org.testng.annotations.Test;

/**
 * Java里方法的参数传递方式只有一种：值传递。所谓们传递，就是将实际参数值的副本（复制品）传入力法内，而参数本身不会受到任何影响。
 * 基本类型参数的传递，是把字面值进行复制然后传给被调函数。双方各自的后续修改，互不影响。
 * @author epanhai
 */
public class PrimitiveValuePass {

    @Test
    public void testAddOne(){
        //这里把i换成m就特别好理解了
        int i = 10;
        //基本数据类型是字面值传递，相当于把i的字面值复制一份传递给addOne方法
        addOne(i);
        System.out.println("test: " + i);
    }


    public static void addOne(int i) {
        i++;
        System.out.println("addOne：" + i);
    }



    @Test
    public void testSwap(){
        int i = 10;
        int j=20;
        //基本数据类型是字面值传递，相当于把i和j的字面值复制一份传递给addOne方法
        swap(i,j);
        System.out.println("test: "+i+" "+j);
    }


    public static void swap(int i, int j) {
        System.out.println("swap: "+i+" "+j);
        int temp=i;
        i=j;
        j=temp;
        System.out.println("swap: "+i+" "+j);
    }



}

