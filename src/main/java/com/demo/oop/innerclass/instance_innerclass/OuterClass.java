package com.demo.oop.innerclass.instance_innerclass;


import org.junit.Test;

/**
 * 内部类分类：实例内部类，静态内部类，局部内部类，分别想象成实例成员，静态成员，局部成员(局部变量)
 *
 * 把实例成员内部类InnerClass想象成外部类一种普通的引用类型的实例成员变量
 *
 * 实例内部类对象声明：
 *   实例内部类对象声明完整格式：OuterClass.InnerClass in
 *   在外部类内部声明内部类对象时候可以省略外部类限制符，直接InnerClass in, 其实编译器会自动添加上外部类限制符，等价于 OutterClass.InnerClass in
 *
 *  实例内部类对象创建：
 *    实例成员内部类理解为外部类的一个成员，所以实例成员内部类对象是依附外部类对象而存在的，也就是说，如果要创建实例成员内部类的对象，前提是必须存在一个外部类的对象
 *    外部类内部创建语法： InnerClass in = 外部类对象.new InnerClass(xxx)
 *                     若this等价于当前外部类对象，在外部类实例方法中这样创建：InnerClass in = this.new InnerClass(xxx) 或者省略this: InnerClass in = new InnerClass(xxx)
 *
 *  内部类访问外部类静态成员：外部类名.外部类静态成员
 *  内部类访问外部类实例成员：外部类对象.外部类成员 或者 外部类.this.外部类实例成员
 *  在内部类中，外部类.this可以理解为当前外部类对象的引用
 *  实例成员内部类理解为外部类的一个成员，所以内部类对象可以无条件直接访问外部类所有成员变量或者方法，包括private成员变量或者方法
 *
 *  外部类访问内部类实例成员：内部类对象.内部类成员
 *          外部类对象不能直接访问内部类的成员变量或者方法，因为外部类对象存在的时候内部类对象可能不存在，所以内部类的成员变量或者方法也不存在，因此无法通过外部类对象直接访问内部类的成员变量或者方法，
 *          必须通过内部类对象去访问
 *
 * 内部类中不能出现静态变量
 * 我们可以把内部类看成外部类的非静态成员，它的初始化必须在外部类对象创建后以后进行，要加载内部类必须在实例化外部类之后完成,
 * java虚拟机要求所有的静态变量初始化必须在对象创建之前完成，另外要先类加载，然后静态变量初始化，因此内部类中不能出现静态变量
 * java常量放在内存中常量池，它的机制与变量是不同的，编译时，加载常量是不需要加载类的
 */
public class OuterClass {
    private static int id=1;
    private String name="outerInstanceName";//1

    public void showOuterName(){
        System.out.println(name);
    }




    class InnerClass{
        //private static int id=2;//内部类中不能出现静态变量
        private static final int id=2;
        private String name="innerInstanceName";//2

        /**
         * 成员内部类理解为外部类的一个成员，所以可以访问外部类private成员变量
         * 外部类成员变量1和内部类成员变量2及内部类局部变量3同名时候，系统查找顺序：3--->2--->1（就近访问）
         * 内部类中显式访问外部类成员语法：外部类.this.外部类成员
         * 内部类中显式访问内部类成员语法：this.成员
         */
        public void showName(){
            String name="innerLocalName";//3
            System.out.println(name);//内部类局部变量
            System.out.println(this.name);//this代指内部类对象引用
            System.out.println(new OuterClass().name);//外部类对象.外部类成员
            System.out.println(OuterClass.this.name);//外部类.this.外部类实例成员
            System.out.println(OuterClass.id);//外部类名.外部类静态成员

            OuterClass.this.showOuterName();//外部类.this代指外部类对象引用
            new OuterClass().showOuterName();// java常量放在内存中常量池，编译时加载常量是不需要加载类的
        }
    }

    public static void main(String[] args) {
        //实例成员内部类构造方法想象成外部类的一个普通方法,当前外部类对象可能还不存在
        //InnerClass in=this.new InnerClass();

        //通过外部类对象直接创建内部类对象
        OuterClass.InnerClass innerInstance = new OuterClass().new InnerClass();
        //外部类内部声明内部类对象时候,编译器会自动添加上外部类限制符OuterClass
        InnerClass innerInstance2 = new OuterClass().new InnerClass();

        //通过内部类对象访问内部类成员，包括私有成员
        System.out.println(innerInstance.name);
        innerInstance.showName();
    }


    /**
     *  若this等价于当前外部类对象，在外部类实例方法中这样创建：
     *  InnerClass in = this.new InnerClass(xxx) 或者省略this: InnerClass in = new InnerClass(xxx)
     */
    @Test
    public void test(){
        InnerClass innerInstance = this.new InnerClass();
        InnerClass innerInstance2 = new InnerClass();//省略this
    }
}


class OuterClassTest{
    public static void main(String[] args) {
        //外部类外部声明内部类对象必须添加上外部类限制符： 外部类.内部类 引用名
       OuterClass.InnerClass innerClass = new OuterClass().new InnerClass();
    }
}