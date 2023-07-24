package com.demo.basic.method.valuepass;

import org.testng.annotations.Test;

/**
 * Java 里方法的参数传递方式只有一种：值传递。所谓们传递，就是将实际参数值的副本（复制品）传入力法内，而参数本身不会受到任何影响。
 * 如果参数是引用类型，那么值传递的时候传递的就是对象的地址，因此对象的地址(引用变量的值)不会改变，但是指向对象的内容可能会改变
 * 类比：
 * User user=new User();
 * User user2=user;
 * @author epanhai
 */
public class ReferenceValuePass {


    /**
     * 引用变量保存的值是堆内存地址，所以参数是引用变量时传递的就是地址
     *
     * 类比：
     * User user=new User();
     * User user2=user;
     */
    @Test
    public void testAddAge(){
        User user = new User();//user引用类型变量保存着对象在堆内存地址
        user.setAge(30);
        System.out.println("main-beforeAddAge: "+user.getAge());
        //这里会把user变量值，也就是对象堆内存地址的副本传递给被调方法的参数变量，然后被调方法的参数变量也会保存着上面User对象在堆内存地址, 和user变量所指向相同的对象
        //user变量的值(对象的地址)永远不会变，但指向对象的内容可能会发生变化
        addAge(user);
        System.out.println("main-afterAddAge: "+user.getAge());
    }

    public static void addAge(User user){
        user.setAge(user.getAge()+1);
    }
}

class User {
    private int age;

    String[] hobby;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }
}
