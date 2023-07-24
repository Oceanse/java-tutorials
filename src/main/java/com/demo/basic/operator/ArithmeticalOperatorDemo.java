package com.demo.basic.operator;

import com.demo.oop.inherit.demo2_this_super.demo2.A;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ArithmeticalOperatorDemo {


    /**
     * 算数运算符：Arithmetic Operator
     * + - * / % ++ --
     * -除了作为减号外，还可以是求负运算符
     */
    @Test
    public void testBasic() {

        int i = 1;
        int i2 = 2;

        //结果和参与运算变量类型一致
        System.out.println("i + i2=" + (i + i2));
        System.out.println("i - i2=" + (i - i2));
        System.out.println("i * i2=" + (i * i2));
        System.out.println("i / i2=" + (i / i2));//0
        System.out.println();

        System.out.println("10*10/5+3-1*4/2=" + (10 * 10 / 5 + 3 - 1 * 4 / 2));//先乘除后加减

        double d = 1.0;
        System.out.println(d / 2);//参与运算的操作数类型需要保持一致，因此int类型2先自动转换为double类型，然后两个double类型相除
    }


    @Test
    public void testDivision() {
        //只有浮点数除以0才会得到正无穷大或者负无穷大
        //被除数或者除数有一个是小数，那么被除数除数都会自动转成浮点数
        System.out.println(1 / 0.0F);//Infinity
        System.out.println(-1 / 0.0);//-Infinity

        //正数除以0会产生异常：ArithmeticException: / by zero
        System.out.println(1 / 0);
    }


    /**
     * 取模运算
     */
    @Test
    public void testMod() {
        //取模运算符号看左边
        System.out.println(5 % 2);
        System.out.println(5 % -2);
        System.out.println(-5 % 2);
        System.out.println(-5 % -2);

        //1~100内偶数的和和数量
        int i = 0;
        int sum = 0;
        int count = 0;
        for (i = 1; i < 101; i++) {
            if (i % 2 == 0) {
                sum += i;
                count++;
            }
        }
        System.out.println(String.format("1~100内偶数的和:%d, 数量是%d", sum, count));
    }


    /**
     * 取模运算运用之轮训算法
     */
    @Test
    public void testMod2() {
        ArrayList<String> hosts=new ArrayList();
        hosts.add("host1");
        hosts.add("host2");
        hosts.add("host3");

        for (int i = 0; i < 10; i++) {
            String host = hosts.get(i % hosts.size());
            System.out.println(String.format("%s处理请求",host));
        }
    }


    /**
     * 取模运算
     * 如果y=2^n, x%y=x&(y-1)
     */
    @Test
    public void testMod3() {
        int number = 8;
        int number2 = 10;
        int result1 = number2 % number;
        int result2 = number2 & (number - 1);
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
    }

    /**
     * ++ --都是单目运算符(Unary Operator)，只能有一个操作数
     * ++a,--a： 先赋值，再运算
     * a++,a--:  先运算，再赋值
     * 从优先级角度考虑：后++<算数运算符/赋值运算符<前++
     * <p>
     */
    @Test
    public void testIncreaseAndDecrease() {

        int a = 10;
        int b = a++;
        int c = 10;
        int d = ++c;
        System.out.println("b = " + b);//10
        System.out.println("d = " + d);//11
    }

    /**
     * ++ --都是单目运算符(Unary Operator)，只能有一个操作数
     * ++a,--a： 先赋值，再运算
     * a++,a--:  先运算，再赋值
     * 从优先级角度考虑：后++<算数运算符/赋值运算符<前++
     * <p>
     */
    @Test
    public void testIncreaseAndDecrease2() {

        int a = 10;
        int b = 10;
        System.out.println(a++);//这里有一个参数值传递(赋值)的过程，也就是把实参传递给println(int x)的形参，后加加可以理解为先传递(赋值),后加加
        System.out.println(++b);//这里有一个参数值传递(赋值)的过程，也就是把实参传递给println(int x)的形参，前加加可以理解为先加价，后传递(赋值)
    }


    /**
     * ++ --都是单目运算符(Unary Operator)，只能有一个操作数
     * ++a,--a： 先进行自增或者自减，再运算；
     * a++,a--: 先运算，再进行自增或者自减
     * 从优先级角度考虑：后++<算数运算符/赋值运算符<前++
     * <p>
     */
    @Test
    public void testIncreaseAndDecrease3() {
        int i = 1;
        int i2 = 2 * i++;
        System.out.println("i=" + i);//2
        System.out.println("i2=" + i2);//2
        System.out.println();

        int i3 = 3;
        int i4 = 2 * ++i3;
        System.out.println("i3=" + i3);//4
        System.out.println("i4=" + i4);//8
        System.out.println();
    }


    /**
     * 自增自减可以修改变量存储的变量值(赋值修改，自增自减修改)
     * 只能怪对变量进行自增自减，不能对常量值或者结果为常量值的表达式进行自增自减
     */
    @Test
    public void testIncreaseAndDecrease4() {
        //int i5=1++;  不能对常量值或进行自增自减

        int a = 1;
        int b = 2;
        //(a+b)++;  a+b的结果是常量值，不能对结果为常量值的表达式进行自增自减(本质也是不能对常量值或进行自增自减)

        //不能对常量值或者结果为常量值的表达式进行自增自减
        int i;
        System.out.println(i = 100);
        //System.out.println((i=100)++);

        //不能对常量值或者结果为常量值的表达式进行自增自减
        int j;
        //System.out.println((j=i)++);
    }


    /**
     * 单目运算符-，用来修改数据的符号，和二元减号相同，但是编译器能正确识别
     */
    @Test
    public void test() {
        double number = 3000.0;
        double number2=-number;
        double number3=number-number2;
        System.out.println("number = " + number);
        System.out.println("number2 = " + number2);
        System.out.println("number3 = " + number3);
        number3-=6000;
        System.out.println("number3 = " + number3);
    }


    /**
     * 算数异或：以二进制数据为基础进行运算的，即当使用到异或运算时，都会先将两个运算数转换成二进制数据后，再进行异或运算，
     * 如果值相同为0，不同为1：0^0 = 0， 1^0 = 1， 0^1 = 1， 1^1 = 0
     * 布尔异或或者逻辑异或详见逻辑运算符
     * 两个操作数不同时返回true,相同时返回false
     *  算数异或：
     */
    @Test
    public void test算数异或(){
        System.out.println(0 ^ 0);
        System.out.println(1 ^ 1);
        System.out.println(0^1);
        System.out.println(1^0);

        //应用1：哈希值
        String key="myKey";
        int hash;
        System.out.println((hash=key.hashCode())^hash>>16);

        //应用2:加法器的和位输出
    }


    /**
     *  异或运算的性质:
     *  (1)0^N=N, N^N=0
     *  (2）满足交换律及结合律
     */
    @Test
    public void test算数异或2(){
        System.out.println(0^7);
        System.out.println(7^7);

        //交换律
        System.out.println((1^7)==(7^1));
        System.out.println((1^2^3)==(1^3^2));

        //结合律
        System.out.println((1^2^3)==(1^(2^3)));
    }


    /**
     * 如果一个数组中只有一个数出现了奇数次，剩下的数都出现了偶数次，求这个出现了奇数次的数。
     * 利用交换律N^N=0性质，则所有出现偶数次的数异或结果都为0，
     * 然后0^N=N,遍历数组将所有的数异或，得到的结果即为出现奇数次的数。
     */
    @Test
    public void test算数异或3(){
        int[] arr=new int[10];
        arr[0]=1;
        arr[1]=1;
        arr[2]=2;
        arr[3]=2;
        arr[4]=3;
        int target=arr[0];
        for (int i=1;i<arr.length;i++){
            target^=arr[i];
        }
        System.out.println(target);
    }


    /**
     * java.lang.Math 类提供的工具方法可以完成求幂、开方、绝对值等运算
     */
    @Test
    public void testMathTools() {
        System.out.println(Math.abs(-1.2));//|-1.2|
        System.out.println(Math.pow(2, 3));//2^3
        System.out.println(Math.sqrt(4));//2.0
        System.out.println(Math.random());//[0.0,1.0)随机数
        System.out.println(Math.random() * 100 + 1);//[1,101),也就是[1,100]
    }
}
