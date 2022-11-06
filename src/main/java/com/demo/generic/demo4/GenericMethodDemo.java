package com.demo.generic.demo4;

import com.demo.collection_map.model.Product;
import org.testng.annotations.Test;

import java.util.*;

/**
 * 使用背景：
 * 有时候我们可能就仅仅在某一个方法上需要使用泛型， 外界仅仅是关心该方法，不关心类其他的属性或者方法，这样的话如果我们在整个类上定义泛型，未免就有些小题大做了，这时候我们
 * 可以使用为某个方法量身定制的泛型方法
 *
 * 语法：
 * 修饰符 <E> 返回值类型 方法名(形参列表) { 方法体... }, 其中<E>表明该方法将使用泛型类型E，只有在返回值之前声明了一个由尖括号包围的类型形参,才是泛型方法，此时才可以在方法中使用泛型类型E。
 *      eg:  public <E> E getE(E e) {return e;}
 * 泛型类中的使用了泛型的成员方法并不是泛型方法,个人称之为使用泛型的方法
 * 泛型方法声明的类型形参可以用来指定返回值类型和参数类型，但是不能用来指定属性类型
 * 类型形参只能代表引用型类型，不能是原始类型
 *
 * 使用：
 * 泛型方法通常在被调用的时候传入类型实参
 *
 *
 * 泛型类和泛型方法:
 * 泛型类和泛型方法并存,类类型变量和方法类型变量可以不同;实际上泛型类和泛型方法是相互独立的
 * 泛型类在类中声明类参数变量
 * public static <E> void printArray( E[] inputArray)： 把<E>理解为定义在方法中的类型变量(方法类型形参), 之后方法参数和返回值都可以使用类型变量
 * class GenericClassDemo<K,V>: 把<K,V>理解为定义在类中的类型变量(类类型形参), 之后变量，方法参数和返回值都可以使用类型变量
 * @author epanhai
 */
public class GenericMethodDemo {


    /**
     * 你可以通过定义方法类型形参来声明一个泛型方法，该方法在调用时可以接收不同类型的参数。根据传递给泛型方法的参数类型，编译器适当地处理每一个方法调用
     * 泛型方法中定义的类型变量可以用在方法的参数和返回值
     * 在调用泛型方法的时候指明泛型的具体类型
     *
     * @param inputArray
     * @param <E>
     */
    public static <E> void printArray(E[] inputArray) {
        // 输出数组元素
        for (E element : inputArray) {
            System.out.print(element + " ");
        }
    }

    @Test
    public void test() {
        // 创建不同类型数组： Integer, Double 和 Character
        Integer[] intArray = {1, 2, 3, 4, 5};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4};
        Character[] charArray = {'H', 'E', 'L', 'L', 'O'};

        System.out.println("整型数组元素为:");
        // 传递一个整型数组
        GenericMethodDemo.printArray(intArray);

        System.out.println("\n双精度型数组元素为:");
        // 传递一个双精度型数组
        GenericMethodDemo.printArray(doubleArray);

        System.out.println("\n字符型数组元素为:");
        // 传递一个字符型数组
        GenericMethodDemo.printArray(charArray);
    }


    /**
     * 你可以通过定义方法类型形参来声明一个泛型方法，该方法在调用时可以接收不同类型的参数。根据传递给泛型方法的参数类型，编译器适当地处理每一个方法调用
     * 泛型方法中定义的类型变量可以用在方法的参数和返回值
     * 在调用泛型方法的时候指明泛型的具体类型
     *
     * @param e
     */
    public static <E> E returnElement(E e) {
        return e;
    }

    @Test
    public void test2() {
        //在调用泛型方法的时候指明泛型的具体类型
        Integer integer = returnElement(1);
        Boolean aBoolean = returnElement(true);
        Character character = returnElement('c');
    }


    //数组复制到list
    public <E> List<E> fromArrayToList(E[] e, List<E> list) {
        for (E e1 : e) {
            list.add(e1);
        }
        return list;
    }


    @Test
    public void test3() {
        //在调用泛型方法的时候指明泛型的具体类型
        List<Integer> l = new ArrayList<>();
        List<Integer> integers = fromArrayToList(new Integer[]{1, 2, 3}, new ArrayList<Integer>());
        System.out.println(integers);
    }


    /**
     * 静态泛型方法
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T getT(T t) {
        return t;
    }

    @Test
    public void test4() {
        //调用静态方法
        Boolean t = getT(true);
        System.out.println(t);
    }


    /**
     * 泛型方法中使用可变参数
     *
     * @param e
     * @param <E>
     */
    public <E> void getEles(E... e) {
        // 输出数组元素
        for (E element : e) {
            System.out.println(element);
        }
    }

    @Test
    public void test5() {
        Integer[] intArray = {1, 2, 3, 4, 5};
        this.getEles(intArray);

        this.getEles(new Product(2, "洗发水", 12), new String("ocean"));
    }


    /**
     * 类型参数 T extends Comparable<T> 表明传入方法中的类型必须实现了 Comparable 接口。
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
    public void test6() {
        System.out.println(max(3, 4, 5));
        System.out.println(max(6.6, 8.8, 7.7));
        System.out.println(max("pear", "apple", "orange"));
    }


}