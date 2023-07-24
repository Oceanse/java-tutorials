package com.demo.generic.generic_introduce;

import com.demo.collection_map.model.Apple;
import com.demo.collection_map.model.Fruit;
import com.demo.collection_map.model.Grape;
import com.demo.collection_map.model.Orange;
import com.demo.oop.oopintrouce.oopintroduce.Person;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * https://www.cnblogs.com/qi-dian-ao/p/8463639.html
 * https://blog.csdn.net/Beyondczn/article/details/107093693
 *
 * 概念：
 * 泛型，即“参数化类型（Parameterized Type）或者类型参数化”。一提到参数，最熟悉的就是定义方法时有形参，然后调用此方法时传递实参。
 * 类似于方法中的形参，泛型就是将原来的具体的类型进行形参化，允许在定义类、接口、方法时把参数类型定义成形参形式（可以称之为类型形参）,个人又将类型形参分为类类型形参和方法类型形参；
 * 在创建对象、调用方法时动态地指定具体的类型（类型实参，即传入实际的类型参数）
 *
 * 优点：
 * Java 泛型（generics）是 JDK 5 中引入的一个新特性, 泛型提供了编译时类型安全检测机制，该机制允许我们在编译时检测到非法的类型数据结构
 * 1 类型安全, 编译时类型检查，消除了强制类型的转换
 * 2 提高了程序的灵活型和扩展性
 *
 *
 * 使用方式：
 * 泛型有三种使用方式，分别为：泛型类、泛型接口、泛型方法，都带有<>
 * 泛型类、泛型接口、泛型方法声明的类型参数也被称之为类型变量(类型形参)，
 * 泛型类、泛型接口的属性类型和方法的参数、返回值可以使用声明的泛型变量(类型形参)，我们可以称这些属性或者方法为“带泛型的属性方法”或者“使用泛型的属性方法”，因此带泛型的属性方法必须出现在泛型类中;
 * 泛型方法和泛型类/泛型接口无关，其可以出现在非泛型类中,也就是泛型方法独立于泛型类或者泛型接口
 * <p>
 *
 *
 * 举例：
 * 泛型接口：
 * public interface Map<K, V> {
 * public void put(K key, V value); //使用泛型的方法
 * <p>
 * public V get(K key);//使用泛型的方法
 * <p>
 * public <E> E getE(E e);//泛型方法，独立于泛型类
 * }
 * <p>
 * <p>
 * 泛型类：
 * public class Object<T> {
 * private T obj; //使用泛型的属性
 * <p>
 * ArrayList<T> list; //属性使用类类型形参作为自己的类型形参
 * <p>
 * public T getObj() {//使用泛型的方法
 * return obj;
 * }
 * <p>
 * public void setObj(T obj) {//使用泛型的方法
 * this.obj = obj;
 * }
 * <p>
 * public <E> E getE(E e){//泛型方法,独立于泛型类
 * return e;
 * };
 * }
 *
 * @author epanhai
 */
public class GenericDemo {


    /**
     * 泛型安全性
     * 未使用泛型的弊端
     */
    @Test
    public void testArrayListWithoutGeneric() {
        //没有使用泛型的时候就相当于使用了泛型类型为Object的泛型,List<Object> list=new ArrayList<>()
        //ArrayList不带泛型可以存放任意数据类型程，所以导致序运行后会发生ClassCastException
        ArrayList names = new ArrayList();
        names.add("tom");
        names.add("marry");
        //自动装箱
        names.add(123);
        //这里只能用Object类型接收，因为编译器不知道names集合中的元素类型
        for (Object name : names) {
            //name.toUpperCase(); //因为这里相当于Object name=String对象，不能调用String类独有的toUpperCase方法，需要向下转型
            //java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
            System.out.println(((String) name).toUpperCase());
        }
    }


    /**
     * 泛型安全性
     * 使用泛型的ArrayList
     */
    @Test
    public void testArrayListWithGeneric() {
        //使用泛型后，编译器会在编译阶段就能够确定集合中存放元素的类型
        //java7之前，必须写成：ArrayList<Integer> b = new ArrayList<Integer>();
        //在Java7中是可以通过前面的类型参数去推导出ArrayList中的数据类型的，也就是类型参数不需要了，
        // 但是这个<>尖括号是必须的，至于尖括号中的类型，是可以自动被推导出来的，这个就叫做菱形语法，为啥叫菱形语法嘞，因为这个<>尖括号像菱形
        //这里的Integer就是类型实参，类型形参在泛型类的定义中：public class ArrayList<E>
        ArrayList<String> names2 = new ArrayList<>();
        names2.add("ocean");
        names2.add("phy");
        //names2.add(1024); 在编译阶段，编译器就会报错
        //这里可以用String类型接收，因为编译器通过泛型知道names2集合中的元素类型,所以使用泛型可减少类型的转换
        for (String name : names2) {
            //因为这里相当于String name=String对象，能直接调用String类独有的toUpperCase方法，不需要向下转型，因此可以避免ClassCastException
            name.toUpperCase();
        }
    }


    /**
     *
     */
    @Test
    public void testSomeScenarioWithGeneric() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //Map使用泛型
        Map<String, Integer> info = new HashMap<>();
        info.put("ocean", 28);
        //map.put("mxz",27.5)   在编译阶段，编译器就会报错

        //iterator使用泛型
        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple());
        fruits.add(new Grape());
        fruits.add(new Orange());
        Iterator<Fruit> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            Fruit fruit = iterator.next();
            if (fruit instanceof Apple) {
                System.out.println("apple is here");
            }
        }


        //反射使用泛型
        Class<Person> personClass = Person.class;
        //class Constructor<T>
        Constructor<Person> constructor = personClass.getConstructor();
        //使用泛型后这里可以使用Person接收
        Person person = constructor.newInstance();
    }





}