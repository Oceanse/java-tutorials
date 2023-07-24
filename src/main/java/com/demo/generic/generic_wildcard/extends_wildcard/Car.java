package com.demo.generic.generic_wildcard.extends_wildcard;

import java.lang.reflect.InvocationTargetException;

public abstract class Car {
    public abstract void start();
}

class BMW extends Car {
    @Override
    public void start() {
        System.out.println("BMW 启动");
    }
}

class BYD extends Car {
    @Override
    public void start() {
        System.out.println("BYD 启动");
    }
}

class Train {
    public void start() {
        System.out.println("和谐号 启动");
    }
}


class CarFactory {

    /**
     * Class<? extends Car> 看成是 Class<BMW> Class<BYD>😓的父类
     * 参数只能接收 BMW.class   BYD.class等
     * 通过上界通配符限制，只能生产Car的子类对象
     *
     * @param clazz
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Car produceCar(Class<? extends Car> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Car car = clazz.getDeclaredConstructor().newInstance();
        return car;
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Car bmw = produceCar(BMW.class);
        Car byd = produceCar(BYD.class);
        //produceCar(Train.class); //编译报错,因为Train没有继承Car,不在Class<? extends Car>范围之内
        bmw.start();
        byd.start();
    }
}