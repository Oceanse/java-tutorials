package com.demo.lambda.lambda_intro.demo3;

import com.demo.collection_map.model.comparable_model.Students;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaDemo {


    /**
     * 类型推断：参数类型可以自动推断而可以省略不写，
     * 其实String[] names={"ocean"}等价于String[] names=new String[]{"ocean"}也是类型推断
     * ArrayList<String> ls=new ArrayList<>也是类型推断
     *
     * 语法简写：
     * 1 若lambda体只有一条语句，那么花括号,return,分号可以同时省略
     * 2 只有一个参数的时候小括号可以省略
     */
    @Test
    public void testComparator() {
        //lambda表达式的参数列表中的数据类型可以省略不写，JVM编译器可以自动根据上下文推断出参数类型，即类型推断
        Comparator<Integer> comparator = (x, y) -> {
            return Integer.compare(x, y);
        };

        //若lambda体只有一条语句，那么花括号,return,分号可以同时省略
        Comparator<Integer> comparator2 = (x, y) ->
                Integer.compare(x, y);
    }


    @Test
    public void testComparator2() {
        Comparator<Integer> comparator = Integer::compare;
    }


    @Test
    public void testSort() {
        List<Students> hs = new ArrayList<>();
        hs.add(new Students("李四", 14));
        hs.add(new Students("张三", 13));
        hs.add(new Students("王五", 15));
        System.out.println(hs);
        Collections.sort(hs, (o1, o2) -> o1.getAge() - o2.getAge());
        //等价于： Collections.sort(hs, (o1,  o2)->{return o1.getAge() - o2.getAge();});
        System.out.println(hs);
    }

    @Test
    public void testSort2() {
        List<Students> hs = new ArrayList<>();
        hs.add(new Students("李四", 14));
        hs.add(new Students("张三", 13));
        hs.add(new Students("王五", 15));
        System.out.println(hs);
        Collections.sort(hs, (o1, o2) -> Integer.compare(o1.getAge(), o2.getAge()));
        //等价于： Collections.sort(hs, (o1,  o2)->{return Integer.compare(o1.getAge(), o2.getAge());});
        System.out.println(hs);
    }


    public void testFinal() {
        //lambda表达式内部引用了同一级别的局部变量的时候，该局部变量可以不用显式的声明final,但是其本质仍然是final变量
        int ticket = 1;
        Runnable task = () -> {
            System.out.println(ticket);
        };
    }

    public void testFinal2() {
        //lambda表达式内部引用了同一级别的局部变量的时候，该局部变量可以不用显式的声明final,但是其本质仍然是final变量
        int ticket = 1;//这里等价于final int ticket = 1;
        Runnable task = () -> {
            //System.out.println(ticket++);//编译报错
        };
    }
}
