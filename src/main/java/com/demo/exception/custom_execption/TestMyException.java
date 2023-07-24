package com.demo.exception.custom_execption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class TestMyException {

    private static final Logger LOG = LogManager.getLogger(TestMyException.class);

    @Test
    public void test() {
        try {
            throw new MyException("自定义编译时异常");//MyException是编译时异常,必须显示的处理
        } catch (MyException e) {
            LOG.error(e);
        }
    }


    /**
     * MyException2是运行时异常,可以暂时不处理
     */
    public void test2() {
        throw new MyException2("自定义运行时异常");
    }


    /**
     * MyException2是运行时异常,并进行catch处理
     */
    public void test3() {
        try {
            throw new MyException2("自定义运行时异常");
        } catch (MyException2 e) {
            //LOG.error(e);
            throw new MyException2(e);
        }
    }


    public void test4() {
        try {
            throw new MyException2("自定义运行时异常");
        } catch (MyException2 e) {
            //LOG.error("log 打印error:",e);
            throw new MyException2("自定义运行时异常");
        }
    }


    public void test5() {
        try {
            throw new MyException2("自定义运行时异常");
        } catch (MyException2 e) {
            //LOG.error("log 打印error:",e);
            throw new MyException2("这是自定义运行时异常", e);
        }
    }


    public static void main(String[] args) {

        TestMyException t = new TestMyException();
        // t.test();
        //t.test2();
        //t.test5();
        //t.test2_2();


    }
}