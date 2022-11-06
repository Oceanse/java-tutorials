package com.demo.IO.otherstream.bytearray_stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 内存字节流
 * ByteArrayInputStream 和 ByteArrayOutputStream 是用来完成内存的输入和输出功能。
 * 内存操作流一般在生成一些临时信息时才使用。 如果临时信息保存在文件中，还需要在有效期过后删除文件，这样比较麻烦。
 * @author epanhai
 */
public class ByteArrayStreamDemo {

    public static void main(String[] args) {
        // 定义一个字符串，全部由大写字母组成
        String str = "HELLOWORLD";

        try(ByteArrayInputStream bis = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            // 准备从内存ByteArrayInputStream中读取内容
            int temp = 0;
            while ((temp = bis.read()) != -1) {
                // 读取的数字变为字符
                char c = (char) temp;
                // 将字符变为小写
                bos.write(Character.toLowerCase(c));
            }
            // 所有的数据就全部都在ByteArrayOutputStream中
            String newStr = bos.toString();
            System.out.println(newStr);
        }catch (IOException e) {
            e.printStackTrace();
        }


    }
}
