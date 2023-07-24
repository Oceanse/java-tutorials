package com.demo.lambda.optional.demo2;

import org.testng.annotations.Test;

import java.util.Optional;

public class OptionalDemo {

    @Test
    public void createOptional() {
        //使用Optional.empty()方法创建一个空的Car类型的Optional对象。
        Optional emptyOptional = Optional.empty();

        //创建一个非空值的Optional，如果car为null的话，直接抛出空指针异常
        Car car = new Car();
        Optional<Car> optionalCar = Optional.of(car);

        //创建一个可以为null的Optional，该方法支持car为null，但是会在用到car的地方抛出异常，但不是空指针异常。
        Car car2 = null;
        Optional<Car> optionalCar2 = Optional.ofNullable(car2);
        System.out.println("emptyOptional = " + emptyOptional.isEmpty());
        System.out.println("optionalCar = " + optionalCar.isEmpty());
        System.out.println("optionalCar2 = " + optionalCar2.isEmpty());
    }


    /**
     * 使用get来从Optional中取值。
     * get（）方法是最简单也是最不安全的方法，如果变量存在就返回，不存在的话则会抛出NoSuchElementException的异常。
     * 所以，get（）的使用场景一定是十分确定Optional修饰的值一定是有内容的，否则不建议使用。
     */
    @Test
    public void getTest() {
        Car car = new Car();
        Insurance insurance = new Insurance();
        car.setInsurance(Optional.of(insurance));
        Insurance insurance1 = car.getInsurance().get();

    }


    /**
     * 使用get来从Optional中取值。
     * get（）方法是最简单也是最不安全的方法，如果变量存在就返回，不存在的话则会抛出NoSuchElementException的异常。
     * 所以，get（）的使用场景一定是十分确定Optional修饰的值一定是有内容的，否则不建议使用。
     */
    @Test
    public void getTest2() {
        Car car = new Car();
        Insurance insurance = null;
        car.setInsurance(Optional.ofNullable(insurance));//这里返回空option
        Insurance insurance1 = car.getInsurance().get();//NoSuchElementException

    }


    /**
     * 安全获取：orElse
     * 该方法相对于get（）的好处在于当Optional对象中不存在则可以返回一个默认的值
     */
    @Test
    public void orElseTest() {
        Car car = new Car();
        Insurance insurance = new Insurance();
        car.setInsurance(Optional.of(insurance));
        insurance.setName("insurance1");
        //使用orElse来赋予默认的值
        Insurance insurance1 = car.getInsurance().orElse(new Insurance());
        System.out.println(insurance1);
    }


    /**
     * 安全获取：orElse
     * 该方法相对于get（）的好处在于当Optional对象中不存在则可以返回一个默认的值
     */
    @Test
    public void orElseTest2() {
        Car car = new Car();
        car.setInsurance(Optional.ofNullable(null));
        //使用orElse来赋予默认的值
        Insurance insurance = new Insurance();
        insurance.setName("insurance2");
        Insurance insurance1 = car.getInsurance().orElse(insurance);//car.getInsurance()返回空optional,所以这里会返回orElse中的默认值
        System.out.println(insurance1);
    }


    /**
     * 安全获取：orElseGet(懒加载)
     * 该方法是orElse（）方法的延迟调用版，当对象为空的时候才会产生默认值，它的性能相对于orElse（）来说更好一些，建议使用该方法， 下面是orElseGet（）和orElse（）的源码对比：
     * public T orElse(T other) {
     * return this.value != null ? this.value : other;
     * }
     * <p>
     * <p>
     * public T orElseGet(Supplier<? extends T> supplier) {
     * return this.value != null ? this.value : supplier.get();
     * }
     */
    @Test
    public void orElseGetTest() {
        Car car = new Car();
        Insurance insurance = new Insurance();
        car.setInsurance(Optional.of(insurance));
        insurance.setName("insurance1");
        //使用orElseGet来赋予默认的值
        Insurance insurance1 = car.getInsurance().orElseGet(() -> new Insurance());
        System.out.println(insurance1);
    }


    /**
     * 安全获取：orElseGet
     * 该方法是orElse（）方法的延迟调用版（有点像懒加载），当对象为空的时候才会产生默认值；
     * 在执行较密集的调用时，比如调用Web 服务或数据查询，这个差异会对性能产生重大影响。
     */
    @Test
    public void orElseGetTest2() {
        Car car = new Car();
        car.setInsurance(Optional.ofNullable(null));
        //使用orElse来赋予默认的值
        Insurance insurance = new Insurance();
        insurance.setName("insurance2");
        Insurance insurance1 = car.getInsurance().orElseGet(()->insurance);//car.getInsurance()返回空optional,所以这里会返回orElseGet中的默认值
        System.out.println(insurance1);
    }


    /**
     * 两种方法都调用了createNewCar()方法，这个方法会记录一个消息并返回Car对象。
     * 由此可见，当对象为空而返回默认对象时，行为并无差异。
     */
    @Test
    public void orElse_orElseGet_Test() {
        Car car = null;
        Car Car1 = Optional.ofNullable(car).orElse(createNewCar());
        Car Car2 = Optional.ofNullable(car).orElseGet(() -> createNewCar());
    }


    /**
     * 两个Optional 对象都包含非空值，两个方法都会返回对应的非空值。
     * 不过，orElse()方法仍然创建了Car对象。与之相反，orElseGet()方法不创建Car对象。
     * 在执行较密集的调用时，比如调用Web 服务或数据查询，这个差异会对性能产生重大影响。
     */
    @Test
    public void orElse_orElseGet_Test2() {
        Car car = new Car();
        Car Car1 = Optional.ofNullable(car).orElse(createNewCar());
        Car Car2 = Optional.ofNullable(car).orElseGet(() -> createNewCar());
    }

    private Car createNewCar() {
        System.out.println("Creating New Car");
        return new Car();
    }


    /**
     * orElseThrow
     * 当Optional修饰的对象为空的时候来抛出一个自己指定的异常类型。
     */
    @Test
    public void orElseThrowTest(){
        Car car = new Car();
        Car Car1 = Optional.ofNullable(car).orElseThrow(()->new RuntimeException());
    }


    /**
     * orElseThrow
     * 当Optional修饰的对象为空的时候来抛出一个自己指定的异常类型。
     */
    @Test
    public void orElseThrowTest2(){
        Car car = null;
        Car Car1 = Optional.ofNullable(car).orElseThrow(()->new RuntimeException());
    }



    /**
     * 安全消费：ifPresent
     * 当Optional修饰的对象为空的时候来抛出一个自己指定的异常类型。
     */
    @Test
    public void ifPresentTest(){
        Car car = new Car();
        Insurance insurance = new Insurance();
        insurance.setName("insurance");
        car.setInsurance(Optional.ofNullable(insurance));
        car.getInsurance().ifPresent(insurance1 -> System.out.println(insurance1));//Optional<Insurance>非空时候就消费
    }


    /**
     * 安全消费：ifPresent
     * 当Optional修饰的对象为空的时候来抛出一个自己指定的异常类型。
     */
    @Test
    public void ifPresentTest2(){
        Car car = new Car();
        car.setInsurance(Optional.ofNullable(null));
        car.getInsurance().ifPresent(insurance1 -> System.out.println(insurance1));//Optional<Insurance>非空时候就消费
    }

    /**
     * 数据转换map
     */
    @Test
    public void optionalMap() {
        Insurance insurance = new Insurance();
        insurance.setName("insurance");
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        Optional<String> nameOptional = optionalInsurance.map(insurance1 -> insurance1.getName());
        String name = nameOptional.get();
        System.out.println("name = " + name);
    }


    /**
     * 数据转换map
     */
    @Test
    public void optionalMap2() {
        Insurance insurance = new Insurance();
        insurance.setName("insurance");
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        String name = optionalInsurance.map(Insurance::getName).orElse("unKnown");
        System.out.println("name = " + name);
    }


    @Test
    public void optionalMap3() {
        Optional<Insurance> optionalInsurance = Optional.ofNullable(null);
        String name = optionalInsurance.map(Insurance::getName).orElse("unKnown");
        System.out.println("name = " + name);
    }


    /**
     * 使用map方法取出来的是一个用Optional<Optional< Car>>类型的对象,这种Optional可以使用flatMap处理
     */
    @Test
    public void flatMapTest() {
        Person person=new Person();
        Optional<Person> optionalPerson = Optional.of(person);
        //Person::getCar返回的是Optional<Car>，所以最终的返回值是Optional<Optional<Car>>
        Optional<Optional<Car>> car = optionalPerson.map(Person::getCar);
    }


    /**
     * flatMap方法，就和之前的stream流一样，“将所有内容都放在最外面的容器里”。
     */
    @Test
    public void flatMapTest2() {
        Person person=new Person();
        Optional<Person> optionalPerson = Optional.of(person);
        //Person::getCar返回的是Optional<Car>，经过flatMap后，Optional<Optional<Car>>转化成Optional<Car>
        Optional<Car> carOptional = optionalPerson.flatMap(Person::getCar);
        Optional<Insurance> insuranceOptional = carOptional.flatMap(car -> car.getInsurance());
        Optional<String> nameOptional = insuranceOptional.map(insurance -> insurance.getName());
        String name = insuranceOptional.map(insurance -> insurance.getName()).orElse("unknown");
    }


    /**
     * filter过滤数据
     */
    @Test
    public void optionalFilter() {
        Insurance insurance = new Insurance();
        insurance.setName("insurance");

        Insurance insurance2 = new Insurance();
        insurance2.setName("insurance2");
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        Optional<Insurance> insuranceOptional = optionalInsurance.filter(insurance1 -> insurance1.getName().equals("insurance"));
        Insurance result = insuranceOptional.orElse(insurance2);
        System.out.println("result = " + result);
    }


    /**
     * filter过滤数据
     */
    @Test
    public void optionalFilter2() {
        Insurance insurance2 = new Insurance();
        insurance2.setName("insurance2");

        //optionalInsurance为空
        Optional<Insurance> optionalInsurance = Optional.ofNullable(null);

        //optionalInsurance为空,经过filter的结果还是空Optional
        Optional<Insurance> insuranceOptional = optionalInsurance.filter(insurance1 -> insurance1.getName().equals("insurance"));

        //返回默认结果
        Insurance result = insuranceOptional.orElse(insurance2);
        System.out.println("result = " + result);
    }



    /**
     * filter过滤数据
     */
    @Test
    public void optionalFilter3() {
        Insurance insurance = new Insurance();
        insurance.setName("insurance");
        Insurance insurance2 = new Insurance();
        insurance2.setName("insurance2");

        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);

        //没有符合条件的经过Insurance，filter的结果是空Optional
        Optional<Insurance> insuranceOptional = optionalInsurance.filter(insurance1 -> insurance1.getName().equals("ocean"));

        //返回默认结果
        Insurance result = insuranceOptional.orElse(insurance2);
        System.out.println("result = " + result);
    }



    @Test
    public void isPresentTest(){
        Insurance insurance = new Insurance();
        insurance.setName("insurance");
        Optional<Insurance> optionalInsurance = Optional.ofNullable(insurance);
        if(optionalInsurance.isPresent()){
            Insurance insurance1 = optionalInsurance.get();
            System.out.println("insurance1 = " + insurance1);
        }

        Optional<Insurance> optionalInsurance2 = Optional.ofNullable(null);
        if(optionalInsurance2.isPresent()){
            Insurance insurance2 = optionalInsurance2.get();
            System.out.println("insurance2 = " + insurance2);
        }

    }



}

class Person {

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

    @Override
    public String toString() {
        return "Insurance{" +
                "name='" + name + '\'' +
                '}';
    }
}