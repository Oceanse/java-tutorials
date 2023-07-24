package com.demo.reflection.demo3_reflection_application.demo2_dynamic_proxy.dynamic_proxy.demo3;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;


/**
 * https://blog.csdn.net/NCS123456/article/details/125225705
 * https://baijiahao.baidu.com/s?id=1766153190360463618&wfr=spider&for=pc
 *
 * CGLIB通过继承目标类，创建他的子类，在子类当中重写父类的相关方法，实现功能的增强。
 *
 * CGLIB是一个用于生成代理类的开源库，和JDK动态代理都能在运行时生成代理类，还存在其他一些第三方类库，如Javassist和AspectJ，它们不仅可以在运行时生成代理类
 *
 * CGLIB vs jdk动态代理
 * 接口要求：JDK动态代理要求被代理的类实现某个接口, CGLIB可以代理那些没有实现接口的类
 * 执行速度：CGLIB在目标方法的执行速度上更快。这是因为CGLIB采用了一种称为FastClass的机制，
 * 它通过生成的代理类直接调用目标方法，避免了一些额外的方法调用，从而提高了执行效率。相比之下，
 * JDK动态代理需要通过反射调用目标方法，会引入一些性能开销。
 *使用限制： CGLIB通过生成继承目标类生成其子类来实现代理，因此目标类和目标方法不能被声明为final，否则CGLIB无法生成代理类。
 *
 * Spring框架的AOP模块在实现上使用了JDK动态代理和CGLIB两种技术。对于实现了接口的类，Spring默认使用JDK动态代理来生成代理类；
 * 而对于没有实现接口的类，Spring会使用CGLIB来生成代理类。这样，Spring能够根据目标类的特性选择最合适的代理方式。
 *
 */
public class CglibTest {
    public static void main(String[] args) {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码，会在code目录打印cglib动态生成的代码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./code");


        LogInterceptor logInterceptor = new LogInterceptor();
        Enhancer enhancer = new Enhancer();
        //设置代理类的超类，也就是目标类，cglib是通过继承来实现的
        enhancer.setSuperclass(UserDao.class);
        enhancer.setCallback(logInterceptor);

        //生成代理对象
        UserDao proxyDao = (UserDao)enhancer.create();   // 创建代理类
        proxyDao.update();
        proxyDao.select();
    }
}
