package com.demo.generic.generic_wildcard.wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * 大多时候，我们都可以使用泛型方法来代替通配符的.....
 * 下面这printList和printList2两个方法都是可以的.
 *
 * 原则：
 * 如果参数之间的类型有依赖关系，或者返回值是与参数之间有依赖关系的。那么就使用泛型方法
 * 如果没有依赖关系的，就使用通配符，通配符会灵活一些.
 */
public class Wildcard2 {


    /**
     * 泛型方法
     * @param list
     * @param <E>
     */
    public static <E> void printList(List<E> list) {
        for (E e : list) {
            System.out.println(e);
        }
    }

    /**
     * 无界通配符
     * @param list
     */
    public static void printList2(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        Wildcard2.printList(new ArrayList<Number>());
        Wildcard2.printList2(new ArrayList<Number>());
    }
}
