package com.demo.lambda.function_interface;

import com.demo.collection_map.model.comparable_model.Students;
import com.demo.collection_map.model.comparable_model.Students2;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


/**
 *
 * java内置四大函数式接口，都是泛型接口
 *
 * Consumer<T> :消费型接口
 * 			void accept(T t);
 *
 * Supplier<T> :供给型接口
 * 			T get();
 *
 * Function<T,R> :函数型接口
 * 			R apply(T t);
 *
 * Predicate<T> :断言型接口
 * 			boolean test(T t);
 *
 */
public class FunctionInterface {
    /**
     * 消费型接口Consumer<T>
     */
    @Test
    public void testConsumer() {
        //Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
        //这个过程可以想象成包含了创建了实现类及其对象
        Consumer<String> consumer = (str) -> {
            System.out.println(str.toUpperCase());
        };

        //Consumer对象调用自己的accept()方法
        consumer.accept("millionaire");
    }

    /**
     * 消费型接口interface Consumer<T> {void accept(T t);}
     * 面向Consumer接口编程，方法的具体功能取决于接口的具体实现，也就是接口的实现类
     * 有点像函数式编程，把参数"money" 传给函数"consumer"去处理
     *
     * @param money
     * @param consumer
     */
    public void consumer(Double money, Consumer<Double> consumer) {
        //面向接口调用
        consumer.accept(money);
    }

    @Test
    public void testConsumer2() {
        consumer(1000.0, item -> {
            item = item - 100;
            System.out.println("消费100元，还剩" + item + "元");
        });
    }


    /**
     * 供给型接口Supplier<T>
     * T get()
     */
    @Test
    public void testSupplier() {
        //这个过程可以想象成包含了创建了实现类及其对象
        Supplier<Students> supplier = () -> {
            return new Students();
        };

        //若lambda体只有一条return语句，那么花括号和return可以同时省略
        //这个过程可以想象成包含了创建了实现类及其对象
        Supplier<Students2> supplier2 = () -> new Students2();

        //Supplier对象调用自己的get()方法
        Students students = supplier.get();
        Students2 students2 = supplier2.get();
    }

    /**
     * 面向Supplier接口编程，方法的具体功能取决于接口的具体实现，也就是接口的实现类
     *有点像函数式编程
     * @param count
     * @param supplier
     * @return
     */
    public List<Integer> getNumList(int count, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }


    /**
     * 供给型接口Supplier<T><T>
     * T get()
     */
    @Test
    public void testSupplier2() {
        List<Integer> numList = getNumList(10, () -> {
            return (int) (Math.random() * 100);
        });
        //若lambda体只有一条return 语句，那么花括号和return可以同时省略
        List<Integer> numList2 = getNumList(10, () ->
                (int) (Math.random() * 100)
        );

        System.out.println(numList);
        System.out.println(numList2);
    }


    /**
     * 函数型接口Function<T, R>, T是参数类型，R是返回值类型
     * R apply(T t)
     */
    @Test
    public void testFunction() {
        //若lambda体只有一条return 语句，那么花括号和return可以同时省略
        //这个过程可以想象成包含了创建了实现类及其对象
        Function<String, Integer> function = str -> str.length();
        Integer rich = function.apply("rich");
        System.out.println(rich);

    }

    /**
     * 面向Function接口编程，方法的具体功能取决于接口的具体实现，也就是接口的实现类
     *有点像函数式编程，把参数"money" 传给函数"consumer"去处理
     * @return
     */
    public int getStrLength(String str, Function<String, Integer> function) {
        return function.apply(str);
    }

    /**
     * 函数型接口Function<T, R>
     * R apply(T t)
     */
    public void testFunction2() {
        int len1 = getStrLength("me", str -> {
            return str.length();
        });
        //若lambda体只有一条return 语句，那么花括号和return可以同时省略
        int len2 = getStrLength("change", str -> str.length());
        System.out.println(len1);
        System.out.println(len2);
    }


    /**
     * 面向Function接口编程，方法的具体功能取决于接口的具体实现，也就是接口的实现类
     *有点像函数式编程，把参数"money" 传给函数"consumer"去处理
     * @return
     */
    public String myTrim(String str, Function<String, String> function) {
        return function.apply(str);
    }

    /**
     * 函数型接口Function<T, R>
     * R apply(T t)
     */
    @Test
    public void testFunction3() {
        String s1 = myTrim("\t\tocean\t\t", str -> str.trim());
        String s2 = myTrim("\t\tocean\t\t", str -> {
            return str.trim();
        });
        System.out.println(s1);
        System.out.println(s2);
    }


    /**
     * 断言型接口Predicate<T>
     * boolean test(T t), 也就是判断这个参数，返回布尔值
     */
    public void testPredicate() {
        Predicate<Map> predicate = map -> {
            return map.isEmpty();
        };
        //若lambda体只有一条return语句，那么花括号和return可以同时省略
        Predicate<Map> predicate2 = map -> map.isEmpty();
    }

    /**
     * 面向Function接口编程，方法的具体功能取决于接口的具体实现，也就是接口的实现类
     *
     * @return
     */
    public List<String> filterString(List<String> ls, Predicate<String> predicate) {
        List<String> list = new ArrayList<>();
        for (String item : ls) {
            //把符合指定条件的元素加入集合
            if (predicate.test(item)) {
                list.add(item);
            }
        }
        return list;
    }

    /**
     * 断言型接口Predicate<T>
     * boolean test(T t)
     */
    @Test
    public void testPredicate2() {
        List<String> list = Arrays.asList("java", "python");
        List<String> list1 = filterString(list, str -> {
            return str.startsWith("p");
        });
        //若lambda体只有一条return语句，那么花括号和return可以同时省略
        List<String> list2 = filterString(list, str -> str.startsWith("j"));
        System.out.println(list1);
        System.out.println(list2);
    }
}
