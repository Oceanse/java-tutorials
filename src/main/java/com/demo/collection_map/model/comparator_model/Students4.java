package com.demo.collection_map.model.comparator_model;

/**
 * @author epanhai
 */
public class Students4{
    private String name;
    private int age;

    public Students4() {
    }

    public Students4(String name, int age) {
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


    @Override
    public String toString() {
        return "Students3{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
