package com.demo.oop.inherit.demo4_private;

/**
 * 子类继承父类后，子类就拥有了父类全部的属性和行为，可以把继承过来的属性和方法全部脑补到子类当中；
 * 只不过对于父类私有属性和方法，子类只能间接拥有(通过继承父类暴露的getter/setter/constructor等方法间接访问和修改)
 */
public class Student extends Person{
    public int id;

    /**
     * 说明正在创建的Student对象拥有id属性和name属性
     * @param id
     * @param name
     * @param age
     */
    public Student(int id, String name, int age) {
        //this.age=age; //当前正在创建的Student对象不能直接访问父类中的私有age属性
        //父类暴露的public constructor修改父类私有属性age
        super(age);
        this.id = id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {

        //父类暴露的constructor修改父类私有属性age
        Student s=new Student(1572116,"ocean",25);

        // public int getAge() {return age;}是父类pubic方法，被子类继承后这个方法就变成子类自己的方法(继承过来就是自己的了)，子类可以通过某些暴露的方法访问继承过来的私有属性
        System.out.println(s.getAge());

        // public int setAge() {this.age = age;;}是父类pubic方法，被子类继承后这个方法就变成子类自己的方法(继承过来就是自己的了)， 子类可以通过某些暴露的方法访问继承过来的私有属性
        s.setAge(30);
        System.out.println(s.getAge());


        //age has private access in 'com.demo.oop.inherit.demo3_private.Person'
        //private类型的数据成员只能在定义的类内部被定义类的类型对象(编译及运行时类型)直接访问和修改修改(对象.属性)，其余任何地方都不可以直接修改
        //简单来说就是要满足一是在定义类中操作，二是对象的编译及运行时类对象都属于定义属性的类
         Person p=new Person();
         //System.out.println(p.age);
    }

}
