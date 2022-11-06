package com.demo.enums.demo1;


/**
 * https://www.toutiao.com/a6764240507808776715/
 *
 * 枚举类型是Java 5中新增特性的一部分，它是一种特殊的数据类型，因为它既是一种类(class)类型却又比类类型多了些特殊的约束，
 * 但是这些约束的存在也造就了枚举类型的简洁性、安全性以及便捷性
 *
 * 执行 javac Direction.java 命令，生成 ColorEn.class 文件。
 * 然后执行 javap Direction.class 命令，输出如下内容：
 * final class Direction extends Enum{
 *     public final static Direction EAST = new Direction();
 *     public final static Direction WEST = new Direction();
 *     public final static Direction NORTH = new Direction();
 *     public final static Direction SOUTH = new Direction();
 * }
 *  enum枚举是一个特殊的类，继承了java.lang.Enum
 *  enum 是一种受限制的类,因为被修饰为 final，所以不能被其他类继承,另外Direction继承java.lang.Enum类，而Java不支持多重继承，所以不能再继承其他类
 * 定义的枚举值，会被默认修饰为 public static final ，从修饰关键字，即可看出枚举值本质上是静态常量。
 * 枚举可以添加普通方法、静态方法、抽象方法、构造方法
 *
 * 在 enum 中，提供了一些基本方法：
 * values()：返回 enum 实例的数组，而且该数组中的元素严格保持在 enum 中声明时的顺序。
 * name()：返回实例名。
 * ordinal()：返回实例声明时的次序，从 0 开始。
 * getDeclaringClass()：返回实例所属的 enum 类型。
 * equals() ：判断是否为同一个对象。
 *
 *
 */
public enum Direction {
    EAST, WEST, NORTH, SOUTH;
}



