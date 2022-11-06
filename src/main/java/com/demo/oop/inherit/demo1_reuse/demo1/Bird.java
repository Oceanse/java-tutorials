package com.demo.oop.inherit.demo1_reuse.demo1;

/**
 * 子类扩展了新的属性和方法
 * 把Bird继承过来的属性和方法脑补进来
 */
public class Bird extends Animal {

    String swingsShape;

    public Bird() {
        //这里会默认调用父类Animal的空参构造函数: super();
        System.out.println(" public Bird() is invoked");
    }


    public String getSwingsShape() {
        return swingsShape;
    }

    public void setSwingsShape(String wingsShape) {
        this.swingsShape = wingsShape;
    }

    public void fly() {
        System.out.println("Fly...");
    }
}
