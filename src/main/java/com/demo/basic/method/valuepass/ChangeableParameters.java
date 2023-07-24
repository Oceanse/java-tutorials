package com.demo.basic.method.valuepass;

import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * jdk1.5开始支持形参个数可变的参数，简称为可变形参
 * 语法是在最后一个参数类型和参数名中间加三个点: printNames(String... names),它表明方法可以接受多个参数值
 * <p>
 * 可变参数列表 vs 数组参数
 * 可变参数的本质就是一个数组参数，printNames(String... names)类似于printNames(String[] names)，所以实参数组可以传给形参可变参数，实参可变参数可以传给数组形参
 * 在调用的时候可变参数比数组显得更加的方便简洁
 * 可变参数只能位于参数列表的最后
 *
 * @author epanhai
 */
public class ChangeableParameters {

    /**
     * 可变参数的本质就是一个数组参数
     * 可变参数只能位于参数列表的最后
     * @param classification
     * @param books
     */
    public void showBooks(String classification, String... books) {
        //可变参数的本质就是一个数组参数
        System.out.print(classification+": ");
        for (String book : books) {
            System.out.print(book + "\t");
        }
    }

    /**
     * 调用非常方便
     */
    @Test
    public void testShowBooks() {
        showBooks("technical", "thinking in java", "spring in action");
    }


    /**
     * 第二个参数只能传数组
     *
     * @param classification
     * @param books  数组作为参数
     */
    public void showBooks2(String classification, String[] books) {
        System.out.print(classification+": ");
        for (String book : books) {
            System.out.print(book + "\t");
        }
    }

    /**
     * 数组作为参数相对可变参数,调用相对不方便
     */
    @Test
    public void testShowBooks2() {
        showBooks2("technical", new String[]{"thinking in java", "spring in action"});
    }


    /**
     * 可变参数实参可以传递给数组形参或者说参数为数组类型的方法可接受可变参数作为入参
     */
    public static void printNames(String... names) {
        printArray(names);
    }

    public static void printArray(String[] names) {
        System.out.println(Arrays.toString(names));
    }

    @Test
    public static void test2() {
        printNames("cc", "dd");
    }


    /**
     * 数组实参可以传递给可变参数形参或者说可变参数的方法可接受数组作为入参
     */
    public static void printArray2(int[] arr) {
        printNames2(arr);
    }

    public static void printNames2(int... args) {
        for (int arg : args) {
            System.out.println(arg);
        }
    }

    @Test
    public static void test3() {
        printArray2(new int[]{1, 2, 3});
    }


    /**
     * 关于单参数和可变参数的匹配问题
     *
     * @param names
     */
    public void showNames(String... names) {
        System.out.println("(String... names): ");
        for (String name : names) {
            System.out.print(name);
        }
        System.out.println();
    }


    /**
     * 关于单参数和可变参数的匹配问题
     */
    public void showNames(String name) {
        System.out.println("showNames(String name): ");
        System.out.println(name);
    }

    @Test
    public void testShowNames() {
        showNames("ocean");//精确匹配
        showNames(new String[]{"ocean"});//单参数组匹配可变参数方法
    }

}
