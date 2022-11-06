package com.demo.net;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class InetAddressDemo {
    public static void main(String[] args)throws Exception {
        //一个InetAddress对象代表一个ip Address
        InetAddress inet = InetAddress.getByName("www.baidu.com");
        System.out.println(inet);
        //返回百度服务器的IP地址
        System.out.println(inet.getHostAddress());
        //域名
        System.out.println(inet.getHostName());
    }


    @Test
    public void test() throws IOException {
        InetAddress inet = InetAddress.getByName("localhost");
        System.out.println(inet);
        //返回主机的IP地址
        System.out.println(inet.getHostAddress());
        //返回主机的域名
        System.out.println(inet.getHostName());
    }

}
