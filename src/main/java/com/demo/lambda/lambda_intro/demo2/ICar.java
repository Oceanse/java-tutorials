package com.demo.lambda.lambda_intro.demo2;

/**
 * 函数型接口：无参无返回
 * lambda可以用来创建函数型接口对象，这个对象可以赋值给某个引用变量 或者 作为实参传给方法 或者 作为方法的返回值
 */
public interface ICar {
    void drive();
}

class ICarImpl implements ICar {
    @Override
    public void drive() {
        System.out.println("BMW");
    }
}


class TestCar {

    /**
     *  通过实现类创建实现类对象
     *  通过实现类创建实现类对象后，方法的功能也就确定下来，然后就可以传递实参，调用这个对象的方法
     */
    public void test(){
        ICar car=new ICarImpl();
        car.drive();
    }


    /**
     * 创建匿名内部类对象，不需要显式定义实现类,lambda创建对象的过程可以想象成包含了创建了实现类及其对象
     * 函数型接口：无参无返回
     * 通过匿名内部类创建实现类对象后，方法的功能也就确定下来，然后就可以传递实参，调用这个对象的方法
     */
    public void test2(){
        ICar car=new ICarImpl(){
            @Override
            public void drive() {
                System.out.println("BMW");
            }
        };

        car.drive();
    }


    /**
     * lambda表达式创建接口对象，但是只适合创建函数型接口对象
     * 通过lambda方式创建实现类对象后，方法的功能也就确定下来，然后就可以传递实参，调用这个对象的方法
     *
     */
    public void test3(){
        ICar car=()->{
            System.out.println("BMW");
        };
        car.drive();
    }


    /**
     * 面向ICar接口编程，方法的具体功能取决于接口的具体实现，也就是接口的实现类
     * @param car
     */
    public static void driveCar(ICar car){
        car.drive();
    }

    public static void main(String[] args) {
        //方法的调用主要是传递参数，只不过这里的参数是"函数参数"
        driveCar(()-> System.out.println("BMW"));

    }

}

