package com.demo.lambda.lambda_intro.demo2;


/**
 * 有参有返回
 * lambda可以用来创建函数型接口对象，这个对象可以赋值给某个引用变量 或者 作为实参传给方法 或者 作为方法的返回值；
 * lambda创建对象的过程可以想象成包含了创建了实现类及其对象
 */
interface ICar4{
    int add(int a,int b);
}

class TestCar4 {
    // 当代码块中只有一条语句时候，即使该表达式需要返回值，也可以省略return关键字和花括号以及分号，这行语句的返回值将作为代码块的返回值
    ICar4 car=(a,b)->a+b;
    //等价于
    ICar4 car2=(a,b)->{return a+b;};

    public static void test(ICar4 car, int m, int n){
        int add = car.add(m, n);
        System.out.println(add);
    }

    public static void main(String[] args) {
        test((a,b)->a+b, 1,2);
    }

}
