package com.demo.IO.others.otherstream.合并流;

import java.io.*;

/**
 * @author epanhai
 */
public class SequenceInputStreamDemo {
    public static void main(String[] args) throws Exception {

        InputStream is1 = new FileInputStream("temp1.log");
        InputStream is2 = new FileInputStream("temp2.log");
        SequenceInputStream sis = new SequenceInputStream(is1, is2);

        int temp = 0;
        OutputStream os = new FileOutputStream("temp3.logt");
        while ((temp = sis.read()) != -1) {
            os.write(temp);
        }

        sis.close(); // 关闭合并流
        is1.close(); // 关闭输入流1
        is2.close(); // 关闭输入流2
        os.close(); // 关闭输出流
    }
}
