package com.demo.IO.io.buffer;

import org.testng.annotations.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author epanhai
 */
public class BufferedOutputStreamDemo {
    /**
     * BufferedOutputStream(OutputStream out)： 创建一个新的缓冲输出流
     */
    @Test
    public void test() {
        //文件不存在会在工程根目录下被创建,如果有这个文件，会清空这个文件的数据
        File f = new File("testResource\\test.txt");
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            //fos指向被写文件,如果有这个文件，
            fos = new FileOutputStream(f, true);
            bos = new BufferedOutputStream(fos);
            //从指定的字节数组写入此输出流，然后输出流流向文件
            bos.write("asdfghj".getBytes("utf-8"), 0, 4);
            bos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {//关闭流，释放内存资源
                bos.close();//后打开的先关闭
                fos.close();//先打开的后关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
