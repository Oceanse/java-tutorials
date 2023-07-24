package com.demo.oop.inherit.demo3_Polymorphism.OverrideDemo;

/**
 * 重写(Override): 方法覆盖需要和多态联合使用才会凸显其真正的价值
 * <p>
 * 当父类实例方法不能满足子类需求，可以对父类继承的方法进行重写,重写只是针对方法，不针对属性，重写后子类只拥有重写后的方法
 * 也就是子类对象不能再去直接调用父类中的方法； 如果非要通过子类对象调用父类方法，可以在子类的某个方法中通过super实现如下：{super.父类被重写的方法}
 * <p>
 * 重写规则：
 * 重写方法的方法名、参数必须和被重写的方法保持一致
 * 重写后方法的权限不能变小，可以变大
 * 重写后方法的返回值类型是引用类型的话可以更小或者相等，基本类型则必须相同，一般情况下保持相同即可
 * 重写后方法不能抛出更多的异常
 *
 * 构造方法不能被继承，所以就不能被重写
 *
 * 静态方法不能被重写：
 * 重写一般和多态结合使用，也就是根据运行时对象的不同而调用不同的方法，而静态方法是类级别，通过类名调用，无需创建对象即可使用，
 * 所以可以在子类中定义和父类完全相同的方法，但是不能添加@Override,因此我们能不称之为重写，而是相当于定义了一个新的静态方法，
 * 方法覆盖只是针对实例方法，而非静态方法
 *
 * 私有方法不能被重写：
 * 如果子类中定义了一个与父类private方法具有相同的方法名、相同的形参列表、相同的返回值类型的方法，依然不是重写，只是在子类中重新定义了一个新方法
 */
public class Animal {

    void move() {
        System.out.println("移动");
    }

    Object species() {
        return "Animal";
    }


    /**
     * 静态方法不能被重写
     */
    public static void sleep() {
        System.out.println("sleep...");
    }


    /**
     * 私有方法不能被重写
     */
    private void babyCount() {
        System.out.println(2);
    }


    public static void main(String[] args) {
        Animal a = new Sparrow();
        a.move();
    }
}


class Bird extends Animal {

    String Swing = "Bird的翅膀";

    //重写后方法的权限可以变大
    @Override
    public void move() {
        System.out.println("Bird飞翔");
    }


    /*
    重写后方法的权限不能小于被重写方法的权限
    @Override
    private void move() {
        System.out.println("飞翔");
    }*/


    // 重写后方法的返回值类型比被重写的方法的返回值类型更小或者相等
    @Override
    public String species() {
        return "Bird";
    }


}


class Sparrow extends Bird {

    String Swing = "Sparrow的翅膀";

    @Override
    public void move() {
        System.out.println("麻雀飞翔");
    }


    /**
     * 通过super调用父类被重写的方法
     */
    public void superClassMove() {
        super.move();
    }



  /*
    静态方法不能被重写
    @Override
    public static void sleep(){
        System.out.println("123sleep...");
    }
    */

    /**
     * 私有方法不能被重写，即使方法和父类完全相同，也会被认为时当前类新增的方法
     */
    //@Override  //这里添加@Override会编译报错
    private void babyCount() {
        System.out.println(1);
    }

    public static void main(String[] args) {
        new Sparrow().move();
        new Sparrow().superClassMove();
        //super.move(); //super不能出现在静态方法中
        new Sparrow().showSwing();
    }

    public void showSwing() {
        System.out.println(this.Swing);
        System.out.println(super.Swing);
    }

}

