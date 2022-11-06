package com.demo.basic.control_statement.break_continue;

import org.testng.annotations.Test;

/**
 * continue是跳过本次循环continue后面剩下的语句，接着进行下一次循环，并不会终止循环
 * continue可以作为单独的java 语句
 * <p>
 * 与break类似的足, continue后也可以紧跟一个标签， 用f 肖接跳过标签所标识循环的当次循环的剩下语句， 重新开始下一次循环
 * @author epanhai
 */
public class ContinueDemo {

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue;//i是偶数时候跳过本次循坏的剩余代码(continue后面剩下的语句)，然后进行下一次循环
            }
            System.out.println(i);
        }
    }


    /**
     * continue是跳过本次循环continue后面剩下的语句，接着进行下一次循环
     * 所以continue放在最后是没有意义的，因为这相当于没有忽略任何语句
     */
    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if (i % 2 == 0) {
                //i是偶数时候跳过本次循坏的剩余代码(continue后面剩下的语句)，然后进行下一次循环
                //continue放在最后是没有意义的，因为这相当于没有忽略任何语句
                continue;
            }
        }
    }



}