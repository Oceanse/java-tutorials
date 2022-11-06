package com.demo.generic.demo2;

import org.testng.annotations.Test;

import javax.websocket.Session;
import java.sql.Date;

/**
 * 泛型类定义：
 * class GenericClassDemo<K, V>，把<K,V>理解为定义在类中的类型变量(个人称之为类型形参或者类型变量), 之后成员变量，方法参数和返回值都可以使用类型形参
 *
 * 泛型类使用：
 * 泛型类通常是在实例化（创建对象）的时候指定具体的类型：类名<具体的数据类型> 对象名 = new 类名<具体的数据类型>();
 * Java1.7以后，后面的<>中的具体的数据类型可以省略不写， 菱形语法类名<具体的数据类型> 对象名 = new 类名<>();
 *
 * 注意：
 * 类型形参不能是基本类型
 * @author epanhai
 */
public class GenericClassDemo<K, V> {//此处K V是类类型形参，也被称为一个类型变量,实例化时候指定具体的类型

    /**
     *  泛型类、泛型接口的属性方法可以使用泛型变量或者泛型形参，我们可以称这些属性或者方法为“带泛型的属性方法”或者“使用泛型的属性方法”，
     *  因此带泛型的属性方法必须出现在泛型类/接口中;
     * 类类型形参充当属性类型
     */
    private K key;
    private V value;

    public GenericClassDemo() {
    }


    /**
     * 类类型形参充当参数类型，这个方法称之为使用泛型的方法，但不是泛型方法
     *
     * @param k
     * @param v
     */
    public GenericClassDemo(K k, V v) {
        this.key = k;
        this.value = v;
    }


    /**
     * 类类型形参充当返回值类型，这个方法称之为使用泛型的方法，但不是泛型方法
     *
     * @return
     */
    public K getKey() {
        return key;
    }


    /**
     * 类类型形参充当参数类型，这个方法称之为使用泛型的方法，但不是泛型方法
     *
     * @param key
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * 类类型形参充当返回值类型，这个方法称之为使用泛型的方法，但不是泛型方法
     *
     * @return
     */
    public V getValue() {
        return value;
    }


    /**
     * 类类型形参充当参数类型，这个方法称之为使用泛型的方法，但不是泛型方法
     *
     * @param value
     */
    public void setValue(V value) {
        this.value = value;
    }


    /**
     * 实例化时候指定具体的类型实参，类型参数只能是引用类型，不能是基本类型。
     */
    @Test
    public static void test() {
        GenericClassDemo<String, Integer> c = new GenericClassDemo<String, Integer>("ocean", 31);

        String name = c.getKey();
        int age = c.getValue();


        System.out.println("name" + " : " + name);
        System.out.println("age" + " : " + age);
    }


    /**
     *  实例化时候没有指定具体的类型实参，那么就按照Object类型处理，意味着在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型。
     *  定义的泛型类，就一定要传入泛型类型实参么？并不是这样，在使用泛型的时候如果传入泛型实参，则会根据传入的泛型实参做相应的限制，
     *  此时泛型才会起到本应起到的限制作用。如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型。
     */
    @Test
    public void test2() {

        GenericClassDemo c1 = new GenericClassDemo("date", new Date(System.currentTimeMillis()));
        GenericClassDemo c2 = new GenericClassDemo("number", new Double[]{1.0, 2.0});

        //只能用Object接收
        Object key = c1.getKey();
        Object value = c1.getValue();

        //只能用Object接收
        Object key2 = c2.getKey();
        Object value2 = c2.getValue();
    }
}