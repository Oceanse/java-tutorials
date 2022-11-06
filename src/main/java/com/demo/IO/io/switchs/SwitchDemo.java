package com.demo.IO.io.switchs;

import org.testng.annotations.Test;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;


/**
 * An InputStreamReader is a bridge from byte streams to character streams
 * It reads bytes and decodes them into characters using a specified charset.
 * The charset that it uses may be specified by name or may be given explicitly,
 * or the platform's default charset may be accepted.
 * <p>
 * 1，源或者目的对应的设备是字节流，但是操作的却是文本数据，可以使用转换作为桥梁。提高对文本操作的便捷。
 * 2，一旦操作文本涉及到具体的指定编码表时，必须使用转换流 。
 * <p>
 * InputStreamReader就是把InputStream转换成Reader
 * @author epanhai
 */
public class SwitchDemo {


    @Test
    public void test() throws IOException {

        InputStream inputStream = new FileInputStream("test.txt");

        //InputStreamReader类是从字节流到字符流的桥接器,做的逻辑是根据指定的编码方式把字节流转成字符流，inputStreamReader就是解码后的字符流
        //转换编码要与读取文件的编码保持一致，否则会出现乱码
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str;
        //可以按行读取数据，推测理解为把上面的解码的数据按行读取
        //如果没有读到换行符，程序就会阻塞，直到读到换行符；
        //linux下换行符是\n;   windows中的换行符是\r\n，
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
    }


    @Test
    public void test2() throws IOException {
        URI uri = URI.create("http://wwww.baidu.com");
        URL url = uri.toURL();
        InputStream inputStream = url.openStream();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str;
        //可以按行读取数据，推测理解为把上面的解码的数据按行读取
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
    }


    @Test
    public static void test3() throws IOException {
        //System.in是标准输入流，默认从键盘输入；
        //将字节输入流转换成字符输入流
        InputStreamReader isr = new InputStreamReader(System.in);

        //构建缓冲字符输入流BufferedReader(Reader in)
        try (BufferedReader bufferedReader = new BufferedReader(isr)) {
            System.out.println("please input the data: ");
            String str;
            //可以按行读取数据，推测理解为把上面的解码的数据按行读取
            while ((str = bufferedReader.readLine()) != null) {
                if("exit".equals(str)){
                    System.exit(0);
                }else{
                    System.out.println(str);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        test3();
    }

}
