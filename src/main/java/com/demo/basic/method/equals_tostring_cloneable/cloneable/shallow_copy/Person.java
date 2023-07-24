package com.demo.basic.method.equals_tostring_cloneable.cloneable.shallow_copy;

/**
 * java.lang.Object类中有一个方法clone()，这个方法将返回Object对象的一个拷贝，简单理解为"只拷贝引用，没有拷贝对象"
 * 如果一个类重写了 Object 内定义的 clone()方法 ，需要同时实现 Cloneable 接口（虽然这个接口内并没有定义 clone() 方法），
 * 否则会抛出异常，也就是说， Cloneable 接口只是个合法调用 clone() 的标识（marker-interface）。
 */
public class Person implements Cloneable{
    String name;
    int age;
    Computer computer;

    public Person(String name, int age, Computer computer) {
        this.name = name;
        this.age = age;
        this.computer = computer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    //如果不对其进行重写，这里可以省略
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", computer=" + computer +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Computer computer=new Computer("M1 Mac");
        Person person=new Person("ocean",33,computer);
        Person personCopy = (Person)person.clone();
        System.out.println("person = " + person);
        System.out.println("personCopy = " + personCopy);
        System.out.println("person = personCopy: "+(person == personCopy));

        String computerAddress = Integer.toHexString(person.getComputer().hashCode());//无符号整数的16进制字符串
        String computerAddress2 = Integer.toHexString(personCopy.getComputer().hashCode());//无符号整数的16进制字符串
        System.out.println("computerAddress = " + computerAddress);//215be6bb
        System.out.println("computerAddress2 = " + computerAddress2);//215be6bb
        System.out.println(person.getComputer()==personCopy.getComputer());//浅拷贝，原型对象和拷贝对象的computer引用共享同一个对象，也就是clone方法默认只是拷贝了对象的引用地址
    }
}
class Computer{
    String brand;

    public Computer(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "brand='" + brand + '\'' +
                '}';
    }
}