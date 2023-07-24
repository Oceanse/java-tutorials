package com.demo.oop.anonymous.匿名内部类.demo1;

/**
 * 匿名内部类：没有类名的内部类
 * 首先是没有类名，因为没有采用implements创建实现类或者extends创建子类，所以子类没有类名，
 * 其次是定义在类中的内部类，这个内部类一定是某个接口的实现类或者抽象类/具体类的子类
 * 另外系统在创建匿名内部类时候会立即创建其对象
 * 匿名内部类是局部内部类的一种简化形式，本质上是一个对象,如果某个对象在整个程序中只需要使用一次，那么就可以使用匿名内部类
 * 或许叫匿名内部类对象更合适
 * <p>
 * 匿名内部类使用场景：
 * 匿名内部类一般是作为参数或者返回值来使用
 * 当接口的实现类或者父类的子类只是使用一次的话，采用implements创建实现类或者extends创建子类并实例化显得资源浪费， 所以可以采用匿名内部类创建接口实现类实例或者父类的子类实例，这样无需创建新的类，减少代码冗余。
 * 匿名内部类适合只需要使用一次的类
 * <p>
 * 优点： 匿名内部类可以使你的代码更加简洁，可以在定义一个类的同时对其进行实例化。
 * <p>
 * 匿名内部类使用原则：
 * 1 匿名内部类存在的前提是要有继承或者实现， 也就是匿名内部类必须继承一个父类或者实现一个且最多一个接口，
 * 2 匿名内部类不能是抽象类，因为系统在创建匿名内部类时候会立即创建其对象
 * 3 匿名内部类不能定义构造器，因为它没有类名，但是可以定义初始化块
 * 4 匿名内部类创建使用后立即消失，不能重复使用
 * 5 匿名内部类可以用在具体类、抽象类、接口上，且对方法个数没有要求。
 *   通过实现接口创建匿名内部类对象只能使用默认隐式的的空参构造器，因此new 接口名()不能传入其他参数：    new 接口名(){方法体}
 *   通过继承抽象类或者具体类，匿名内部类可以使用抽象父类所拥有的任何构造器： new 类名(){。。。} 或者 new 类名(实参列表){方法体}
 *   方法体可以对父类方法进行重写或者扩展
 *
 *   个人理解：匿名内部类兼具了匿名内部类(没有类名)和匿名对象(对象没有句柄)的特征
 */
interface Product {
    double getPrice();
    String getName();
}


/**
 * 创建一个Product接口实现类
 */
class Iphone implements Product{
    @Override
    public double getPrice() {
        return 8000;
    }

    @Override
    public String getName() {
        return "iphone14 pro";
    }
}



public class TestProduct {

    /**
     * 面向接口调用
     *
     * @param product
     */
    public static void getProductInfo(Product product) {
        System.out.println("name: " + product.getName() + " and price: " + product.getPrice());
    }


    public static void main(String[] args) {

        //创建实现类及其对象，并作为参数传递给方法，如果这个实现类只是使用一次就会显得浪费，可以使用匿名内部类的方式，可以无需创建新的类，减少代码冗余。
        Iphone iphone = new Iphone();
        getProductInfo(iphone);


        //以匿名内部类的形式创建接口的实现类：new 接口名(){...}
        getProductInfo(new Product() {
            @Override
            public double getPrice() {
                return 6000;
            }

            @Override
            public String getName() {
                return "watchS8";
            }
        });


        //以匿名内部类的形式创建接口的实现类：new 接口名(){...}
        getProductInfo(new Product() {
            @Override
            public double getPrice() {
                return 20000;
            }

            @Override
            public String getName() {
                return "M1 Mac pro";
            }
        });
    }
}
