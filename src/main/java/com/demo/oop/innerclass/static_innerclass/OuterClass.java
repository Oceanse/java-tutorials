package com.demo.oop.innerclass.static_innerclass;

/**
 * 静态内部类：class OuterClass {static class InnerClass {}}
 *
 * 静态内部类对象初始化：外部类类名.内部类类名 xxx = new 外部类类名.内部类类名(参数)，   OuterClass.InnerClass in= new OuterClass.InnerClass(参数);
 * 静态内部类可以想象成静态属性或者静态方法，是不需要依赖于外部类对象而存在，所以在没有外部类的对象的情况下，也可以创建静态内部类的对象，
 * 也就是静态内部类不用产生外部类的实例化对象即可产生内部类的实例化对象，静态内部类对象是寄生在外部类的类本身，而不是寄生在外部类的对象中
 *
 * 外部类访问静态内部类静态成员：
 * 外部类外部： 外部类类名.内部类类名.静态成员
 * 外部类内部： 内部类类名.静态成员，也就是可以省略外部类名
 *
 * 外部类访问静态内部类实例成员：
 * 内部类对象.实例成员
 *
 * 外部类内部可以访问内部类私有属性
 * 外部类外部不能访问内部类私有成员
 *
 * 静态内部类访问外部类静态成员：
 * 外部类类名.静态成员
 *
 * 静态内部类访问外部类实例成员：
 * 外部类对象.成员变量
 * 静态内部类不能直接访问或者通过OuterClass.this访问外部类实例成员变量或者方法，因为外部类对象可能不存在
 *
 *

 *
 */
public class OuterClass {

    private String name="outInstanceName";
    private static int age=30;


    static class InnerClass{

        public String name="innerInstanceName";
        private static double weight =125.0;
        public static double height =173.0;


        public InnerClass() {
        }

        public void showInfo(){
            String localName="localName";
            System.out.println(localName);//访问内部类局部变量
            System.out.println(this.name);//访问内部类实例变量,this代指内部类对象引用
            System.out.println(new OuterClass().name);//访问外部类实例成员： 外部类对象.成员变量
            System.out.println(OuterClass.age);//访问外部类静态成员: 外部类类名.静态成员
            //System.out.println(StaticOuterClass.this.name);//静态内部类不可以用OuterClass.this作为外部类对象的引用，因为此时外部类对象可能好不存在
        }

        public static double showWeight(){
            return weight;
        }
    }

    public static void main(String[] args) {
        //静态内部类对象是寄生在外部类的类本身，而不是寄生在外部类的对象中：外部类类名.内部类类名 xxx = new 外部类类名.内部类类名()
        InnerClass innerClass = new InnerClass();

        //外部类内部访问内部类实例成员,包括私有成员
        System.out.println(innerClass.name);
        innerClass.showInfo();

        //外部类内部访问内部类静态成员：外部类类名.内部类类名.静态成员
        //外部类内部可以访问内部类私有属性
        System.out.println(OuterClass.InnerClass.weight);
        System.out.println(InnerClass.weight);//外部类内部时候可以省略外部类限制符
        System.out.println(OuterClass.InnerClass.showWeight());
        System.out.println(InnerClass.showWeight());//外部类内部时候可以省略外部类限制符
    }
}


class TestStaticInnerClass{
    public static void main(String[] args) {

        //外部类外部声明内部类对象引用时候必须添加上外部类限制符
        OuterClass.InnerClass innerInstance= new OuterClass.InnerClass();
        //外部类外部访问内部类实例成员
        System.out.println(innerInstance.name);

        //外部类外部访问内部类静态成员：必须添加上外部类限制符
        System.out.println(OuterClass.InnerClass.height);
        System.out.println(OuterClass.InnerClass.showWeight());

        //外部类外部不能访问外部类私有成员，更不能访问内部类私有成员
        //System.out.println(OuterClass.InnerClass.weight);
    }
}
