package com.demo.oop.innerclass.static_innerclass;


/**
 * bulider建造者模式
 */
public class Person {
    private String name;
    private String role;
    private int age;


    /**
     *构造函数私有,只允许通过静态内部类的build()方法创建Person类实例
     */
    private Person() {
    }


    /**
     * 静态内部类
     */
    public static class Builder {
        private String name;
        private String role;
        private int age;

        public Builder() {
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        //返回外部类实例
        public Person build() {
            Person person = new Person();
            if (name != null) {
                person.name = name;
            }
            if (role!= null) {
                person.role = role;
            }
            person.age =age;
            return person;
        }

    }

}

class Client {

    public static void main(String[] args) {
        //创建内部类对象：外部类对象.内部类构造函数
        Person.Builder builder = new Person.Builder()
                .setName("arong")
                .setAge(20);

        //通过内部类创建外部类对象1
        Person person1 = builder.build();

        //通过内部类创建外部类对象2
        Person person2 = new Person.Builder()
                .setAge(23)
                .setName("老冯~~~")
                .setRole("重庆军统")
                .build();
    }
}