package com.demo.basic.flow_control.loop.fors;

import org.testng.annotations.Test;

import java.util.*;

/**
 * As of Java 5, the enhanced for loop was introduced and mainly used to traverse collection or arrays.
 * 语法：“类型”为集合元素的类型，“变量名”表示集合中的每一个元素，“集合”是被遍历的集合对象或数组
 * for(类型 变量名:集合) {
 * 语句块;
 * }
 * 特点：方便简单，不能修改数组或者集合的元素的值
 *
 * @author epanhai
 */
public class ForeachDemo {

    /**
     * for-each traverse array
     * 无须获得数组长度，也无须根据索引来访问数组元素,for-each 语句在遍历集合的时候要简单方便得多。
     */
    @Test
    public void traverseArray() {
        int[] numbers = {10, 20, 30, 40, 50};
        //n保存了集合中的元素，for-each 语句将集合中的元素一一取出来，并保存到n中,
        for (int number : numbers) {
            System.out.println(number);
        }

    }


    /**
     * for-each traverse List & Set
     */
    @Test
    public void traverseListAndSet() {

        List<String> fruits = Arrays.asList("apple", "grape", "orange", "lemon");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        Set<String> books = new HashSet<>();
        books.add("Thinking in java");
        books.add("Linux");
        books.add("Spring cloud");
        for (String book : books) {
            System.out.println(book);
        }
    }

    /**
     * for-each traverse map
     */
    @Test
    public void traverseMap() {
        Map<String, Integer> personInfo = new HashMap<>();
        personInfo.put("ocean", 28);
        personInfo.put("god", 99);

        Set<String> names = personInfo.keySet();
        for (String name : names) {
            System.out.println("name: " + name + ", age: " + personInfo.get(name));
        }
    }




    /**
     * 看了一下 iterator 的源码，发现迭代器在调用next方法时，会检查列表是否被修改过，如果被修改过，就会抛出ConcurrentModificationException异常。
     * 因为Iterator 是工作在一个独立的线程中，并且拥有一个 mutex 锁。因此 Iterator 在工作的时候是不允许被迭代的对象被改变的，所以不能在使用 iterator
     * 进行遍历的同时 list 移除这个元素。因此，可以使用 iterator 的remove 方法。
     */
    @Test
    public void traverseAndDelete(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        for (Integer e : list) {
            System.out.println(e);
            //不要循环遍历容器元素，然后删除特定元素。正确姿势应该是遍历容器的迭代器（Iterator），删除元素。
            list.remove(e);
        }
    }


    /**
     * 看了一下 iterator 的源码，发现迭代器在调用next方法时，会检查列表是否被修改过，如果被修改过，就会抛出ConcurrentModificationException异常。
     * 因为Iterator 是工作在一个独立的线程中，并且拥有一个 mutex 锁。因此 Iterator 在工作的时候是不允许被迭代的对象被改变的，所以不能在使用 iterator
     * 进行遍历的同时 list 移除这个元素。因此，可以使用 iterator 的remove 方法。
     */
    @Test
    public void iteratorAndDelete(){
        List list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);

        Iterator<Integer> iterator = list.iterator();
        int target=1;
        while(iterator.hasNext()){
            int result= iterator.next();
            if(result==target){
                //迭代器删除
                iterator.remove();
            }
        }

        System.out.println(list);
    }

    /**
     * for(类型 变量名:集合)
     * 当使川foreach 来迭代访问数组元素时， foreach 中的循环变量是一个临时变量
     * 这个临时变量可以理解为集合元素的副本，通过变量名操作元素不会影响原集合中元素的变量值(无论是字面值或者引用值)
     * 如果集合元素是基本类型，那么通过变量名操作元素不会影响原集合中元素的变量值
     */
    @Test
    public void testForeach() {
        double[] scores = new double[]{100.0, 120.0, 130};

        //foreach遍历
        for (double score : scores) {
            System.out.println(score);
        }
        System.out.println();


        //foreach修改
        //相当于把每个数组元素的字面值的副本传递给新的变量score
        for (double score : scores) {
            score = 2 * score;
        }

        //foreach遍历,scores数组元素本身没有改变
        for (double score : scores) {
            System.out.println(score);
        }
    }


    /**
     * for(类型 变量名:集合)
     * 当使川foreach 来迭代访问数组元素时， foreach 中的循环变量是一个临时变量
     * 这个临时变量可以理解为集合元素的副本，通过变量名操作元素不会影响原集合中元素的变量值(无论是字面值或者引用值)
     * 但是如果集合元素是引用类型，也就是集合元素保存的是对象的地址，那么通过临时变量名操作元素虽然不能影响指向的对象，但是可以影响指向对象的内容
     * <p>
     * <p>
     * ForMode2l没有重写toString方法，所以可以看出foreach不会改变集合元素的值
     */
    @Test
    public void testForeach2() {
        ForModel p1 = new ForModel("ocean");
        ForModel p2 = new ForModel("phy");

        //foreach遍历,  personList存放的是对象的内存地址
        List<ForModel> list = Arrays.asList(p1, p2);
        for (ForModel forModel : list) {
            System.out.println(forModel);
        }
        System.out.println();

        //foreach修改
        for (ForModel item : list) {
            //这里不会改变原List中的元素变量值(对象地址)，但是这里的item也会指向原列表元素所指向的对象，
            // 相当于一个对象又多了一个句柄，所以这里原列表元素所指向的对象的内容会发生变化
            item.setName("PanhaiYang");
        }

        //foreach遍历,  personList存放的是对象的内存地址
        for (ForModel forModel : list) {
            System.out.println(forModel);
        }
    }


    /**
     * 通过foreach遍历/迭代数组元素(不需要索引)
     * 不会修改数组或者集合的元素的值，这里的话就是数组元素永远指向常量池中固定的对象，
     * 又因为String是不可变类，所以数组元素永远指向常量池中固定的对象，且这个对象的内容也不会被改变
     * 总结：使用foreach 循环迭代数组元素时， 并不能改变数组元素的值
     */
    @Test
    public void testForeach3() {
        //这里fruits中的数组元素变量保存的实际上是常量池中字符串的地址
        String[] fruits = new String[]{"apple", "orange", "banana"};
        //相当于把每个数组元素的值传递给给新的变量fruit，这里数组元素的值个人理解是常量池中字符串的地址，
        //也就是把常量池中字符串的地址赋值给了新的变量fruit，临时变量fruit和数组元素变量两个句柄指向常量池中的同一个字符串对象
        for (String fruit : fruits) {
            //虽然新的变量fruit和数组元素变量两个句柄指向常量池中的同一个字符串，但是String是不可变类，也就是常量池中的字符串内容不可改变
            //所以当其中一个句柄试图修改字符串内容时候，本质是会创建一个新的字符串，但是句柄会仍然指向修改之前的字符串
            //所以这里fruit.toUpperCase()之后，fruit和数组元素还是指向原先的常量池中的字符串对象，因此原数组元素的值不会变
            fruit.toUpperCase();
            System.out.print(fruit+" ");
        }
        //fruits数组元素本身没有改变
        System.out.println();
        for (String fruit : fruits) {
            System.out.print(fruit+" ");
        }
    }


}


class ForModel {
    String name;

    public ForModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ForModel{" +
                "name='" + name + '\'' +
                '}';
    }
}


class ForModel2 {
    String name;

    public ForModel2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
