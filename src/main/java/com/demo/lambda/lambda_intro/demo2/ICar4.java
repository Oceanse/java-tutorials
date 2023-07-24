package com.demo.lambda.lambda_intro.demo2;


import org.junit.Test;

/**
 * 有参有返回
 * lambda可以用来创建函数型接口对象，这个对象可以赋值给某个引用变量 或者 作为实参传给方法 或者 作为方法的返回值；
 * lambda创建对象的过程可以想象成包含了创建了实现类及其对象
 */
interface ICar4{
    int process(int a,int b);
}

class TestCar4 {


    @Test
    public static void test(){
        //通过lambda方式创建实现类对象后，方法的功能也就确定下来，然后就可以传递实参，调用这个对象的方法
        ICar4 car=(a,b)->{return a+b;};

        // 当代码块中只有一条语句时候，即使该表达式需要返回值，也可以省略return关键字和花括号以及分号，这行语句的返回值将作为代码块的返回值
        ICar4 car2=(a,b)->a+b;


        System.out.println(car.process(1, 2));
        System.out.println(car2.process(1, 2));
    }


    /**
     * 面向ICar4接口编程，方法的具体功能取决于接口的具体实现，也就是接口的实现类
     */
    public static void test2(ICar4 car, int m, int n){
        int add = car.process(m, n);
        System.out.println(add);
    }

    public static void main(String[] args) {
        test2((a,b)->a+b, 1,2);
    }

}
