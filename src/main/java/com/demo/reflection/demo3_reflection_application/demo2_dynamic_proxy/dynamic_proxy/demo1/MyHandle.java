package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.dynamic_proxy.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 第三步: 创建InvocationHandler实现类.在invoke()方法中完成代理对象执行的功能
 *
 */
public class MyHandle implements InvocationHandler {

    /**
     * 目标类对象,传入什么类型目标对象，就创建该类型的目标对象
     */
    private Object target;

    /**
     * 传入是谁的对象,就给谁创建代理
     * @param target
     */
    public MyHandle(Object target) {
        this.target = target;
    }

    /**
     * invoke()方法是代理对象执行的功能
     * 1.调用目标的方法
     * 2.增强功能
     *
     * @param proxy jdk创建的代理对象，和MainShop中创建的代理对象proxyObject是同一个对象，在invoke方法中基本不用，无需处理
     * @param method 目标方法对象，代表目标类中的方法
     * @param args   目标方法参数，代表目标方法的参数
     *
     * @return 接口方法的返回值，也就是目标方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("pre process...");
        Object price = method.invoke(target, args);//返回值就是目标类方法的返回值，但是需要强制转型
        System.out.println("price: "+price);
        System.out.println("post process...");
        return price;
    }
}
