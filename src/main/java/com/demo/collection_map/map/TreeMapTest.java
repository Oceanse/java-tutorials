package com.demo.collection_map.map;

import org.testng.annotations.Test;

import java.util.*;

public class TreeMapTest {

    /**
     * TreeMap默认按key进行升序排序
     */
    @Test
    public void sortByKey(){
        Map<String, Integer> fruits= new TreeMap<>();
        fruits.put("grape", 1);
        fruits.put("apple", 2);
        fruits.put("banana", 3);
        System.out.println(fruits);
    }

    /**
     * 自定义按key排序
     */
    @Test
    public void sortByKey2(){
        //匿名内部类形式，按找key降序排序
        Map<String, Integer> fruits= new TreeMap<>(new Comparator<String>() {
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
        Map<String, Integer> fruits2= new TreeMap<>((str1,str2)->str2.compareTo(str1));
        fruits2.put("apple", 2);
        fruits2.put("orange", 1);
        fruits2.put("banana", 3);
        System.out.println(fruits2);

        //按找key降序排序
        Map<String, Integer> fruits3= new TreeMap<>(Comparator.reverseOrder());
        fruits3.put("apple", 2);
        fruits3.put("orange", 1);
        fruits3.put("banana", 3);
        System.out.println(fruits3);

        //按找key升序排序
        Map<String, Integer> fruits4= new TreeMap<>((str1,str2)->str1.compareTo(str2));
        fruits4.put("apple", 2);
        fruits4.put("orange", 1);
        fruits4.put("banana", 3);
        System.out.println(fruits4);
    }


    /**
     * 自定义按照value排序
     */
    @Test
    public void sortByValue(){
        Map<String, String> map = new TreeMap<>();
        map.put("name", "ocean");
        map.put("city", "weifang");
        map.put("school", "shangda");
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String, String>>(){
            @Override
            public int compare(Map.Entry<String, String> mapping1, Map.Entry<String, String> mapping2) {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }
        });
        System.out.println(list);
    }


}
