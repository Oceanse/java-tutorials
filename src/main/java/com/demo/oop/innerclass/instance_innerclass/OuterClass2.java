package com.demo.oop.innerclass.instance_innerclass;


/**
 * 在外部类构造器中创建内部类对象
 */
public class OuterClass2 {

    private String name;

    /**
     * 把实例成员内部类InnerClass想象成外部类一种普通的引用类型的实例成员变量
     * 这里实际上是OuterClass2.InnerClass in,外部类内部声明内部类对象时候会自动添加上外部类限制符
     */
    InnerClass in;

    public OuterClass2() {
    }

    /**
     * 内部类构造方法是外部类对象可以调用的内部类成员，内部类的实例变量、方法成员，外部类是不能直接调用的，因为这个时候内部类对象可能还存在
     * 可以把实例内部类的构造方法想象成外部类的一个普通方法，通过外部类对象.方法名调用
     * 外部类中this指当前外部类对象引用，内部类中需要使用 外部类.this 代指外部类对象引用
     * @param name
     */
    public OuterClass2(String name) {
        this.name = name;
        in = this.new InnerClass(name);
    }

    class InnerClass {
        private String name = "innerInstanceName";

        public InnerClass() {
        }

        public InnerClass(String name) {
            this.name = name;
        }

        /**
         * 成员内部类理解为外部类的一个成员，所以可以访问外部类private成员变量
         * 外部类成员变量1和内部类成员变量2及内部类局部变量3同名时候，系统查找顺序：3--->2--->1
         * 显式访问外部类成员变量1语法：外部类.this.成员变量
         * 显式访问内部类成员变量2语法：this.成员变量
         */
        public void showName() {
            String name = "innerLocalName";
            System.out.println(name);
            System.out.println(this.name);
            System.out.println(OuterClass2.this.name);//内部类中需要使用 外部类.this 代指外部类对象引用
        }
    }


    public static void main(String[] args) {
        new OuterClass2("ocean").in.showName();
        OuterClass2.InnerClass innerClass = new OuterClass2().new InnerClass();
        innerClass.showName();

    }
}
