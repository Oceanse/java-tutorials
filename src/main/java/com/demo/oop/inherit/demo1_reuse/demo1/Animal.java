package com.demo.oop.inherit.demo1_reuse.demo1;
/**
 * is-a: 这种事物是那种事物中的一个种类；绵羊是羊的一个种类，羊是动物的一个子类
 * 继承需要符合的关系是：is-a，可以理解为:“子类 is a 父类”, 父类更通用，子类更具体
 *
 *
 * extends 的英文意思是扩展，而不是继承,表现为:
 * 1 直接拥有父类非private的属性和方法，
 *   间接拥有父类private的属性和方法，private属性方法也可以脑补到子类，但是只能对其间接读写，比如对于父类private属性，子类可以通过构造器(super)和其他方法进行读写
 *  子类继承父类后，子类就拥有了父类全部的属性和行为，可以把继承过来的属性和方法全部脑补到子类当中；
 *  只不过对于父类私有属性，子类只能间接拥有,也就是子类不能直接操作继承的私有属性，但是可以通过继承父类暴露的getter/setter/constructor等方法间接访问和修改，
 *  也就是绕了一个弯后去操控这些私有属性，所以可以理解为子类间接拥有了父类的私有属性(间接读写)
 *  对于父类的私有方法，也是一样，子类只能间接拥有，通过继承父类暴露的方法间接调用
 *  记住： 子类继承父类后，子类调用方法时候一定调用的是子类自己的方法(从父类继承过来就是子类自己的了)，只是这个方法可能会被重写，也可能没有重写
 *  脑海中要有子类拥有的的全部的成员变量和方法
 *
 * 2 重写父类对象方法：
 * 当父类方法不能满足子类需求(比如toString方法)，可以对父类继承的方法进行重写,重写只是针对方法，不针对属性，重写后子类只拥有重写后的方法
 * In object-oriented terms, overriding means to override the functionality of an existing method.
 * 子类方法和父类方法的 返回值 方法名(参数) 完全相同时候，编译器就会认为子类对父类方法进行了重写，无论带不带有@Override
 * 重写后方法的权限不能小于被重写方法的权限;
 * 重写后方法抛出的异常不能多于被重写方法；
 * 静态方法不能被覆盖
 * 私有方法不能被重写
 *
 * 3 新增属性和方法：
 * 另外可以在父类的基础上新增成员变量和方法
 *
 *
 * 优点：
 * 继承提高代码可重用性,减少创建类的工作量(子类可以拥有父类的方法和属性); 为后面的重写和多态做铺垫
 *
 *
 * 缺点：
 * 增强代码耦合性（开发项目的原则为高内聚低耦合）。当父类的常量、变量和方法被修改时就会影响到子类，所以需要考虑子类的修改，有可能会导致大段的代码需要重构。
 *
 *
 * 多态：
 * SupperClass obj=new SubClass();  obj.method();
 *  obj在编译时期是SupperClass类型，运行期是Subclass类型；引用变量在不同时期表现出的不同类型称之为多态
 *  obj指向的对象是在运行期决定的(运行时才会创建对象)，所以obj的实际类型(父类还是子类)在运行期决定，因此调用哪个类的方法也在运行时完成。
 *
 *  public void(SuperClass obj){
 *      obj.m();
 * }
 *  在运行期间，obj的实际类型可能是SuperClass或者不同的子类SubClass1,SubClass12
 *  编译期间无法决定将调用哪个类方法，运行时也会着obj的实际类型调用不同的方法，因此这也叫作运行时多态或动态方法分派。
 *
 *
 *  Note:
 * 继承可以使用 extends 和 implements 这两个关键字来实现继承; 当一个类没有继承的两个关键字，则默认继承object,因此可以调用它的toString、equals等方法
 * java只支持单继承,子类只能有一个直接父类
 * 私有属性/方法和构造器不支持继承
 * 类的继承不改变类成员的访问权限，也就是说，如果父类的成员是公有的、被保护的或默认的，它的子类仍具有相应的这些特性
 * @author epanhai
 */
public class Animal {

    String species;
    String food;

    public Animal() {
        System.out.println(" public Animal() is invoked ");
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void eat() {
        System.out.println("eating...");
    }

}
