package com.demo.generic.generic_wildcard.super_wildcard;

import org.testng.annotations.Test;

import java.util.*;

public class SuperWildCard2 {

    /**
     *  假设自己实现一个工具方法：实现将src集合中的元素复制到dest集合的功能， 因为dest集合可以
     *  保存src集合中的所有元素， 所以dest集合元素的类型应该是src集合元素类型或者其父类。
     *  public static <T> T copy(Collection<T> src,Collection<? super T> dest)
     *  *
     * @param src
     * @param dest
     * @param <E>
     */
    public <E> void copyCollection(Collection<E> src, Collection<? super E> dest){
        for (E e : src) {
            dest.add(e);
        }
    }


    @Test
    public void testCopyCollection(){
        List<Integer> src= Arrays.asList(1,2,3);
        List<Number> dest=new ArrayList<>();
        copyCollection(src,dest);
        System.out.println(dest);
        dest.clear();

        List<Double> src2= Arrays.asList(1.0,2.0,3.0);
        copyCollection(src2,dest);
        System.out.println(dest);
    }


    /**
     * public static <T> void sort(List<T> list, Comparator<? super T> c)
     */
    @Test
    public void testSort(){
        List<Double> list= Arrays.asList(1.0,2.0,3.0);
        Collections.sort(list,(d1,d2)->Double.compare(d1,d2));
    }

}
