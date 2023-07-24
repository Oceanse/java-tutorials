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
public class MethodIntroduce {


    /**
     * 方法外调用方法
     */
    int[] randomArray= this.generateArray(10);

    public int[] generateArray(int length){
        int[] randomArray=new int[length];
        for (int i = 0; i < length; i++) {
            randomArray[i]=(int)(Math.random()*100+1);//Math.random():[0,1)
        }
        return randomArray;
    }


    /**
     * 方法内部调用方法
     * 方法中不能嵌套定义方法，但是可以嵌套调用方法
     */
    @Test
    public void testSort() {
        System.out.println("ArrayBeforeSorted = " + Arrays.toString(randomArray));
        System.out.println("ArrayAfterSorted = " + Arrays.toString(sort(randomArray)));
    }


    public int[] sort(int[] array){
        Arrays.sort(array);
        return array;
    }


    //压栈顺序：main-->average-->sum
    //弹栈顺序：sum-->average-->main

    /**
     * 程序入口：main(String[] args)
     *
     * 每条线程都有一个独立的栈,在线程创建时创建
     * 栈的存取顺序是先进后出,后进先出,就像是手枪弹夹,后进去的先打出来;
     * 方法嵌套调就是不断压栈弹栈的过程
     * a-->b-->c(a调用b,b调用c),  那么a,b,c会依次压栈，a在最下面，b在中间，c在最上面面
     * c最先执行完，最先弹栈(释放栈内存)，b在弹栈，a最后弹栈
     * 如果方法嵌套调用的深度越大，那么占用的栈空间就会越大，可能会造成stackoverflow
     * */
    public static void main(String[] args) {
        int chinese=130;
        int math=150;
        int averageScore=average(130,150);
        System.out.println(averageScore);

    }

    public static int average(int i, int j){
        int m= sum(i,j);
        return  m/2;
    }


    private static int sum(int i, int j){
        return i+j;
    }

}
