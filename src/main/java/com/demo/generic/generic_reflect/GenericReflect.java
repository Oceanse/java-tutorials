package com.demo.generic.generic_reflect;

import com.demo.oop.oopintrouce.oopintroduce.Person;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

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
        Object obj = constructor2.newInstance();
        Person person2=(Person)obj;
    }


    /**
     * Array.newInstance动态创建数组
     * @param type
     * @param sz
     * @return
     * @param <T>
     */
    public <T> T[] getArray(Class<T> type, int sz) {
        T[] array = (T[]) Array.newInstance(type, sz);
        return array;
    }

    @Test
    public void test() throws ClassNotFoundException {
        Class<Integer> clazz = Integer.class;
        Integer[] array = (Integer[]) Array.newInstance(clazz, 10);
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }
        System.out.println(Arrays.toString(array));
    }


    private static Object resizeArray (Object oldArray, int newSize) {
        //获取数组oldArray的长度
        int oldSize = Array.getLength(oldArray);
        //获取数组oldArray的元素类型
        Class elementType = oldArray.getClass().getComponentType();
        //实例一个新的数组 类型和oldArray的一样 长度参数传入的newSize
        Object newArray = Array.newInstance(elementType,newSize);
        //得到新数组newArray 和oldArray两个中长度最短的，并把长度返回给preserveLength
        int preserveLength = Math.min(oldSize,newSize);
        //数组内容复制
        if (preserveLength > 0) {
            System.arraycopy (oldArray, 0, newArray, 0, preserveLength);
        }
        return newArray;
    }
}
