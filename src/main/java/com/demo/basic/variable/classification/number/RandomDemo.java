package com.demo.basic.variable.classification.number;

import org.testng.annotations.Test;

import java.util.Random;

public class RandomDemo {

    /**
     * 生成[0,1)之间的随机数
     */
    @Test
    public void testRandom() {
        Random random=new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextDouble());
        }
    }

    /**
     * Math.random(), 生成 [0,1)之间的随机数
     */
    @Test
    public void testRandom2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Math.random());
        }
    }


    /**
     * 生成[0,100)之间的随机数
     */
    @Test
    public void testRandom3() {
        Random random=new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(100));
        }
    }

    /**
     * 生成[0,100]之间的随机数
     */
    @Test
    public void testRandom4() {
        Random random=new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(101));
        }
    }


}
