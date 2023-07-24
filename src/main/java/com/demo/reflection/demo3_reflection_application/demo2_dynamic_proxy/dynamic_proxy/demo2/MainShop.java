package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.dynamic_proxy.demo2;

import com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.dynamic_proxy.demo1.MyHandle;
import com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.dynamic_proxy.demo1.UsbKingFactory;
import com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.dynamic_proxy.demo1.UsbSell;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * https://www.cnblogs.com/jia0504/p/13811424.html
 * 第四步: 使用Proxy类的静态方法,创建代理对象,并把返回值转换成接口类型
 */
public class MainShop {
    public static void main(String[] args) {

        //目标对象
        UsbSell factory = new UsbKingFactory();

        //3.通过代理工厂创建代理对象,使用Proxy类的静态方法,创建代理对象
        //第一个参数是代理对象的装载器，可以通过目标对象来获取，个人理解他们两个拥有相同的类加载器
        //第二个参数是代理对象的父接口，代理对象和目标对象要实现相同的接口，所以可以通过目标对象来获取
        //第三个参数是InvocationHandler对象，包含代理对象实现的功能=目标对象基本功能+增强功能
       UsbSell proxyObject = (UsbSell) Proxy.newProxyInstance(
               factory.getClass().getClassLoader(),
               factory.getClass().getInterfaces(),
               new InvocationHandler() {
                   @Override
                   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                       System.out.println("pre process...");
                       Object price = method.invoke(factory, args);//返回值就是目标类方法的返回值，但是需要强制转型
                       System.out.println("price: "+price);
                       System.out.println("post process...");
                       return price;
                   }
               });

        //4.通过代理对象执行方法
        float price = proxyObject.sell(1);
        System.out.println("通过动态代理对象,调用方法:" + price);
    }
}
