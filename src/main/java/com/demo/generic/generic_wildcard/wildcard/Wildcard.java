package com.demo.generic.generic_wildcard.wildcard;

import org.testng.annotations.Test;

import java.util.*;

/**
 * 无界通配符：<?>
 *
 * 这里的泛型通配符?是一种特殊的类型实参，而不是类型形参，可以把?看成所有类型的父类；
 * List<A>,List<>B都是List<?>的子类
 *
 * 泛型通配符？可以出现非泛型类和非泛型方法中，
 * @author epanhai
 */
public class Wildcard {

    @Test
    public void test() {

        //向上转型1
        Object o;
        String s = "abc";
        o = s;

        // 向上转型2
        // 在Java语言中，数组是协变的, 如果 Number是 Integer 的超类型，那么 Number[] 也是 Integer[]的超类型
        Integer[] arr = new Integer[]{1, 2, 3};
        Number[] num;
        num = arr;

        //A是B的子类，List<A>不是List<B>的子类,这么做将破坏要提供的类型安全泛型;
        //假设list2=list成立，那么list和list2指向同一个堆对象，list2引用可以向堆中添加Double元素：list2.add(1.0)， list成立也就会指向Double堆，明显有问题
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Number> list2;
        //list2=list;   List<Number>不是List<Integer>的父类


        //泛型相同时引用可以传递
        List<Integer> list3 = Arrays.asList(1, 2, 3);
        List<Integer> list4;
        list4 = list3;
        list4.add(345);
    }


    /**
     * 无界通配符<?>类型通配符是成一种特殊的类型实参, 而不是类型形参，可以把？看成所有类型的父类；
     * List<A>,List<>B都是List<?>的子类
     */
    @Test
    public void testWildcard() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<?> list2;
        list2 = list;
    }


    /**
     * <?>类型通配符是成一种特殊的类型实参, 而不是类型形参，可以把？看成所有类型的父类；
     * List<A>,List<>B都是List<?>的子类
     * 泛型通配符可以出现非泛型类中
     * @param list
     */
    public static void printList(List<?> list) {
        for (Object o : list) {
            System.out.print(o + " ");
        }
    }

    @Test
    public static void testPrintList() {
        List<Double> list = Arrays.asList(1.0, 2.0);
        printList(list);

        List<Object> list2 = Arrays.asList('a', 'b', 1, "ocean");
        printList(list2);
    }


    /**
     * 泛型通配符可以出现非泛型类中
     * 当我们使用?号通配符的时候：就只能调对象与类型无关的方法，不能调用对象与类型有关的方法，因为直到外界使用才知道具体的类型是什么。
     * 比如下面的Set集合，我是不能使用add()方法的。因为add()方法是把对象丢进集合中，而现在我是不知道对象的类型是什么。
     * @param set
     */
    public void test5(Set<?> set) {
        // set.add(1); // 使用"?"的集合不能添加元素，因为添加元素之前编译器无法知道具体的类型是什么,也就是不确定list类型的最小值
        // set.add(23);// 使用"?"的集合不能添加元素，因为添加元素之前编译器无法知道具体的类型是什么,也就是不确定list类型的最小值

        //可以调用与类型无关的方法
        set.size();
        set.clear();
    }

}
