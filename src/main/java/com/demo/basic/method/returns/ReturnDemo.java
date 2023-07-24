package com.demo.basic.method.returns;
import org.testng.annotations.Test;
import java.util.Scanner;

/**
 * return 用在方法中有两个作用：
 *   1 return 返回值;  表示返回方法结果；
 *   2 return; 表示方法执行结束，跳出方法；如果方法无需返回值但又因为方法内部业务逻辑需要结束运行， 则可以只用return来跳出方法, 不需要返回任何数据
 *     return; 语句只能出现在void方法中，因为它表示结束或者退出方法，不会返回数据； void方法的结尾处都会默认有一个隐式的 return；
 *
 *  注意：
 *  return; 或者 return 返回值; 我们可以称之为return语句，return语句只要被执行，所在方法就会执行结束，所以return语句后面不能跟其他语句
 * */
public class ReturnDemo {


    /**
     * 声明方法时包含返回值类型，那么方法体中要确保返回相应类型的结果
     * 这里需要if-else语句同时存在保证存在返回值
     * 假设去掉else分支,那么if语句可能不会被执行，也就是方法最终可能没有返回值，因此会与声明方法时的返回值类型有冲突，因此编译时候就会报错
     * @param d
     * @return
     */
   public static double abs(double d){
       // return d<0 ? -d : d
        if(d<0){
            return -d;
        }
        else {
            return d;
        }
    }


    /**
     * return语句后面不能跟其他语句，否则编译都会报错
     * @return
     */
    public static double returnEnd(){
        return 1.0;
        //System.out.println();
    }


    /**
     * return 返回值， 这里返回值和方法声明的返回值类型不一致时候可以进行自动类型转化
     * @return
     */
    public static double returnAutoConvert(){
        return 1;
    }


    /**
     * return 返回值. 自动装箱
     * @return
     */
    public static Double returnAutoBox(){
        return 1.0;
    }


    /**
     * return 返回值. 自动拆箱
     * @return
     */
    public static double returnAutoUnbox(){
        return Double.valueOf("31");
    }





    /**
     *  return;关键字跳出方法
     *  return;语句只能出现在void方法中，因为它表示结束或者退出方法，不会返回数据
     */
    public static void printNumber() {
        Scanner scanner =new Scanner(System.in);
        while(true) {
            System.out.println("Please input a number:");
            int num = scanner.nextInt();
            if(num==0){
                return;//方法执行结束，用return;
            }
            System.out.println(num);
        }
    }


    /**
     * return; 语句只能出现在void方法中，因为它表示结束或者退出方法，不会返回数据
     * return; 表示已经退出方法， 所以后面不能跟语句
     */
    public  void testReturn(){
        return;
        //后面不能跟语句
    }



    /**
     * return; vs break;
     */
    @Test
    public void return_break() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if (i == 5) {
                break;  //break是跳出当前for循环,但不会终止方法，也就是会继续执行for循环之后的代码
                //return; //终止方法，直接跳出当前方法
            }
        }
        //前面如果是return，这里永远不会被执行
        System.out.println("hahaha");
    }



    public static void main(String[] args) {
        printNumber();
    }


}
