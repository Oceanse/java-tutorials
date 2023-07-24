package com.demo.basic.method.equals_tostring_cloneable.cloneable.deep_copy;

/**
 * 深拷贝：原对象和拷贝对象完全独立，修改一个对象的值，并不会影响另一个对象的值
 * 此案例中，就是两个Person2对象的computer引用分别指向两个独立的Computer2对象
 * 对象里的引用类型变量经常出现层层调用关系，彻底深拷贝，有时候很难实现的
 * https://blog.csdn.net/m0_49425260/article/details/127910794
 */
public class Person2 implements Cloneable{
    String name;
    int age;
    Computer2 computer;

    public Person2(String name, int age, Computer2 computer) {
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

    public Computer2 getComputer() {
        return computer;
    }

    public void setComputer(Computer2 computer) {
        this.computer = computer;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", computer=" + computer +
                '}';
    }


    /**
     * 深拷贝：两个Person2对象的computer引用分别指向两个独立的Computer2对象
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person2 personCopy = (Person2)super.clone();//此时的personCopy的computer引用和当前对象的的computer引用指向同一个Computer2对象
        Computer2 computer2Copy=(Computer2)this.getComputer().clone();//克隆新的Computer2对象
        personCopy.setComputer(computer2Copy);//把personCopy的computer引用指向新的Computer2对象
        return personCopy;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Computer2 computer=new Computer2("M1 Mac");
        Person2 person=new Person2("ocean",33,computer);
        Person2 personCopy = (Person2)person.clone();
        System.out.println("person = " + person);
        System.out.println("personCopy = " + personCopy);
        String computerAddress = Integer.toHexString(person.getComputer().hashCode());
        String computerAddress2 = Integer.toHexString(personCopy.getComputer().hashCode());
        System.out.println("computerAddress = " + computerAddress);
        System.out.println("computerAddress2 = " + computerAddress2);
        System.out.println(person.getComputer()==personCopy.getComputer());//浅拷贝，原型对象和拷贝对象的computer引用共享同一个对象，也就是clone方法默认只是拷贝了对象的引用地址
    }
}
class Computer2 implements Cloneable{
    String brand;

    public Computer2(String brand) {
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}