package com.demo.collection_map.map;

import com.demo.basic.code_block.construct_block.demo1.Person;
import com.demo.collection_map.model.comparable_model.Students;
import com.demo.collection_map.model.comparable_model.Students3;
import org.testng.annotations.Test;

import java.util.*;

public class TreeMapTest {

    /**
     * TreeMap默认按key进行升序排序,也就是自然排序
     * 不指定Comparator, 那么key必须要实现Comparable接口，否则会报cannot be cast to java.lang.Comparable
     * 像String、包装类等实现了Comparable接口，重写了compareTo(obj)方法，给出了比较两个对象大小的方式
     */
    @Test
    public void putByComparableTest1() {
        Map<String, Integer> fruits = new TreeMap<>();
        fruits.put("grape", 1);
        fruits.put("apple", 2);
        fruits.put("banana", 3);
        System.out.println(fruits);
    }


    /**
     * Person没有实现Comparable接口，报cannot be cast to java.lang.Comparable
     */
    @Test
    public void putByComparableTest2() {
        Map<Person, Integer> persons = new TreeMap<>();
        persons.put(new Person("Tracy", 34), 1);
        persons.put(new Person("ashly", 36), 8);
        persons.put(new Person("sherry", 27), 6);
    }


    /**
     * TreeMap默认按key进行升序排序,也就是自然排序
     * 不指定Comparator, 那么key必须要实现Comparable接口，否则会报cannot be cast to java.lang.Comparable
     * 像String、包装类等实现了Comparable接口，重写了compareTo(obj)方法，给出了比较两个对象大小的方式
     */
    @Test
    public void putByComparableTest3() {
        Map<Students3, Integer> students = new TreeMap<>();
        students.put(new Students3("Tracy", 34), 1);
        students.put(new Students3("Ashly", 36), 8);
        students.put(new Students3("Sherry", 27), 6);
        System.out.println(students);
    }


    /**
     * 使用Comparator按key定制排序
     * 先按照姓名升序，姓名相同则按照年龄升序
     * 匿名内部类形式
     */
    @Test
    public void putByComparableTest4() {
        Map<Person, Integer> persons = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int compareResult = o1.getName().compareTo(o2.getName());
                return compareResult == 0 ? Integer.compare(o1.getAge(), o2.getAge()) : compareResult;
            }
        });
        persons.put(new Person("Tracy", 34), 1);
        persons.put(new Person("Ashly", 36), 8);
        persons.put(new Person("Sherry", 29), 22);
        persons.put(new Person("Sherry", 27), 6);
        System.out.println(persons);
    }


    /**
     * 使用Comparator按key定制排序
     * 先按照姓名升序，姓名相同则按照年龄升序
     * lambda表达式形式
     */
    @Test
    public void putByComparableTest5() {
        Map<Person, Integer> persons = new TreeMap<>(
                (Person o1, Person o2) -> {
                    int compareResult = o1.getName().compareTo(o2.getName());
                    return compareResult == 0 ? Integer.compare(o1.getAge(), o2.getAge()) : compareResult;
                });
        persons.put(new Person("Tracy", 34), 1);
        persons.put(new Person("Ashly", 36), 8);
        persons.put(new Person("Sherry", 29), 22);
        persons.put(new Person("Sherry", 27), 6);
        System.out.println(persons);
    }



    /**
     * 使用Comparator按key定制排序
     * 先按照姓名升序，姓名相同则按照年龄升序
     * lambda表达式形式
     */
    @Test
    public void putByComparableTest6() {
        Map<Person, Integer> persons = new TreeMap<>(
                Comparator.comparing(Person::getName).thenComparingInt(Person::getAge));
        persons.put(new Person("Tracy", 34), 1);
        persons.put(new Person("Ashly", 36), 8);
        persons.put(new Person("Sherry", 29), 22);
        persons.put(new Person("Sherry", 27), 6);
        System.out.println(persons);
    }


    /**
     * 使用Comparator按key定制排序：降序排序
     */
    @Test
    public void putByComparableTest7() {
        //匿名内部类形式，按找key降序排序
        Map<String, Integer> fruits = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String obj1, String obj2) {
                return obj2.compareTo(obj1);
            }
        });
        fruits.put("grape", 1);
        fruits.put("apple", 2);
        fruits.put("banana", 3);
        System.out.println(fruits);

        //lambda表达式，按找key降序排序
        Map<String, Integer> fruits2 = new TreeMap<>((str1, str2) -> str2.compareTo(str1));
        fruits2.put("apple", 2);
        fruits2.put("orange", 1);
        fruits2.put("banana", 3);
        System.out.println(fruits2);

        //按找key降序排序
        Map<String, Integer> fruits3 = new TreeMap<>(Comparator.reverseOrder());
        fruits3.put("apple", 2);
        fruits3.put("orange", 1);
        fruits3.put("banana", 3);
        System.out.println(fruits3);

        //按找key升序排序
        Map<String, Integer> fruits4 = new TreeMap<>((str1, str2) -> str1.compareTo(str2));
        fruits4.put("apple", 2);
        fruits4.put("orange", 1);
        fruits4.put("banana", 3);
    }


    /**
     * 自定义按照value排序
     */
    @Test
    public void sortByValue() {
        Map<String, String> map = new TreeMap<>();
        map.put("name", "ocean");
        map.put("city", "weifang");
        map.put("school", "shangda");
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }
        });
        System.out.println(list);
    }


    @Test
    public void traverseTest() {
        Map<String, String> map = new TreeMap<>();
        map.put("name", "ocean");
        map.put("city", "weifang");
        map.put("school", "shangda");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        System.out.println();
        entries.forEach(entry->System.out.println(entry.getKey()+":"+entry.getValue()));
    }


}
