package com.demo.basic.variable.classification.number;

import org.testng.annotations.Test;

import java.math.BigInteger;

/**
 * 在java中经常会遇到比较大的数，甚至超过了long型，那么该如何处理这些“大数据”呢？
 * 在java中有两个类BigInteger和BigDecimal分别表示大整数类和大浮点数类，从原则上是可以表示“天文单位”一样大的数字咯，但有一个缺点就是比较费内存！
 */
public class BigNumber {
    @Test
    public void testBigInteger() {
        BigInteger a=new BigInteger("123");
        BigInteger b=BigInteger.valueOf(123);
        BigInteger c=a.add(b);
        System.out.println("c = " + c);
    }
}
