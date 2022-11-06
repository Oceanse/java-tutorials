package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * https://www.cnblogs.com/jia0504/p/13811424.html
 * 第四步: 使用Proxy类的静态方法,创建代理对象,并把返回值转换成接口类型
 */
public class MainShop {
    public static void main(String[] args) {
        //1.创建目标对象
        UsbSell factory = new UsbKingFactory();

       //2.创建InvocationHandler对象，包含代理对象实现的功能
        InvocationHandler myHandle = new MyHandle(factory);

        //3.创建代理对象,使用Proxy类的静态方法,创建代理对象
        //第一个参数是目标对象的装载器；
        //第二个参数是目标对象的父接口
        //第三个参数是InvocationHandler对象，包含代理对象实现的功能
        UsbSell proxy = (UsbSell) Proxy.newProxyInstance(factory.getClass().getClassLoader(),
                factory.getClass().getInterfaces(),
                myHandle);

        //4.通过代理执行方法
        float price = proxy.sell(1);
        System.out.println("通过动态代理对象,调用方法:" + price);
    }
}
