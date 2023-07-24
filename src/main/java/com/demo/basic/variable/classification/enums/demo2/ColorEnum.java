package com.demo.basic.variable.classification.enums.demo2;

import org.testng.annotations.Test;

/**
 * 枚举功能之一：组织常量
 * 在 JDK5 之前，在 Java 中定义常量都是public static final TYPE a; 这样的形式。
 * 有了枚举，你可以将有关联关系的常量组织起来，使代码更加易读、安全，并且还可以使用枚举提供的方法。
 * <p>
 * 枚举功能之二：switch 状态机
 * 我们经常使用 switch 语句来写状态机。JDK7 以后，switch 已经支持 int、char、String、enum 类型的参数。
 * 这几种类型的参数比较起来，使用枚举的 switch 代码更具有可读性。
 * <p>
 * 在 enum 中，提供了一些基本方法：
 * values()：返回 enum 实例的数组，而且该数组中的元素严格保持在 enum 中声明时的顺序。
 * name()：返回枚举常量的字符串形式，也就是枚举常量的名称
 * ordinal()：返回实例声明时的次序，从 0 开始。
 * getDeclaringClass()：返回实例所属的 enum 类型。
 * equals() ：判断是否为同一个对象。
 */
public enum ColorEnum {
    //枚举类的类体部分是常量的定义，值一般是大写的字母，多个值之间以逗号分隔，最后以分号结束；
    //枚举表示的类型其取值是必须有限的，也就是说每个值都是可以枚举出来的
    //常量定义时候会自动调用枚举类的构造方法(如果没有定义，默认提供一个空参构造方法，这里就是空参构造方法)，所以每个常量相当于枚举类的一个实例对象
    RED,
    GREEN,
    BLUE;



    /**
     * 枚举类名.静态常量名 获取枚举常量
     * name()和toString()都是返回枚举常量的字符串形式
     */
    public static void testEnumToStringAndName() {
        //枚举类名.静态常量名， 常量对象的引用又赋值给了另一个引用，所以Color.RED和red都相当于常量对象的引用
        ColorEnum red = ColorEnum.RED;
        ColorEnum green = ColorEnum.GREEN;
        ColorEnum blue = ColorEnum.BLUE;

        //枚举类中重写了toString()方法,返回的是枚举常量的名称,也就是枚举常量名称的字符串形式;
        System.out.println(red);
        System.out.println(green);
        System.out.println(blue);

        //name()返回枚举常量的字符串形式
        System.out.println(red.name());
        System.out.println(green.name());
        System.out.println(blue.name());
    }


    /**
     * 根据字符串
     * green  green2  green3是一个对象的三个引用
     */
    @Test
    public static void testEnumValueOf() {
        ColorEnum green = ColorEnum.GREEN;
        //据String名称获取枚举常量，要求字符串跟枚举的常量名必须一致;
        ColorEnum green2 = ColorEnum.valueOf("GREEN");//Color.valueOf("GREEN")相当于ColorEnum.GREEN，是一个常量对象的引用
        ColorEnum green3 = Enum.valueOf(ColorEnum.class, "GREEN");//返回常量对象的引用
        System.out.println(green);
        System.out.println(green2);
        System.out.println(green3);
        ColorEnum yellow = ColorEnum.valueOf("YELLOW");//异常：No enum constant com.demo.basic.variable.variable_datatype.enums.demo2.Color.YELLOW
    }

    /**
     * values()方法的作用就是获取枚举类中的所有常量值(所有Color对象的引用)，并作为数组返回
     * 也就是返回枚举常量数组，而且该数组中的元素严格保持在 enum 中声明时的顺序。
     */
    public static void testValues() {
        ColorEnum[] colors = ColorEnum.values();
        for (ColorEnum color : colors) {
            System.out.println(color.name());
        }
    }


    /**
     * ordinal()：返回实例声明时的次序，从 0 开始。
     */
    public static void testOrdinalAndName() {
        ColorEnum[] colors = ColorEnum.values();
        for (ColorEnum color : colors) {
            System.out.println(color.ordinal() + " " +color.name());
        }
    }

    /**
     * Color必须是枚举类
     * @param color
     */
    public static void printColor(ColorEnum color) {
        switch (color) {//switch支持枚举类
            case RED:
                System.out.println("red");
                break;
            case BLUE:
                System.out.println("blue");
                break;
            case GREEN:
                System.out.println("green");
                break;
            default:
                break;
        }
    }

    public static void testPrintColor() {
        printColor(ColorEnum.GREEN);
    }

    public static void main(String[] args) {
        testEnumToStringAndName();
        System.out.println();
        testOrdinalAndName();
        System.out.println();
        testPrintColor();
    }

}



