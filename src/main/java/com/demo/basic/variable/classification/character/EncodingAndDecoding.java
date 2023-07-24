package com.demo.basic.variable.classification.character;

import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Java虚拟机规范中明确说明了java的char类型使用的编码方案是UTF-16
 * UTF-16 的编码长度要么是2个字节（U+0000 到 U+FFFF），要么是4个字节（U+010000 到 U+10FFFF）
 * Java中常见的char基本位于unicode表的BMP平面，全部用双字节(16位二进制)表示，使用unicode码点(编号)的16位二进制(不足用0补齐)作为存储数值，简单来说就是字符编号就是其存储编码.
 * <p>
 * UTF-16BE、UTF-16LE、UTF-16 三者之间的区别
 * UTF-16BE，其后缀是 BE 即 big-endian，大端的意思。大端就是将高位的字节放在低地址表示。
 * UTF-16LE，其后缀是 LE 即 little-endian，小端的意思。小端就是将高位的字节放在高地址表示。
 * ASCII码及扩展ASCII码：https://www.cnblogs.com/yuyanc/p/16288384.html
 */
public class EncodingAndDecoding {


    @Test
    public void testCodingAndEncoding() throws UnsupportedEncodingException {
        String str = "a中";
        //编码操作GBK编码：中文每个字符占用2个字节,英文1个字节
        byte[] byteArray = str.getBytes("GBK");
        System.out.println(Arrays.toString(byteArray));//[97, -42, -48]

        //编码操作 UTF-16编码
        byte[] byteArray2 = str.getBytes(StandardCharsets.UTF_16BE);
        System.out.println(Arrays.toString(byteArray2));//[0, 97, 78, 45]

        //编码操作 UTF-8编码
        byte[] byteArray3 = str.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(byteArray3));//[97, -28, -72, -83]

        //编码操作 使用平台默认字符集编码，得到的结果和byteArray3相同，进一步证明是平台默认字符集是UTF-8
        System.out.println(Charset.defaultCharset());//UTF-8
        byte[] byteArray4 = str.getBytes();
        System.out.println(Arrays.toString(byteArray4));//[97, -28, -72, -83]
    }

    /**
     * 信息在计算机中的存储和传输是二进制形式，那么久需要认为把字符按照某种规则转化成二进制，这就是编码的过程。
     * 计算机接收了这个编码，如何让使用者认识呢？那必须要将二进制编码转换为人所识别的字符形式，这就是解码的过程。
     * <p>
     * 编码：将字符串转换为 byte 数组
     * 解码：把 byte 数组转换为 字符串
     * GBK是GB2312的扩展，可以兼容GB2312， GB2312标准共收录6763个汉字，GBK共收入21886个汉字和图形符号；
     */
    @Test
    public void testCodingAndEncoding2() throws UnsupportedEncodingException {
        String str = "china中国";
        //编码操作 GBK编码
        byte[] byteArray = str.getBytes("GBK");
        System.out.println(Arrays.toString(byteArray));//[99, 104, 105, 110, 97, -42, -48, -71, -6]

        //解码操作 编码和解码格式一致：GBK解码
        //注意编码的字符集和解码的字符集格式必须一致（是其扩展字符集也可以），否则会乱码
        String str2 = new String(byteArray, "GBK");
        System.out.println("str2 = " + str2);

        //解码操作 编码和解码格式不一致：ASCII解码
        //注意编码的字符集和解码的字符集格式必须一致（是其扩展字符集也可以），否则会乱码
        String str3 = new String(byteArray, "ASCII");//ASCII范围是0~127，-42, -48, -71, -6无法被解码
        System.out.println("str3 = " + str3);
    }


    /**
     * FileInputStream---> InputStreamReader ---------->BufferedReader
     * FileInputStream--->InputStreamReader的过程是字节流转化成字符流的过程，是解码的过程，可以指定编码格式
     *
     * @throws IOException
     */
    @Test
    public void testCodingAndEncoding3() throws IOException {

        //字节输入流,文件采用utf-8编码
        FileInputStream fis = new FileInputStream("pom.xml");
        //这里的isr可以理解成是字符流，字节流--->字符流就是解码的过程，在对字节流进行解码的时候可以指定编码集或者编码规则
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        //字符流包装成缓冲流
        BufferedReader br = new BufferedReader(isr);

        String readContent;
        //Reads a line of text
        while ((readContent = br.readLine()) != null) {
            System.out.println(readContent);
        }
        //关闭时候只需要关闭最外层的包装流(装饰者模式)
        br.close();//关闭流，释放内存资源
    }
}
