package com.demo.collection_map.collection.set;

import org.testng.annotations.Test;

import java.util.LinkedHashSet;

/**
 * LinkedHashSet可以维护元素的插入顺序，使得元素按照插入的顺序进行遍历
 * @author epanhai
 */
public class LinkedHashSetDemo {
    /**
     * LinkedHashSet： 指定遍历顺序
     */
    @Test
    public void test(){
        LinkedHashSet set=new LinkedHashSet();
        set.add(true);
        set.add(true);
        set.add(123);
        set.add("set");
        set.add('$');

        for (Object o : set) {
            System.out.print(o+" ");
        }
    }

}
