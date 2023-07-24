package com.demo.lambda.optional.demo1.optional_scenario;

import java.util.Optional;


/**
 * 如果我们知道有的人没有车，那就不应该在Personal类内部声明Car的变量，所以这里用Optional来修饰,
 * 当存在Car类型变量的时候就返回，当不存在的时候就返回一个空的Optional对象，它就像一个盒子，修饰的对象被放了进去。
 * 除此之外，最重要的是，这就变的非常的明确，用Optional修饰，说明这里是允许变量缺失的。
 */
public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}


class Car {

    //有的车上了保险，也有可能没有上，所以这里使用Optional修饰
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}

class Insurance {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class OptionalTest {

    public static void main(String[] args) {
        Person person = new Person();
        Car car = new Car();
        Insurance insurance = new Insurance();
        //Optional.of()表示对象不能为null
        insurance.setName("insurance");
        car.setInsurance(Optional.of(insurance));//发生npe时候，会直接在赋值为null的地方进行报错，而不会在调用方法的时候出现空指针。
        person.setCar(Optional.of(car));//发生npe时候，会直接在赋值为null的地方进行报错，而不会在调用方法的时候出现空指针

        String carInsuranceName = getCarInsuranceName(person);
        System.out.println("carInsuranceName = " + carInsuranceName);
    }


    /**
     * 这里，我们就不需要进行null的判断、检查，因为发生异常的时候，会直接在赋值为null的地方进行报错，而不会在调用方法的时候出现空指针。
     * Optional只是在调用方法的时候出消除了进行null检查的逻辑，并快速定位问题，
     * @param person
     * @return
     */
    public static String getCarInsuranceName(Person person) {
        //可以通过get方法从Optional中取出值
        return person.getCar().get().getInsurance().get().getName();
    }

}
