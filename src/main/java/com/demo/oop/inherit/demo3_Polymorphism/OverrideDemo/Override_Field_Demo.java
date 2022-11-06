package com.demo.oop.inherit.demo3_Polymorphism.OverrideDemo;

/**
 * 子类和父类具有相同的属性,在使用子类对象(没有父引用指向子类对象)调用同名属性时候调用的时候子类的属性，
 * 可以理解为父类属性被子类同名属性暂时隐藏，父类属性在子类方法中不能被直接访问，但是可以通过super访问，
 * 另外如果父类属性是静态变量，还可以通过父类类名访问；
 *
 * 方法中访问某个变量a,没有显式指定调用者，系统查找a的顺序是：
 * 1 查找方法中是否存在名为a的局部变量
 * 2 查找当前类中是否存在名为a的成员变量
 * 3 查找当前类的直接父类中是否存在名为a的成员变量，依次上溯到当前类的所有父类，直到java.lang.Object, 如果找不到，系统会编译报错
 *
 * 补充：当创建子类对象时候，系统会为子类属性和从父类继承来的属性分配内存，即使父类属性和子类属性重名
 */
public class Override_Field_Demo {
    String id="37xxx";//身份证号

    public Override_Field_Demo() {
    }

    public Override_Field_Demo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}




class Stu extends Override_Field_Demo {
    String id="15xx";//学号

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }


    /**
     *  访问当前对象的id
     */
    public void accessStuId() {
        System.out.println(this.id);
    }


    /**
     * 调用当前类对象的父类对象的id
     */
    public void accessAnimalId() {
        System.out.println(super.id);
    }

    public static void main(String[] args) {

        //创建子类对象时候一定会先创建父类对象
        Stu s=new Stu();
        s.accessStuId();
        s.accessAnimalId();

        Override_Field_Demo s2=new Stu();
        System.out.println(s2.id);

    }
}
