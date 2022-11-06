package com.demo.collection_map.model.comparable_model;

/**
 * @author epanhai
 */
public class Students2 implements Comparable<Students2>{
    private String name;
    private int age;

    public Students2() {
    }

    public Students2(String name, int age) {
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
     * 按照年龄排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(Students2 o) {
        return this.age-o.age;
    }
    @Override
    public String toString() {
        return "Students2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
