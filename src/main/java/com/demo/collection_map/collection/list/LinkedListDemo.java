package com.demo.collection_map.collection.list;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.LinkedList;


/**
 * LinkedList 源码分析(JDK 1.8): https://www.cnblogs.com/chanshuyi/p/java_collection_linkedlist.html
 * LinkedList存储元素的数据结构是双向链表结构，由存储元素的结点连接而成，每一个节点都包含前一个节点的引用，后一个节点的引用和节点存储的值。
 * 当一个新节点插入时，只需要修改其中保持先后关系的节点的引用即可，所以插删效率较高
 * LinkedList 类随机访问元素的速度则相对较慢
 * <p>
 * <p>
 * 方法名称	说明
 * void addFirst(E e)	将指定元素添加到此集合的开头
 * void addLast(E e)	将指定元素添加到此集合的末尾
 * E getFirst()	返回此集合的第一个元素
 * E getLast()	返回此集合的最后一个元素
 * E removeFirst()	删除此集合中的第一个元素
 * E removeLast()	删除此集合中的最后一个元素
 * @author epanhai
 */
public class LinkedListDemo {


    /**
     * 添加首末元素
     */
    @Test
    public void test() {
        LinkedList ls = new LinkedList();
        ls.add("aa");
        ls.add("bb");
        System.out.println(ls);

        //将指定元素插入此列表的开头。
        ls.addFirst("first");
        //将指定元素添加到此列表的结尾。
        ls.addLast("last");
        System.out.println(ls);

        ls.add(1, "second");
        System.out.println(ls);
    }


    /**
     * 获取首末元素
     */
    @Test
    public void test2() {
        // 创建集合对象
        LinkedList<String> products = new LinkedList<String>();
        String p1 = new String("六角螺母");
        String p2 = new String("10A 电缆线");
        String p3 = new String("5M 卷尺");
        String p4 = new String("4CM 原木方板");

        // 将 p1 对象添加到 LinkedList 集合中
        products.add(p1);
        // 将 p2 对象添加到 LinkedList 集合中
        products.add(p2);
        // 将 p3 对象添加到 LinkedList 集合中
        products.add(p3);
        // 将 p4 对象添加到 LinkedList 集合中
        products.add(p4);

        System.out.println(products.getFirst());
        System.out.println(products.getLast());
    }

    /**
     * 移除首末元素
     */
    @Test
    public void test3() {
        LinkedList ls = new LinkedList();
        ls.add("aa");
        ls.add("bb");
        ls.add("cc");
        ls.add("dd");

        LinkedList ls2 = new LinkedList();
        ls2.addAll(ls);
        System.out.println(ls2);

        ls2.removeFirst();
        ls2.removeLast();
        System.out.println(ls2);


    }


    /**
     * 遍历
     */
    @Test
    public void test4() {

        LinkedList ls = new LinkedList();
        ls.add("aa");
        ls.add("bb");


        for (int i = 0; i < ls.size(); i++) {
            System.out.print(ls.get(i) + " ");
        }

        System.out.println();
        for (Object l : ls) {
            System.out.print(l + " ");
        }

        System.out.println();
        Iterator iterator = ls.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

        System.out.println();
        ls.forEach(item -> System.out.print(item + " "));
    }
}
