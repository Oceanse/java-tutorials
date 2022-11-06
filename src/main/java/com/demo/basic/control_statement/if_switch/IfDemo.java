package com.demo.basic.control_statement.if_switch;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * if语句使用布尔表达式或布尔值作为分支条件来进行分支控制；每次只能执行一个分支
 * 执行代码块可以是一条语句也可以是多条语句。如果仅有一条语句，可省略条件语句中的大括号 {}。从编程规范角度不要省略大括号，省略大括号会使程序的可读性变差。
 * <p>
 * 1.   如果条件正确则执行条件执行体1，另外无论条件正确与否，一定会执行执行语句2；
 * if(boolean expression){
 * 条件执行体1；
 * }
 * 执行语句2；
 * <p>
 * <p>
 * 2.  如果布尔表达式=true则执行条件执行体1；否则执行条件执行体2； 每次只能执行一个操作
 * if(布尔表达式){
 * 条件执行体1；
 * }
 * else{
 * 条件执行体2；
 * }
 * <p>
 * <p>
 * 3.  提供多个执行分支,只能一个分支被执行
 * if(条件表达式){
 * 执行代码块；
 * }
 * else if (布尔表达式){
 * 执行代码块；
 * }
 * ……
 * else{
 * 执行代码块；
 * }
 */
public class IfDemo {


    /**
     * if(boolean condition){
     * 条件执行体；
     * }
     * <p>
     * 单行代码块可以省略{}，但是不建议这么做，即使条件执行体只有一行代码；
     * 因为{}使得代码具有更好的可读性
     */
    @Test
    public void test1_1() {
        // 用户名
        String username = "admin";
        // 密码
        String userpass = "123456";
        // 验证码
        String code = "0000";
        if (username != "admin" || userpass != "123456" || code != "0000") {
            System.out.println("登录失败！");
            System.out.println("请检查输入的用户名、密码和验证码是否正确！");
            return;
        }
        if (username == "admin" && userpass == "123456" && code == "0000") {
            System.out.println("登录成功！");
        }
    }


    /**
     * if(boolean condition){
     * 条件执行体；
     * }
     * <p>
     * 单行代码块可以省略{}，但是不建议这么做，即使条件执行体只有一行代码；
     * 因为{}使得代码具有更好的可读性
     */
    @Test
    public void test1_2() {
        List<String> all = Arrays.asList("A.java", "B.java", "A.class", "B.class", "a.c");
        List<String> javaSourceFile = new ArrayList<>();
        List<String> javaClassFile = new ArrayList<>();
        List<String> cSourceFile = new ArrayList<>();
        for (String item : all) {
            if (item.endsWith("java"))
                //if语句块中只包含一条语句，可以省略大括号,从编程规范角度不要省略大括号，省略大括号会使程序的可读性变差。
                javaSourceFile.add(item);
            if (item.endsWith("class"))
                //if语句块中只包含一条语句，可以省略大括号,从编程规范角度不要省略大括号，省略大括号会使程序的可读性变差。
                javaClassFile.add(item);
            if (item.endsWith("c"))
                //if语句块中只包含一条语句，可以省略大括号,从编程规范角度不要省略大括号，省略大括号会使程序的可读性变差。
                cSourceFile.add(item);
        }
        System.out.println("javaSourceFile=" + javaSourceFile);
        System.out.println("javaClassFile=" + javaClassFile);
        System.out.println("cSourceFile=" + cSourceFile);
    }

    /**
     * if(boolean condition){
     * 条件执行体；
     * }
     * if 语句可以用switch替换
     */
    @Test
    public void test1_3() {
        List<String> all = Arrays.asList("A.java", "B.java", "A.class", "B.class", "a.c");
        List<String> javaSourceFile = new ArrayList<>();
        List<String> javaClassFile = new ArrayList<>();
        List<String> cSourceFile = new ArrayList<>();
        for (String item : all) {
            switch (item.substring(item.lastIndexOf(".") + 1)) {
                case "java":
                    javaSourceFile.add(item);
                    break;
                case "class":
                    javaClassFile.add(item);
                    break;
                case "c":
                    cSourceFile.add(item);
                    break;
                default:
                    System.out.println("文件格式不正确");
            }
        }
        System.out.println("javaSourceFile=" + javaSourceFile);
        System.out.println("javaClassFile=" + javaClassFile);
        System.out.println("cSourceFile=" + cSourceFile);
    }


    /**
     * if(条件表达式){
     * 条件执行体1；
     * }
     * else{
     * 条件执行体2；
     * }
     * <p>
     * 非你即我
     */
    @Test
    public void test2() {
        double totalPrice = 100;
        int amount = 0;
        double unitPrice;
        if (amount != 0) {
            //if语句块中只包含一条语句，可以省略大括号,从编程规范角度不要省略大括号，省略大括号会使程序的可读性变差。
            unitPrice = totalPrice / amount;
        } else {
            System.out.println("The amount cannot be zero");
            //若amount==0, 执行到这里会直接退出整个方法(if语句中出现return直接退出所在if所在方法)
            return;
        }
        //只有if被执行时候这句才会被执行，所以不违背变量初始化之后才能使用的原则
        System.out.println("unitPrice is " + unitPrice);
    }

    /**
     * if(条件表达式){
     * 条件执行体1；
     * }
     * else{
     * 条件执行体2；
     * }
     */
    @Test
    public void test2_2() {
        int year = 2020;
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            System.out.println("LEAP YEAR");
        } else {
            System.out.println("COMMON YEAR");
        }
    }


    /**
     * if/else语句块中只包含一条语句，可以省略大括号,从编程规范角度不要省略大括号，省略大括号会使程序的可读性变差。
     */
    @Test
    public void test2_3() {
        int b = 5;
        if (b > 4)
            System.out.println("b大于4");
        else
            System.out.println("b小于4等于");
            //这条语句不属于else语句
            System.out.println("我总是会被执行");

    }


    /**
     * if/else语句块中只包含一条语句，可以省略大括号,从编程规范角度不要省略大括号，省略大括号会使程序的可读性变差。
     */
    @Test
    public void test2_4() {
        int b = 5;
        if (b > 4)
            System.out.println("b大于4");
            //System.out.println();  打开这条注释会报错
        else
            System.out.println("b小于4等于");
    }


    /**
     * 使用花括号使得代码可读性更强
     */
    @Test
    public void test2_5() {
        double n = Math.random();
        if (n > 0.5) {
            n = 1;
        } else {
            n = 0;
        }
        System.out.println(n);

    }


    /**
     * if(条件表达式){
     * 条件执行体1；
     * }
     * else if (条件表达式){
     * 条件执行体2；
     * }
     * ……
     * else{
     * 条件执行体3；
     * }
     * <p>
     * 使用此结构时候的原则：一定要先处理包含范围小的情况；
     * 执行到某个分支路径进行判断时候已经把之前的所有范围exclude掉
     * 每次只能有一个分支被执行
     */
    @Test
    public void test3() {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入成绩:");
        int i = s.nextInt();
        if (i == 100) {
            System.out.println("满分");
            //这里等价于 i>=90 & i!=100(符合当前情形且exclude上面的情形)
        } else if (i >= 90) {
            System.out.println("优秀");
            //这里等价于 i>=60 & !(i>=90) & !(i==100)(符合当前情形且exclude上面的情形)
        } else if (i >= 60) {
            System.out.println("及格");
            //这里等价于 !(i>=60) & !(i>=90) & !(i==100)(符合当前情形且exclude上面的情形)
        } else {
            System.out.println("不及格");
        }
    }


    /**
     * Nested If Statement.
     * 条件细分或者大前提于小前提
     */
    @Test
    public void test4() {
        //Creating two variables for age and weight
        int age = 25;
        int weight = 48;
        //applying condition on age and weight
        if (age >= 18) {
            if (weight > 50) {
                System.out.println("You are eligible to donate blood");
            } else {
                System.out.println("You are not eligible to donate blood");
            }
        } else {
            System.out.println("Age must be greater than 18");
        }
    }


    /**
     * Nested If Statement.
     * 条件细分或者大前提于小前提
     */
    @Test
    public void test4_2() {
        String weather = "sunny";
        String today = "Sunday";
        if (today.equals("Sunday")) {
            if (weather.equals("sunny")) {
                System.out.println("Play outside rooom");
            } else {
                System.out.println("Play inside room");
            }
        } else {
            System.out.println("Go to work");
        }
    }

}
