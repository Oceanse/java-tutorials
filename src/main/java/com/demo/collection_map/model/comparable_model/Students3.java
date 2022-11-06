package com.demo.collection_map.model.comparable_model;

import java.util.TreeSet;

/**
 * @author epanhai
 */
public class Students3 implements Comparable<Students3>{
    private String name;
    private int age;

    public Students3() {
    }

    public Students3(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    /**
     * 先按照年龄排序，年龄相同的再按照字符串排序。
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Students3 o) {
        int num = this.age - o.age;
        // String类里面已经重写了compareTo方法
        // int compareTo(String anotherString)  按字典顺序比较两个字符串
        return num == 0 ? this.name.compareTo(o.name) : num;
    }


   /*另一种写法
    @Override
    public int compareTo(Object obj) {
        Person p = (Person) obj;
        System.out.println(this+" compareTo:"+p);
        if (this.age > p.age) {
            return 1;
        }
        if (this.age < p.age) {
            return -1;
        }
        return this.name.compareTo(p.name);
    }*/

    @Override
    public String toString() {
        return "Students3{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
