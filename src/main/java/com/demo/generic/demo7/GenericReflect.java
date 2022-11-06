package com.demo.generic.demo7;

import com.demo.oop.OOPDemo.demo1.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GenericReflect {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //class Class<T>
        Class<Person> personClass = Person.class;
       //class Constructor<T>
        Constructor<Person> constructor = personClass.getConstructor();
        //使用泛型后这里可以使用Person接收
        Person person = constructor.newInstance();



        Class personClass2 = Person.class;
        Constructor constructor2 = personClass.getConstructor();
        //不使用泛型只能用Object接收
        Object person2 = constructor2.newInstance();
    }
}
