package com.demo.basic.method.overload;

/**
 * 静态方法main方法的重载
 * @author epanhai
 */
public class OverloadWithStaticMethod {

    /**
     * JVM只会调用main(String[] args)
     * @param args
     */
    public static void main(String[] args) {
        main('\u0022');
        main("ocean");
    }

    public static void main(char c) {
        System.out.println(c);
    }
    public static void main(String name) {
        System.out.println(name);
    }
}
