package com.demo.oop.anonymous.匿名内部类.demo2;

/**
 * 通过继承抽象类，匿名内部类可以使用抽象父类所拥有的任何构造器：  new 抽象类名(实参列表){。。。}
 */
public abstract class Shape {

    String name;

    /**
     * 抽象类空参构造器
     */
    public Shape() {
    }

    /**
     * 抽象类参数化构造器
     * @param name
     */
    public Shape(String name) {
        this.name = name;
    }

    abstract double getArea();
}

class TestShape{

    public static double getArea(Shape shape){
        return shape.getArea();
    }

    public static void main(String[] args) {

        /**
         * 以匿名内部类的形式创建抽象类的子类：new 抽象类名(){...}
         */
        double area = getArea(new Shape() {
            double r = 1.0;

            @Override
            double getArea() {
                return Math.PI * r * r;
            }
        });

        /**
         * 以匿名内部类的形式创建抽象类的子类：new 抽象类名(实参列表){...}
         */
        double area2 = getArea(new Shape("Rectangle") {
            double length = 2.0;
            double width=1.0;

            @Override
            double getArea() {
                return length*width;
            }
        });

        System.out.println("area = " + area);
        System.out.println("area2 = " + area2);

    }
}
