package com.demo.generic.generic_wildcard.wildcard;

/**
 * 泛型类
 */
public class Plate<T>{//盘子类
    public T t;

    public Plate(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) {
        //苹果盘
        Plate<Apple> applePlate=new Plate<>(new Apple());
        //梨盘
        Plate<Pear> pearPlate=new Plate<>(new Pear());


       /* incompatible types: Plate<Apple> cannot be converted to Plate<Fruit>
        实际上，编译器认定的逻辑是这样的：
        苹果 IS-A 水果,   但是装苹果的盘子 NOT-IS-A 装水果的盘子
        所以，就算容器里装的东西之间有继承关系，但容器之间是没有继承关系。所以我们不可以把Plate<Apple>的引用传递给Plate<Fruit>。*/
        //Plate<Fruit> fruitPlate=applePlate;


        // Plate<?>和 Plate<? extends Fruit>可以看做Plate<Apple>和 Plate<Pear>的父类
        Plate<?> fruitPlate=applePlate;
        Plate<? extends Fruit> fruitPlate2=pearPlate;


    }

}




class Fruit {}
class Apple extends Fruit {}
class Pear extends Fruit{};