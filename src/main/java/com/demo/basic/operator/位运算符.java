package com.demo.basic.operator;

import org.testng.annotations.Test;

/**
 * Java定义了位运算符，应用于整数类型(int)，长整型(long)，短整型(short)，字符型(char)，和字节型(byte)等类型
 * 位运算符作用在所有的位上，并且按位运算
 * 位运算符的运算过程都是基于补码运算
 * @author epanhai
 */
public class   位运算符 {

    /**
     * 按位与：只有两个位都是1，结果才是1，否则就为0
     * “a”的值是129，原码=补码=10000001(高位0补齐)
     * “b”的值是128，原码=补码=10000000(高位0补齐)
     * 所以结果就是10000000(高位0补齐)，即128。
     */
    @Test
    public void test(){
        int a=129;
        int b=128;
        System.out.println("a和b与的结果是："+(a&b));
    }


    /**
     * 按位或：两个位只要有一个为1，那么结果就是1，否则就为0
     * “a”的值是129，源码=补码=10000001(高位0补齐)
     * “b”的值是128，源码=补码=10000000(高位0补齐)
     * 结果就是10000001(高位0补齐)，即129。
     */
    @Test
    public void test2(){
        int a=129;
        int b=128;
        System.out.println("a和b或的结果是："+(a|b));
    }


    /**
     * 按位取反：如果位为0，结果是1，如果位为1，结果是0，这路不区分符号位和数据位
     * 2的源码=补码=00000010
     * 按位取反后=11111101，这就是补码运算后计算机底层存储的的结果，注意这里仍然是补码，因为计算机底层存储的就是补码，最高位是1，因此是负数，
     *
     * 通过补补得原(补码的补码是原码)，我们可求得其原码
     * 首先符号位不变，其余取反： 10000010， 注意求反码是区分符号位和数据位的
     * 然后整体加1：10000011，最高位是符号位，剩余是数据位，对应的十进制就是-3
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
     * 结果为1101，前面全是0，  对应的源码十进制=13。
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
     *          因为位运算比×运算快，所以可对一下代码进行优化
     *          优化前： x=x×2;
     *          优化后：x=x<<1;
     * 一般来说,也就是没有发生有效位数字丢失，左移n位就相当于乘以2的n次方，对于无符号数、有符号数的正数或负数，均成立
     * 这种数值变化关系只是适用于移位后被舍弃的所有高位里面没有1，不然的话，移位的过程就会有溢出。
     */
    @Test
    public void test5(){
        System.out.println(10 << 1);
        System.out.println(10 << 2);
        System.out.println(10 << 3);
        System.out.println(-10 << 1);
        System.out.println(-10 << 2);
        System.out.println(-10 << 3);
    }


    /**
     * >>: 算术右移运算符，也称带符号右移。把所有的数字向右移动对应位数, 低位移出（舍弃），最高位补符号位，也就是 正数补0，负数补1
     * >>>: 逻辑右移运算符，也称无符号右移,把所有的数字向右移动对应位数, 低位移出（舍弃），高位补0
     * 对于正数来说 >> 和>>>相同, 右移n位，就是除以2的n次方
     * 对于负数来说不同。
     */
    @Test
    public void test6(){
        //算数右移：对正数来说，算术右移=逻辑右移
        System.out.println(16 >> 1);
        System.out.println(16 >> 2);

        //算数右移中，补充的最高位和符号位一致，所以数据的正负不会改变
        System.out.println(-16 >> 1);
        System.out.println(-16 >> 2);
        System.out.println();

        //逻辑右移：对正数来说，算术右移=逻辑右移
        System.out.println(16 >>> 1);
        System.out.println(16 >>> 2);

        // 低位移出（舍弃），高位补0
        System.out.println(-16 >>> 1);
        System.out.println(-16 >>> 2);
    }

    @Test
    public void test7(){
        int i=-1;
        System.out.println(Integer.toBinaryString(i));
        i>>>=10;
        System.out.println(Integer.toBinaryString(i));
        System.out.println();

        long l=-1;
        System.out.println(Long.toBinaryString(l));
        l>>>=10;
        System.out.println(Long.toBinaryString(l));
        System.out.println();;

    }
}
