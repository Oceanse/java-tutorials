package com.demo.basic.operator;

import org.testng.annotations.Test;

public class ArithmeticalOperatorDemo {


    /**
     * 算数运算符：Arithmetic Operator
     * + - * / % ++ --
     * -除了作为减号外，还可以是求负运算符
     */
    @Test
    public void test1() {

        int i = 1;
        int i2 = 2;

        //结果和参与运算变量类型一致
        System.out.println("i + i2=" + (i + i2));
        System.out.println("i - i2=" + (i - i2));
        System.out.println("i * i2=" + (i * i2));
        System.out.println("i / i2=" + (i / i2));//0
        System.out.println();

        System.out.println("10*10/5+3-1*4/2=" + (10 * 10 / 5 + 3 - 1 * 4 / 2));//先乘除后加减

        double d = 1.0;
        System.out.println(d / 2);//参与运算的操作数类型需要保持一致，因此int类型2先自动转换为double类型，然后两个double类型相除
    }


    @Test
    public void test1_2() {
        //只有浮点数除以0才会得到正无穷大或者负无穷大
        //被除数或者除数有一个是小数，那么被除数除数都会自动转成浮点数
        System.out.println(1 / 0.0);//Infinity
        System.out.println(-1.0 / 0);//-Infinity

        //正数除以0会产生异常：ArithmeticException: / by zero
        System.out.println(1/0);
    }


    /**
     * 取模运算
     */
    @Test
    public void test2() {
        //取模运算符号看左边
        System.out.println(5 % 2);
        System.out.println(5 % -2);
        System.out.println(-5 % 2);
        System.out.println(-5 % -2);

        //1~100内偶数的和和数量
        int i = 0;
        int sum = 0;
        int count = 0;
        for (i = 1; i < 101; i++) {
            if (i % 2 == 0) {
                sum += i;
                count++;
            }
        }
        System.out.println(String.format("1~100内偶数的和:%d, 数量是%d", sum, count));
    }


    /**
     * ++ --都是单目运算符(Unary Operator)，只能有一个操作数
     * ++a,--a： 先进行自增或者自减，再运算；
     * a++,a--: 先运算，再进行自增或者自减
     * <p>
     */
    @Test
    public void test3() {

        int i = 1;
        int i2 = 2 * i++;
        System.out.println("i=" + i);//2
        System.out.println("i2=" + i2);//2
        System.out.println();

        int i3 = 3;
        int i4 = 2 * ++i3;
        System.out.println("i3=" + i3);//4
        System.out.println("i4=" + i4);//8
        System.out.println();

        int a = 10;
        int b = 10;
        int c = 10;
        System.out.println(a++ + a++);//10+11
        System.out.println(++b + ++b);//11+12
        System.out.println(++c + c++);//11+11


    }


    /**
     *  自增自减可以修改变量存储的变量值(赋值修改，自增自减修改)
     * 只能怪对变量进行自增自减，不能对常量值或者结果为常量值的表达式进行自增自减
     */
    @Test
    public void test3_2() {
        //int i5=1++;  不能对常量值或进行自增自减

        int a = 1;
        int b = 2;
        //(a+b)++;  a+b的结果是常量值，不能对结果为常量值的表达式进行自增自减(本质也是不能对常量值或进行自增自减)

        //赋值表达式的结果是最左边变量的值，也就是赋值表达式的结果也是一个常量值，所以不能对赋值表达式进行自增自减(本质也是不能对常量值或进行自增自减)
        int i;
        System.out.println(i = 100);
        //System.out.println((i=100)++);
    }


    /**
     * 单目运算符：-
     */
    @Test
    public void test4() {
        double salary = 30000.0;
        System.out.println(-salary);
        System.out.println(-(-salary));
        System.out.println(-(salary + 10000));
    }


    /**
     * java.lang.Math 类提供的工具方法可以完成求幂、开方、绝对值等运算
     */
    @Test
    public void test5() {
        System.out.println(Math.abs(-1.2));//|-1.2|
        System.out.println(Math.pow(2, 3));//2^3
        System.out.println(Math.sqrt(4));//2.0
        System.out.println(Math.random());//[0.0,1.0)随机数
        System.out.println(Math.random() * 100 + 1);//[1,101),也就是[1,100]
    }
}
