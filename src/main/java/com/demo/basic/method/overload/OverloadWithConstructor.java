package com.demo.basic.method.overload;

/**
 * 构造器重载
 * @author epanhai
 */
public class OverloadWithConstructor {

    String msg;
    int code;

    public OverloadWithConstructor() {
    }

    public OverloadWithConstructor(String msg) {
        this.msg = msg;
    }

    public OverloadWithConstructor(int code) {
        this.code = code;
    }

    public OverloadWithConstructor(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
