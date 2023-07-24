package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.dynamic_proxy.demo3;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 如果直接在USerDao上添加日志代码会存在以下问题：
 * 一是需要添加大量重复代码，
 * 二是代码耦合度高。
 *
 */
public class LogInterceptor implements MethodInterceptor {
    /**
     *
     * @param object 代理对象，而不是目标对象
     * @param method 目标对象中的方法
     * @param objects  目标对象中方法的参数，数组表示参数列表，基本数据类型需要传入其包装类型
     * @param methodProxy 代理对象中代理方法对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        // 注意这里是调用 invokeSuper 而不是 invoke，否则死循环栈溢出，methodProxy.invokeSuper执行的是原始类的方法，method.invoke执行的是子类的方法
        Object result = methodProxy.invokeSuper(object, objects);
        after();
        return result;
    }

    private void before() {
        System.out.println(String.format("log start time [%s] ", new Date()));
    }
    private void after() {
        System.out.println(String.format("log end time [%s] ", new Date()));
    }
}
