package com.demo.generic.generic_method;

import com.demo.collection_map.model.comparable_model.Students3;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GenericMethodDemo2 {

    /**
     *  返回值前面的<T>声明次方法是泛型方法，这里返回值和参数都使用了方法泛型形参
     * @param tClass
     * @return
     * @param <T>
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> T getInstance(Class<T> tClass) throws InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        T instance = tClass.getDeclaredConstructor().newInstance();
        return instance;
    }

    @Test
    public void testGetInstance() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<Students3> students3Class=Students3.class;
        Students3 instance = getInstance(students3Class);
    }


    /**
     * 可变参数构造ArrayList,这里返回值和参数都使用了方法泛型形参
     * @param t
     * @return
     * @param <T>
     */
    public  <T> ArrayList<T> asArrayList(T... t) {
        ArrayList<T> list=new ArrayList(t.length);
        for (T t1 : t) {
            list.add(t1);
        }
        return list;
    }


    @Test
    public void testAsArrayList() {
        ArrayList<String> members = asArrayList("ocean", "mama", "baba","brother");
        ArrayList<Integer> numbers = asArrayList(125,150);
    }



    /**
     * 可变参数构造数值型ArrayList,这里返回值和参数都使用了方法泛型形参，泛型形参的上界是Number
     * @param t
     * @return
     * @param <T>
     */
    public  <T extends Number> ArrayList<T> asNumberArrayList(T... t) {
        ArrayList<T> list=new ArrayList(t.length);
        for (T t1 : t) {
            list.add(t1);
        }
        return list;
    }

    @Test
    public void testAsNumberArrayList() {
        ArrayList<Integer> numbers = asNumberArrayList(125,150);
       // ArrayList<String> members = asNumberArrayList("ocean", "mama", "baba","brother"); //编译不通过
    }

    /**
     * 带泛型上界的泛型方法
     * 类型形参 T extends Comparable<T> 表明传入方法中的类型必须实现了 Comparable 接口。
     * @param x
     * @param y
     * @param z
     * @return
     * @param <T>
     */
    public <T extends Comparable<T>> T max(T x, T y, T z) {
        T max = x; // 假设x是初始最大值
        if (y.compareTo(max) > 0) {
            max = y; //y 更大
        }
        if (z.compareTo(max) > 0) {
            max = z; // 现在 z 更大
        }
        return max; // 返回最大对象
    }

    @Test
    public void testMax() {
        System.out.println(max(3, 4, 5));//这里会自动装箱
        System.out.println(max('a', 'c', 'b'));//这里会自动装箱
        System.out.println(max("pear", "apple", "orange"));
        System.out.println(max(new Students3("ocean",33), new Students3("phy",33), new Students3("Haiyang",33)));
    }



}
