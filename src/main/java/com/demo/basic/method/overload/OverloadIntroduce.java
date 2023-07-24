package com.demo.basic.method.overload;


import org.testng.annotations.Test;

/**
 * 重载(overload)就是方法名相同，但是参数的类型、数量、顺序不同; 在同一个类中，允许存在一个以上的同名方法，只要它们的参数个数 类型 顺序不同即可
 *
 * 使用场景：方法的功能具有一定的相似性
 *
 * 优点： 1 这种方法名相同，根据传入的参数不同调用不同的方法非常美观，对程序员来说非常友好，提高了程序的可读性。
 *       2 编译时多态： 方法名称都相同且参数不同。这里编译器在编译时能够识别调用的方法，因此这也称为编译时多态
 *
 *  注意：
 *  重载和方法的修饰符、返回值类型无关，只和方法名及参数相关
 * <p>
 * @author epanhai
 */
public class OverloadIntroduce {

    /**
     * 返回两个整数的和
     */
    int add(int x, int y) {
        return x + y;
    }

    /**
     * 返回两个小数的和
     */
    double add(double x, double y) {
        return x + y;
    }

    /**
     * 返回三个整数的和
     */
    int add(int x, int y, int z) {
        return x + y + z;
    }


    /**
     * 根据参数匹配相应的方法
     */
    @Test
    public void test() {
        //add(int x, int y)
        add(1, 1);

        //add(double x, double y)
        add(1.0, 1.0);

        // add(int x, int y, int z)
       add(1, 2, 3);

    }

}
