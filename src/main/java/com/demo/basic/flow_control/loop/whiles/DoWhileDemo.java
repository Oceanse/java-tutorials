package com.demo.basic.flow_control.loop.whiles;

import org.testng.annotations.Test;

/**
 * 初始条件
 * do {
 * 循环体;
 * } while(条件表达式) ;
 * <p>
 * do-while循环结构会先执行循环体，然后再判断表达式的值
 * do-while循环的循环体至少执行一次。
 */
public class DoWhileDemo {


    @Test
    public void testDoWhile() {
        int i = 10;
        while (i > 100) {
            System.out.println("i = " + i);
        }
    }


    @Test
    public void testDoWhile2() {

        int num = 10;
        do {//至少执行一次
            System.out.println("num = " + num);
        }
        while (num > 100);
    }


    @Test
    public void testDoWhile3() {
        int i = 10;
        int num;//do-while循环保证num会被实例化
        do {
            num = 100;
        }
        while (i < 0);
        System.out.println(num);
    }


    @Test
    public void testDoWhile4() {
        int i = 100;
        int num;//while循环体可能不会被执行
        while (i > 10){
            num = 200;
        }
       // System.out.println(num);//Variable 'num' might not have been initialized
    }


}
