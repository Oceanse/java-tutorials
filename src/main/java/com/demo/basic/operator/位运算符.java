package com.demo.basic.operator;

import org.testng.annotations.Test;

/**
 * Java定义了位运算符，应用于整数类型(int)，长整型(long)，短整型(short)，字符型(char)，和字节型(byte)等类型。
 * 位运算符作用在所有的位上，并且按位运算。
 * @author epanhai
 */
public class 位运算符 {

    /**
     * 按位与：只有两个位都是1，结果才是1，否则就为0
     * “a”的值是129，转换成二进制就是10000001
     * “b”的值是128，转换成二进制就是10000000
     * 所以结果就是10000000，即128。
     */
    @Test
    public void test(){
        int a=129;
        int b=128;
        System.out.println("a 和b 与的结果是："+(a&b));
    }


    /**
     * 按位或：两个位只要有一个为1，那么结果就是1，否则就为0
     * “a”的值是129，转换成二进制就是10000001
     * “b”的值是128，转换成二进制就是10000000
     * 结果就是10000001，即129。
     */
    @Test
    public void test2(){
        int a=129;
        int b=128;
        System.out.println("a 和b 与的结果是："+(a|b));
    }


    /**
     * 按位非：如果位为0，结果是1，如果位为1，结果是0
     * 2的源码=补码=00000010
     * 经过非运算后的补码=11111101，是负数
     * 反码=补码-1=11111100；
     * 原码=10000011=-3
     * 注意：原码和补码的二进制最高位都是符号位
     */
    @Test
    public void test3(){
        byte a=2;
        System.out.println("a 非的结果是："+(~a));
    }


    /**
     *按位异或: 两个操作数的位中，相同则结果为0，不同则结果为1。
     * a 的值是15，转换成二进制为1111，前面全是0
     * b 的值是2， 转换成二进制为0010，前面全是0
     * 结果为1101，前面全是0，  十进制是13。
     */
    @Test
    public void test4(){
        int a=15;
        int b=2;
        System.out.println("a 与 b 异或的结果是："+(a^b));
    }


    /**
     * 左移 << : 操作数的二进制补码全部往左移动X位，移出去的高位舍弃，最低位补0
     *          也就是丢弃左边指定位数，右边补0。
     * 一般来说,也就是没有发生有效位数字丢失 左移一位相当于乘以2的1次方，左移n位就相当于乘以2的n次方。
     */
    @Test
    public void test5(){
        System.out.println(10 << 1);
        System.out.println(10 << 2);
        System.out.println(10 << 3);
    }


    /**
     * >>  ：算术右移运算符，也称带符号右移。把所有的数字向右移动对应位数, 低位移出（舍弃），用最高位填充移位后左侧的空位,即正数补零，负数补1。符号位不变。
     * >>>：逻辑右移运算符，也称无符号右移,把所有的数字向右移动对应位数, 低位移出（舍弃），用0填充左侧的空位。
     * 对于正数来说 >> 和>>>相同, 右移n位，就是除以2的n次方
     * 对于负数来说不同。
     */
    @Test
    public void test6(){
        System.out.println(16 >> 1);
        System.out.println(16 >> 2);
        System.out.println(16 >> 3);
        System.out.println();
        System.out.println(16 >>> 1);
        System.out.println(16 >>> 2);
        System.out.println(16 >>> 3);
    }


}
