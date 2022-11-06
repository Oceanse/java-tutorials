package com.demo.basic.method;

import org.testng.annotations.Test;

import java.util.Arrays;

/**
 *  方法是对一段功能代码的封装，可以被重复调用；
 *       方法不能独立存在，必须定义在类中，它属于某个对象(同一个类的不同对象调用相同方法可能会得到不同的结果)或者类本身
 *       方法不能独立执行，必须通过类或者对象调用，平时所见的"独立调用"其实jvm会自动添加this或者类名
 *
 *  语法： 【修饰符】 返回值类型 方法名(形参列表)
 *
 *      修饰符：权限修饰符/final/static等
 *
 *      返回值类型：有的时候需要方法返回最终的结果给主调函数，可以用return返回；返回值类型就是返回结果的类型
 *
 *      方法名：方法名和形参可以一起定位某个具体的方法
 *
 *      形参：形参中起决定作用的是形参的数据类型
 *      实参: 当方法被调用的时候传递的真实数据被称为实参(实际参数)
 *      实参传给形参时候可能会发生自动类型转换或者自动装箱拆箱
 *
 *
 *  方法只能定义在类中；不能嵌套定义(方法中定义方法)
 *  方法可以嵌套调用(方法中调用方法)，可以在方法内调用，也可以在方法外调用
 *
 *  方法体中的代码会自上而下顺序执行，上面的代码执行完才能执行下一行的代码
 *
 *
 *  main(String[] args)方法是程序的入口
 *
 * @author epanhai
 */
public class MethodDemo {


    /**
     * 方法外调用方法
     */
    int[] sortArray= new MethodDemo().sort(new int[]{1,3,2});


    /**
     * 方法中不能嵌套定义方法，但是可以嵌套调用方法
     */
    @Test
    public void test() {
       //方法中不能嵌套定义方法
       /*
        public int[] sort(int[] array){
            Arrays.sort(array);
            return array;
        }
        */


        //方法内部调用方法
        sort(new int[]{1,3,2});
    }


    public int[] sort(int[] array){
        Arrays.sort(array);
        return array;
    }


    /**
     * 实参传递给形参时候可以自动类型转换以匹配形参
     */
    @Test
    public void test2() {
        add(1,1);//这里的两个1会自动转换成double类型
    }

    public static double add(double d1,double d2){
        return d1+d2;
    }

}
