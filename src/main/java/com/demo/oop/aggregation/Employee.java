package com.demo.oop.aggregation;
/**
 * 面向对象的复用技术: 组合和继承
 *
 * 使用继承的场景：
 * 父类和子类是is-a关系，即子类is父类
 *
 * 继承缺点：
 * 1 子类和父类的严重耦合
 * 2 继承在编码过程中就要指定具体的父类，其关系在编译期就确定，无法在运行期动态改变，因此降低了应用的灵活性
 *
 *
 * 复用：
 * 一个类拥有另一个类的对象引用
 *
 * 使用场景：
 * 两个类之间是HAS-A(组合或者聚合关系)，两个类之间有明确的整体、部分的关系， a事物(产品)是b事物(订单)的一部分，此时就应该采用组合关系来实现复用，
 *
 * 相对继承的优点：
 * 1 整体类与局部类之间松耦合，彼此相对独立
 * 2 组合，在写代码的时候可以采用面向接口编程。所以，类的组合关系一般在运行期确定。
 *
 * 缺点：
 * 创建整体类的对象时，需要创建所有局部类的对象，导致系统对象很多。
 */
public class Employee {
    int id;
    String name;

    /**
     *  Employee有一个实体引用地址(Address)，因此关系是：Employee HAS-A Address
     */
    private Address address;

    public Employee(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    /**
     * 在Employee使用Address类的功能
     */
    void display() {
        System.out.println(id + " " + name+" "+address.getCity() + " " + address.getProvince());
    }

}
