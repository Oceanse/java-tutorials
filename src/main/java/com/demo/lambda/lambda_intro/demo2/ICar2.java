package com.demo.lambda.lambda_intro.demo2;


/**
 * 函数型接口：有参无返回
 * lambda可以用来创建函数型接口对象，这个对象可以赋值给某个引用变量 或者 作为实参传给方法 或者 作为方法的返回值
 */
public interface ICar2 {
    void show(String brand);
}

class ICar2Iml implements ICar2 {
    @Override
    public void show(String brand) {
        System.out.println("The brand of car is： "+brand);
    }
}

class TestCar2 {

    /**
     *  通过实现类创建实现类对象
     *  通过实现类创建实现类对象后，方法的功能也就确定下来，然后就可以传递实参，调用这个对象的方法
     */
    public void test(){
        ICar2 car=new ICar2Iml();
        car.show("Benz");
    }

    /**
     * 创建匿名内部类对象，不需要显式定义实现类
     * 通过匿名内部类创建实现类对象后，方法的功能也就确定下来，然后就可以传递实参，调用这个对象的方法
     */
    public void test2(){
        ICar2 car=new ICar2(){
            @Override
            public void show(String brand) {
                System.out.println("The brand of car is： "+brand);
            }
        };
        car.show("Benz");
    }

    /**
     * 函数型接口：有参无返回
     * lambda表达式创建接口对象，但是只适合创建函数型接口对象；
     * lambda创建对象的过程可以想象成包含了创建了实现类及其对象过程可以想象成包含了创建了实现类及其对象
     * 通过lambda方式创建实现类对象后，方法的功能也就确定下来，然后就可以传递实参，调用这个对象的方法
     */
    public static void test3(){
        //若只有一个参数，小括号可以省略
        ICar2 car=brand->System.out.println("The brand of car is： "+brand);
        car.show("Benz");
    }


    /**
     * 面向ICar2接口编程，方法的具体功能取决于接口的具体实现，也就是接口的实现类
     * @param car
     * @param brand
     */
    public static void test4(ICar2 car, String brand){
        car.show(brand);
    }

    public static void main(String[] args) {
        //方法的调用主要是传递参数，只不过这里的参数是"函数参数"
        test4((brand ->System.out.println("The brand of car is： "+brand)),"BYD");
    }

}