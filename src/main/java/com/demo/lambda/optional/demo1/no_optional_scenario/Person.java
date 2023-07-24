package com.demo.lambda.optional.demo1.no_optional_scenario;


public class Person {
    private Car car;

    public Car getCar(){
        return car;
    }

}
class Car{

    private Insurance insurance;

    public Insurance getInsurance() {
        return insurance;
    }
}

class Insurance{

    private String name;

    public String getName() {
        return name;
    }
}

class MyTest{

    /**
     * 上面的代码就会造成空指针的问题，因为有的人没有车，并且有的车没有保险。
     * @param person
     * @return
     */
    public String getCarInsuranceName(Person person){
        return person.getCar().getInsurance().getName();
    }


    /**
     * 每次引用一个变量都会做一次null的检查，这样看似可以避免了空指针，但它会使代码膨胀，它会使我们的代码充满了深度嵌套的null检查，
     * 代码的可读性下降，还容易漏掉，并且维护起来也比较困难。
     * @param person
     * @return
     */
    public String getCarInsuranceName2(Person person){
        if (person!=null){
            Car car = person.getCar();
            if (car!=null){
                Insurance insurance = car.getInsurance();
                if (insurance!=null){
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }


}