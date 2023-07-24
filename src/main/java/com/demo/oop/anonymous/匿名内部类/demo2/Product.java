package com.demo.oop.anonymous.匿名内部类.demo2;


/**
 * 匿名内部类可以用在具体类、抽象类、接口上，且对方法个数没有要求。
 * 通过继承抽象类，匿名内部类可以使用抽象父类所拥有的任何构造器：  new 抽象类名(实参列表){。。。}
 */
public abstract class Product {
    String name;
    double price;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    abstract double getPrice();
    abstract String getName();
}

class TestProduct{

    public static void getProductInfo(Product product){
        System.out.println("name: "+product.getName()+" and price: "+product.getPrice());
    }



    public static void main(String[] args) {

        //new 抽象类名(实参列表){。。。}
        getProductInfo(new Product("cellphone",3000){
            @Override
            double getPrice() {
                return price;
            }
            @Override
            String getName() {
                return name;
            }
        });


        //new 抽象类名(实参列表){。。。}
        getProductInfo(new Product("book"){
            @Override
            double getPrice() {
                return price;
            }
            @Override
            String getName() {
                return name;
            }
        });


        //new 抽象类名(实参列表){。。。}
        getProductInfo(new Product(){
            @Override
            double getPrice() {
                return price;
            }
            @Override
            String getName() {
                return name;
            }
        });

    }
}
