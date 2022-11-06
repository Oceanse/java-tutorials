package com.demo.basic.method.recursion;



/**
 * 一个方法体内调用它自身， 被称为方法递归。方法递归包含了一种隐式的循环， 它会重复执行某段代码， 但这种噩复执行无须循环控制。
 递归很消耗内存，方法调用一次，方法压栈一次
 * 一直递归导致不断的压栈，最后造成StackOverflowError
 * 所以递归必须有结束条件，但是递归太深还是可能造成栈溢出错误
 * */
public class Recursion4 {


    /**
     * 已知有一个数列： f(O) = 1, f(1)=4, f(n+2) = 2f(n+1)+f(n), 其中n是大于0的整数：求f(10)
     *
     * 仔细看下面递归的过程，当一个方法不断地调用它本身时，必须在某个时刻方法的返回值是确定的，
     * 即不再调用它本身， 否则这种递归就变成了无穷递归，类似于死循环。因此定义递归方法时有一条最重
     * 要的规定： 递归一定要向已知方向递归。
     *
     * 这里告诉了f(0)和f(1),所以要从大向小递归， f(n) = 2f(n-1)+f(n-1)
     */
    public static int fn(int n){
        if(n==0){
            return 1;
        }else if(n==1){
            return 4;
        }else{
            return 2*fn(n-1)+fn(n-2);
        }
    }



    /**
     * 已知有一个数列： f(2O) = 1, f(21)=4, f(n+2) = 2f(n+1)+f(n), 其中n是大于0的整数：
     *
     * 仔细看下面递归的过程，当一个方法不断地调用它本身时，必须在某个时刻方法的返回值是确定的，
     * 即不再调用它本身， 否则这种递归就变成了无穷递归，类似于死循环。因此定义递归方法时有一条最重
     * 要的规定： 递归一定要向已知方向递归。
     *
     *  这里告诉了f(20)和f(21),所以要从大向小递归， f(n) = f(n+2)-2f(n+1)
     */
    public static int fn2(int n){
        if(n==20){
            return 1;
        }else if(n==21){
            return 4;
        }else{
            return fn2(n+2)-2*fn2(n+1);
        }
    }



    public static void main(String[] args) {
        System.out.println(fn(10));
        System.out.println(fn2(10));
    }
}
