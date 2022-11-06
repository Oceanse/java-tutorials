package com.demo.lambda.lambda_intro.demo2;

/**
 * 无参有返回
 * lambda可以用来创建函数型接口对象，这个对象可以赋值给某个引用变量 或者 作为实参传给方法 或者 作为方法的返回值；
 * lambda创建对象的过程可以想象成包含了创建了实现类及其对象
 */
public interface ICar3 {
    double getPrice();//被重写的接口方法需要返回值
}


class TestCar3 {

    //匿名内部类
    static ICar3 car=new ICar3() {
        @Override
        public double getPrice() {
            return 2000;
        }
    };

    //若lambda体只有一条return语句，那么花括号、return、分号可以同时省略
    static ICar3 car2=()-> 100;

    //或者完整写法
    static ICar3 car3=()-> {return 1000;};


    public static double showPrice(ICar3 car){
        double price = car.getPrice();
        System.out.println("price = " + price);
        return price;
    }

    public static void main(String[] args) {
        showPrice(car);
        showPrice(car2);
        showPrice(car3);
    }
}


