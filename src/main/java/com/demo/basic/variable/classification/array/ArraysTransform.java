package com.demo.basic.variable.classification.array;

import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class ArraysTransform {


    /**
     * String<---->char[]
     * String<---->byte[]
     */
    @Test
    public void test() throws UnsupportedEncodingException {
        char[] bless = "Godblessmom".toCharArray();
        System.out.println(Arrays.toString(bless));

        char[] bless2 = "God bless mom".toCharArray();
        System.out.println(Arrays.toString(bless2));

        byte[] bless3 = "abc".getBytes();//相当于使用数组存储字符序列的编码
        System.out.println(Arrays.toString(bless3));

        //字符/字节数组--->String
        System.out.println(new String(bless));
        System.out.println(new String(bless2));
        System.out.println(new String(bless3));
    }


    /**
     * Array--->List
     */
    @Test
    public void test2() {
        String[] fruit = new String[]{"apple", "banana", "grape"};
        List<String> fruits = Arrays.asList(fruit);
    }

}
