package com.demo.generic.demo6.extends_wildcard;

import io.cucumber.java.bs.A;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 上界通配符： ? extends 类型实参上界， 限制未知类型实参的上界,要求该传入的类型实参只能是上界或者其子类
 * <p>
 * 当引入了上界之后，在使用类型的时候就可以使用上界类中定义的方法。比如访问 List<? extends Number> 的时候，
 * 就可以使用 Number 类的 intValue 等方法
 */
public class ExtendsWildcard {

    public static void printSet(Set<? extends Number> numbers) {
        //numbers集合中的每个元素类型都是Number类型或者其子类类型
        for (Number number : numbers) {
            System.out.print(number + " ");
        }
    }

    @Test
    public static void test() {
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


    public void addList(ArrayList<? extends SubClass1> list) {
        for (SubClass1 subClass1 : list) {
            System.out.println(subClass1);
        }

        //因为上界通配符不能确定list的真正泛型类型，所以下面会编译报错，比如list可能是SubClass2类型，但是添加了SubClass1对象就会报错;
        //本质原因是不知道泛型类型的最小范围或者最小值
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
        addList(list1);
        addList(list2);
    }


}

