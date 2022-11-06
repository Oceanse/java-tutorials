package com.demo.generic.demo6.wildcard;

import java.util.ArrayList;
import java.util.List;

/**
 * 大多时候，我们都可以使用泛型方法来代替通配符的.....
 * 下面这test和test2两个方法都是可以的.
 *
 * 原则：
 * 如果参数之间的类型有依赖关系，或者返回值是与参数之间有依赖关系的。那么就使用泛型方法
 * 如果没有依赖关系的，就使用通配符，通配符会灵活一些.
 */
public class Wildcard2 {

    /**
     * 未使用通配符
     * @param list
     * @param <E>
     */
    public static <E> void test(List<E> list) {
    }

    /**
     * 使用通配符
     * @param list
     */
    public static void test2(List<?> list) {

    }

    public static void main(String[] args) {
        Wildcard2.test(new ArrayList<Number>());
        Wildcard2.test2(new ArrayList<Number>());
    }
}
