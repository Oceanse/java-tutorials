package com.demo.basic.operator;

import org.testng.annotations.Test;

/**
 * 逻辑运算符(Logical operator)用于连接两个结果布尔类型的变量/字面量/表达式，用于执行多个判断，
 * 判断的结果是 true 或 false。
 */
public class LogicalOperatorDemo {

    @Test
    public void testAndOr() {
        // &&和||短路
        // 所谓短路，就是当参与运算的一个操作数已经足以推断出这个表达式的值的时候，另外一个操作数（有可能是表达式）就不会 执行

        int i = 1;
        boolean b = false;
        if ((b) & (i++) > 0) {//i++会被执行
        }
        System.out.println("i: " + i);//2


        int i2 = 2;
        boolean b2 = false;
        if ((b2) && (i2++ > 0)) {//i2++不会被执行
        }
        System.out.println("i2: " + i2);//2


        int i3 = 3;
        boolean b3 = true;
        if ((b3) | (i3--) > 0) {
        }
        System.out.println("i3: " + i3);//2


        int i4 = 2;
        boolean b4 = true;
        if ((b4) || (i4--) > 0) {
        }
        System.out.println("i4: " + i4);//2
    }


    /**
     * !单目运算符，只需要一个操作数；操作数是结果为布尔类型的变量/常量/布尔表达式
     * 操作数为true,则返回false;操作数为false,则返回true
     */
    @Test
    public void test非() {
        boolean flag = true;
        System.out.println(!flag);
        System.out.println("!true: " + !true);
        System.out.println("!(2>1): " + !(2 < 1));
    }


    /**
     * 算数异或详见算数运算符
     * 布尔异或:两个操作数不同时返回true,相同时返回false
     */
    @Test
    public void test异或() {
        System.out.println('b' > 'a' ^ 'd' > 'c');
        System.out.println('a' > 'b' ^ 'c' > 'd');
        System.out.println('b' > 'a' ^ 'c' > 'd');
        System.out.println('a' > 'b' ^ 'd' > 'c');
    }


    /**
     * 逻辑运算符优先级高于关系运算符，但是依然建议使用括号来明确优先级
     */
    @Test
    public void testPriority() {
        int x = 1, y = 2, z = 3;
        System.out.println(y > x && z > y);
        System.out.println((y > x) && (z > y));//使用括号来明确优先级
    }
}
