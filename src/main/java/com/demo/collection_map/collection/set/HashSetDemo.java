package com.demo.collection_map.collection.set;

import com.demo.pojo.Resource;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * hashcode(哈希码)：系统可以根据哈希码计算出对象在哈希表中的存储位置(哈希表可以简单理解为数组中+链表)
 * equals方法: 当添加元素出现哈希冲突的时候(两个元素的哈希值相同)，需要通过equals进一步判断是否相同
 *
 *
 * Set继承于Collection接口，元素无序且不重复，主要有HashSet和TreeSet两大实现类。
 * HashSet会通过元素的hashcode和equals方法进行判断元素师否重复。
 * 当你试图把对象加入HashSet时，HashSet会使用对象的hashCode来判断对象加入的位置。
 * 同时也会与其他已经加入的对象的hashCode进行比较，如果没有相等的hashCode，HashSet就会假设对象没有重复出现。
 * 简单一句话，如果对象的hashCode值是不同的，那么HashSet会认为对象是不可能相等的。
 * 如果元素(对象)的hashCode值相同,是不是就无法存入HashSet中了? 当然不是,会继续使用equals 进行比较.
 * 如果 equals为true 那么HashSet认为新加入的对象重复了,所以加入失败
 * 如果 equals为false, 那么HashSet认为新加入的是新的对象，那么就会一链表的形式存储在同一个哈希值处的位置
 * 调用原理:先判断hashcode方法的值,如果不相同直接将元素存储到集合,不会调用equals方法；
 *         如果相同再去判断equals，结果为true则插入失败，结果为false则插入成功；
 *
 * 对象在不重写的情况下使用的是Object的equals方法和hashcode方法，从Object类的源码我们知道：
 * 默认的equals 判断的是两个对象的引用指向的是不是同一个对象；
 * 默认的hashcode也是根据对象地址生成一个整数数值， 也就是hashCode方法返回的是该对象的内存地址
 * 所以如果想要让两个内容相同地址不同的Object对象视为相等的，就必须覆盖Object继下来的hashCode方法和equals方法
 *
 * Java重写hashCode()方法有几个原则：
 * 1、如果两个对象使用equals()方法比较，返回true(即相等)，那么两个比较对象返回的hashCode()必须相等。System.out.println(objA.equals(objB));
 * 如果objA.equals(objB)为true，那么objA.hashCode== objB.hashCode()也一定为true。
 * 2、返回hashCode相等的对象，不一定equals()相等，也即是不同对象出现hash值碰撞。注意，一个好的hash算法应该让不同的对象有不同的hash值，这样会提高散列的性能。
 * 一般情况下，如果我们重写了类的equals()方法，最好也重写hashCode()方法，并且equals()方法里用到的成员变量也需要在hashCode()里做计算。
 * 重写hashCode()方法最好是让不同的对象(即使用equals()不等),返回不同的hashCode的值。
 *
 * Set常用方法：
 * A:添加功能
 *     boolean add(E e);
 *     boolean addAll(Collection<? extends E> c);
 *
 * B:删除功能
 *     boolean remove(Object o);
 *     boolean removeAll(Collection<?> c);
 *     void clear();
 *
 *  C:长度功能
 *     int size();
 *
 *  D:判断功能
 *     boolean isEmpty();
 *     boolean contains(Object o);
 *     boolean containsAll(Collection<?> c);
 *     boolean retainAll(Collection<?> c);
 *
 *  E:获取Set集合的迭代器：
 *     Iterator<E> iterator();
 *
 *  F:把集合转换成数组
 *     Object[] toArray();
 *     <T> T[] toArray(T[] a);
 *
 *     //判断元素是否重复，为子类提高重写方法
 *     boolean equals(Object o);
 *     int hashCode();
 *
 *
 *
 *  HashSet：
 *        HashSet实现Set接口，底层由HashMap(后面讲解)来实现，为哈希表结构，新增元素相当于HashMap的key，value默认为一个固定的Object。
 *        当有元素插入的时候，会计算元素的hashCode值，将元素插入到哈希表对应的位置中来；
 *        两个对象的 hashCode 值相等且通过 equals() 方法比较返回结果为 true，则 HashSet 集合认为两个元素相等。
 *        HashSet 不是同步的，如果多个线程同时访问或修改一个 HashSet，则必须通过代码来保证其同步。
 *
 *
 */
public class HashSetDemo {
    @Test
    public void testAdd(){
        //底层存储无序，且不能重复
        HashSet set=new HashSet();
        boolean add = set.add(true);
        boolean add2 = set.add(true);
        set.add(123);
        set.add("set");
        set.add("set");
        set.add('$');
        //允许插入Null值；
        set.add(null);

        //遍历顺序和插入存储顺序不一致
        System.out.println(set);

        //重复元素没有被插入
        System.out.println("add = " + add);
        System.out.println("add2 = " + add2);
        System.out.println(set.size());


    }

    /**
     * Resource重写equals和hashcode方法，所以new Resource()和new Resource()被认为是相同的
     * 如果只是重写equals, 没有重写hashcode,
     * 那么new Resource("r1","/test")和new Resource("r1","/test")的hashcode是不同的，因此
     * 这两个对象都会被加入到set集合中
     */
    @Test
    public void testAdd2(){
        HashSet set=new HashSet();
        set.add(new Resource("r1","/test"));
        set.add(new Resource("r1","/test"));
        System.out.println(set);
    }





    /**
     *  遍历顺序和插入顺序不一致
     */
    @Test
    public void testTraverse(){
        HashSet hs=new HashSet();
        hs.add("世界军事");
        hs.add("兵器知识");
        hs.add("舰船知识");
        hs.add("汉和防务");


        for (Object o : hs) {
            System.out.print(o+" ");
        }


        System.out.println();
        Iterator iterator = hs.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }

        System.out.println();
        hs.forEach(item-> System.out.print(item+" "));
    }


    @Test
    public void testRemove(){
        HashSet<String> names=new HashSet();
        names.add("Haiyang Pan");
        names.add("Xiang Zhu");
        names.add("Maqian Si");
        names.add("WeiBo Pan");
        names.remove("Maqian Si");
        System.out.println(names);

        //批量删除
        names.removeIf(name->name.endsWith("Pan"));
        System.out.println(names);

    }

}
