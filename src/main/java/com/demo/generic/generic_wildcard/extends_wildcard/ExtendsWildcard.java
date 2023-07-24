package com.demo.generic.generic_wildcard.extends_wildcard;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 上界通配符： <? extends 类型实参上界>， 限制未知类型实参的上界,要求该传入的类型实参只能是上界类型或者其子类
 * Set<? extends Number>可以看成Set<Float>，Set<Double>的父类， extends通配符想象成：(-无穷大，上界)，左区间 G(-无穷大，上界) 是G<? extends 上界>的子类
 * Number是Float,Double的父类，Set<? extends Number>是Set<Float>，Set<Double>的父类，这称之为协变
 * 上界通配符：只读不写，典型的就是Set<? extends Number>可以遍历，但是不能添加元素，因为不能确定类型的最小值
 * Set<? extends Number>,这里?是一种类型实参，所以<? super Number>可以看成带区间性质的类型实参
 */
public class ExtendsWildcard {

    /**
     * Set<?>可以看成Set<Float>，Set<Double>的父类
     * Set<? extends Number>也可以看成Set<Float>，Set<Double>的父类
     * 可以把Set<?>想象成无界父类，Set<? extends Number>想象成有界父类
     */
    @Test
    public void testExtendsWildcard(){
        Set<? extends Number> numbers = new HashSet<>();
        Set<Float> floats = new HashSet<>();
        Set<Double> doubles = new HashSet<>();
        numbers=floats;
        numbers=doubles;
    }

    /**
     * 把Set<?>想象成无界父类，Set<? extends Number>想象成有界父类
     * @param numbers
     */
    public static void printSet(Set<? extends Number> numbers) {
        //numbers集合中的每个元素类型都是Number类型或者其子类类型
        for (Number number : numbers) {
            System.out.print(number + " ");
        }
    }

    @Test
    public static void testPrintSet() {
        // Set<? extends Number> set;  //<? extends ...>泛型上限, set包含的元素类型是 Number 及其子类
        Set<Float> numbers = new HashSet<>();
        numbers.add(1.0f);
        numbers.add(2.0f);
        numbers.add(3.0f);
        printSet(numbers);

        System.out.println();
        Set<Double> numbers2 = new HashSet<>();
        numbers2.add(1.0);
        numbers2.add(2.0);
        numbers2.add(3.0);
        printSet(numbers2);

        Set<Character> letters = new HashSet<>();
        letters.add('a');
        //printSet(letters); 编译报错
    }


    class Baseclass {
    }

    class SubClass1 extends Baseclass {
    }

    class SubClass2 extends SubClass1 {
    }


    /**
     * 上界通配符：只读不写
     * @param list
     */
    public void printList(ArrayList<? extends SubClass1> list) {
        for (SubClass1 subClass1 : list) {
            System.out.println(subClass1);
        }

    }


    /**
     *  上界通配符：只读不写
     *  因为上界通配符不能确定list的真正泛型类型，所以下面会编译报错，比如list可能是SubClass2类型，但是添加了SubClass1类型元素就会报错;
     *  本质原因是上界通配符? extends Type不能确定泛型类型的最小范围或者最小值
     *  同样，无界通配符？也不能确定泛型类型的最小范围或者最小值
     * @param list
     */
    public void addList2(ArrayList<? extends SubClass1> list) {
        //list.add(new SubClass1());
    }


    @Test
    public void test2() {

        ArrayList<Baseclass> list = new ArrayList<>();
        list.add(new Baseclass());
        list.add(new Baseclass());

        ArrayList<SubClass1> list1 = new ArrayList<>();
        list1.add(new SubClass1());
        list1.add(new SubClass1());

        ArrayList<SubClass2> list2 = new ArrayList<>();
        list2.add(new SubClass2());
        list2.add(new SubClass2());

        //addList(list); //添加的列表范围超过上界
        printList(list1);
        printList(list2);
    }


}

