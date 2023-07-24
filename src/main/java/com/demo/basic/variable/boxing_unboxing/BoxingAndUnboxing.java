package com.demo.basic.variable.boxing_unboxing;

import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * 背景：
 * 8种基本数据类型带来了一定的方便性，例如可以进行简单、有效的常规数据处理。但在某些时
 * 候，基本数据类型会有一些制约，例如所有引用类型的变量都继承了Object类， 都可当成Object类型
 * 变量使用。但基本数据类型的变量就不可以， 如果有个方法需要Object类型的参数， 但实际需要的值
 * 却是2、3等数值，这可能就比较难以处理。
 * 为了解决8种基本数据类型的变僵不能当成Object 类型变罹使用的问题， Java 提供了包装类
 * (Wrapper Class)的概念， 为8种基本数据类型分别定义了相应的引用类型， 并称之为基本数据类型的
 * 包装类。
 * <p>
 * <p>
 * 原始类型	包装类型
 * boolean	Boolean
 * byte	Byte
 * char	Character
 * float	Float
 * int	Integer
 * long	Long
 * short	Short
 * double	Double
 * <p>
 * <p>
 * <p>
 * <p>
 * 编译器自动帮我们进行装箱或拆箱的场景
 * 拆箱场景：
 * 进行+，-，*，/混合运算
 * 进行>,<比较运算
 * <p>
 * 装箱场景：
 * 往ArrayList,HashMap等集合类 添加基础类型数据会自动装箱
 * 调用equals等方法
 * <p>
 * 装箱/拆箱场景：
 * 进行 = 赋值操作
 * 参数传递
 * 返回值
 * <p>
 * 我们看一段平常很常见的代码
 * public void testAutoBox() {
 * List<Float> list = new ArrayList<>();
 * list.add(1.0f);
 * float firstElement = list.get(0);
 * }
 * <p>
 * list集合存储的是Float包装类型，我传入的是float基础类型，所以需要进行装箱，
 * 而最后的get方法返回的是Float包装类型，我们赋值给float基础类型，所以需要进行拆箱，很简单，安排的明明白白
 */
public class BoxingAndUnboxing {

    /**
     * 拆箱和装箱就是基本类型和包装类之间的转换
     * 基本类型--->包装类
     * int --> Integer
     * byte --> Byte
     * short --> Short
     * long --> Long
     * char --> Character
     * float --> Float
     * double --> double
     * boolean --> Boolean
     */
    @Test
    public void testBoxingAndUnboxingManually() {

        //手动装箱1
        Integer integer1 = new Integer(100);
        Integer integer2 = new Integer("100");
        Boolean bool1=new Boolean(true);
        Boolean bool2=new Boolean("true");
        Character character = new Character('p');

        //手动装箱2
        Integer integer3 = Integer.valueOf("100");
        Integer integer4 = Integer.valueOf(100);
        Boolean bool3 = Boolean.valueOf(true);
        Boolean bool4 = Boolean.valueOf("true");
        Character character2 = Character.valueOf('d');

        //手动拆箱，很少用到
        int j = integer1.intValue();
        char ch = character.charValue();
    }


    /**
     * 赋值的时候自动装箱拆箱
     */
    @Test
    public void testBoxingAndUnboxingWhenAssign() {
        Double d = Double.valueOf(1.0);
        double d2 = d; //自动拆箱  包装类对象自动转换成基本数据类型 本质上是，编译器编译时自动添加double d2=d.doubleValue()
        Double d3 = d2; //自动装箱  基本类型就自动地封装到与它相似类型的包装中 本质上是，编译器编译时自动添加：Double d3 =Double.valueOf(d2);
        Object flag = true;//先自动装箱，然后向上转型  Object Obj =Boolean.valueOf(true)
    }

    /**
     * 参数传递时候自动装箱拆箱
     */
    @Test
    public void testBoxingAndUnboxingWhenPassParameter() {
        //自动装箱
        ArrayList<Double> doubles = new ArrayList<Double>();
        doubles.add(1.0);//Java编译器自动装箱:  doubles.add(new Double(1.0));

        //自动拆箱
        printChar(Character.valueOf('c'));

        //自动装箱
        printCharacter('c');
    }


    /**
     * 返回值自动装箱拆箱
     */
    public Integer testBoxingAndUnboxingWhenReturn() {
        //return 语句中自动装箱
        return 1;
    }


    /**
     * 返回值可以自动装箱拆箱
     */
    public int testBoxingAndUnboxingWhenReturn2() {
        //return 语句中自动拆箱
        return Integer.valueOf(1);
    }


    /**
     * 数值字符串----->数值基本数据类型/数值包装类
     * 注意涉及到数值型数据时候传入格式不对会抛出NumberFormatException
     * boolean不会抛出NumberFormatException
     */
    @Test
    public void testParseNumericalString() {
        //数值字符串转化成对应的数值，严格来说是转化成数值对应的基本类型
        //这里的priceStr必须是数值字符串，否则会产生ava.lang.NumberFormatException
        double d = Double.parseDouble("100.0");
        Double d2 = Double.valueOf("100.0");

        //这里的number必须是整数字符串
        String number = System.getProperty("port","10086");
        int i = Integer.parseInt(number);
        Integer i2 = Integer.valueOf("100");

        //只有传入true(忽略大小写)时候才返回true，其他字符串返回false,没有数据格式Exception
        boolean v = Boolean.parseBoolean("true");
    }


    /**
     * 基本类型--->String
     * 通过点击command+点击println方法可知其参数是String类型
     */
    @Test
    public void test7() {
        System.out.println(String.valueOf(100));
        System.out.println(String.valueOf('c'));
        System.out.println(String.valueOf(true));

        //扩展
        System.out.println(String.valueOf("aaa"));
        System.out.println(String.valueOf(null));
    }

    /**
     * 这个方法可以接受char类型或者其包装类Character
     *
     * @param c
     */
    public static void printChar(char c) {
        System.out.println(c);
    }


    /**
     * 这个方法可以接受char类型或者其包装类Character
     *
     * @param c
     */
    public static void printCharacter(Character c) {
        System.out.println(c);
    }



}
