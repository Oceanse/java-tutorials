package com.demo.oop.constructor;

public class ClassRoom {

    /**
     * 面积
     */
    double area;

    /**
     * 单参构造方法会覆盖默认的空参构造方法
     * @param area
     */
    public ClassRoom(double area) {
        this.area = area;
    }
}
