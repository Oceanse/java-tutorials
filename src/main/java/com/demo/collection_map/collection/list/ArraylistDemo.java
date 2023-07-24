package com.demo.collection_map.collection.list;

import com.demo.collection_map.model.*;
import com.demo.others.pojo.Resource;
import com.google.common.collect.Lists;
import org.testng.annotations.Test;

import java.util.*;


/**
 * https://juejin.cn/post/6844903582194466824
 * ArrayList底层存储结构是数组,容量大小可调整； 可以进行快速的随机访问，插入与删除元素的速度相对较慢。
 * 集合只能存储引用(对象的内存地址)，不能存储基本数据类型，也不能存储对象，如果添加的是基本类型数据则会自动装箱
 * 有序可以重复
 * @author epanhai
 */
public class ArraylistDemo {


    /**
     * List的构造
     */
    @Test
    public void testConstructArrayList() {

        //通过可变参数构造List, Returns a fixed-size list；Arrays.asList()是个坑，用此方法得到的List的长度是不可改变的，
        //asList()方法实现： 返回的ArrayList不是java.util包下的，而是java.util.Arrays.ArrayList；它是Arrays类自己定义的一个静态内部类，这个内部类没有实现add()、remove()方法
        //如果你的List只是用来遍历，就用Arrays.asList()。如果还要添加或删除元素，还是乖乖地new一个java.util.ArrayList，然后一个一个的添加元素。
        List list = Arrays.asList(1, 2, 3);
        System.out.println(list);
        //list.add(1); //运行会报错

        //通过数组构造List, Returns a fixed-size list
        List list1 = Arrays.asList(new int[]{1, 2, 3});
        //list1.add(1); //运行会报错


        ArrayList list2 = new ArrayList();//底层会创建长度为0的数组，jdk7时10
        list2.add(1);
        list2.add(2f);
        list2.add(3.0);
        list2.add('a');
        list2.add("ocean");
        list2.add(new Resource());
        System.out.println(list2);

        //通过List构造list
        List list3 = new ArrayList(list2);
        System.out.println(list3);

        //通过Set构造list
        Set set = new HashSet();
        set.addAll(list3);
        List list4 = new ArrayList(set);
        System.out.println(list4);


        /*
        <dependency>
         <groupId>com.google.guava</groupId>
         <artifactId>guava</artifactId>
         <version>25.1-jre</version>
         </dependency>
         */
        List list5 = Arrays.asList(1, 2, 3);
        ArrayList list6 = Lists.newArrayList(list5);
        System.out.println(list5 + "\n" + list6);

    }


    /**
     * 添加元素：public boolean add(E e)
     * 元素都一次添加在list的尾部，此时插入效率较高
     */
    @Test
    public void test1() {
        ArrayList<Object> container = new ArrayList();

        //集合只能存储引用(对象的内存地址)，不能存储基本数据类型，也不能存储对象，如果添加的是基本类型数据则会自动装箱，
        container.add(1);
        container.add(2.0f);
        container.add(3.0);
        container.add('@');
        container.add(true);
        container.add("abc");
        container.add(new Resource());
        container.add(new com.demo.IO.net_io.jetty.model.User());
        System.out.println(container);//Collection集合重写了toString方法,相当于依次打印每个对象的toString
    }


    /**
     * 添加元素：public void add(int index, E element)
     * 会把所有元素往后移动一位，所以ArrayList首位或者中间部分插入数据效率较低
     */
    @Test
    public void test1_2() {
        ArrayList<Object> container = new ArrayList();
        container.add(1);
        container.add(2.0f);
        container.add(3.0);
        System.out.println(container);
        container.add(0, "first");
        container.add(1, "second");
        System.out.println(container);
    }

    /**
     * 添加元素：addAll(Collection<? extends E> c)
     */
    @Test
    public void test1_3() {
        ArrayList<Number> container = new ArrayList();
        container.add(1);
        container.add(2.0f);
        container.add(3.0);

        HashSet<Double> container2 = new HashSet();
        container2.add(1.0);
        container2.add(2.0);
        container2.add(3.0);

        ArrayList<Number> container3 = new ArrayList();
        container3.addAll(container);
        container3.addAll(container2);
        System.out.println("container3 = " + container3);
    }


    /**
     * boolean addAll(int index, Collection<? extends E> c)
     */
    @Test
    public void test1_4() {
        ArrayList<Object> container = new ArrayList();
        container.add(1);
        container.add(2.0f);
        container.add(3.0);

        ArrayList<String> container2 = new ArrayList();
        container2.add("one");
        container2.add("two");
        container.addAll(1,container2);
        System.out.println(container2);
    }


    /**
     * 删除元素：
     * 按索引删除：public E remove(int index)
     * 会把所有元素往前移动一位(删除最后一位时候不需要移动元素)
     */
    @Test
    public void test2() {
        ArrayList<Object> container = new ArrayList();
        container.add("first");
        container.add('@');
        container.add(true);
        container.add("last");
        System.out.println(container);

        //删除第一个元素
        Object removeFirstObj = container.remove(0);
        System.out.println(removeFirstObj);
        System.out.println(container);

        //删除最后一个元素
        Object removeLastObj = container.remove(container.size() - 1);
        System.out.println(removeLastObj);
        System.out.println(container);
    }


    /**
     * 删除元素：
     * 按对象删除：public boolean remove(Object o)， Removes the first occurrence of the specified element from this list
     * 会把所有元素往前移动一位(删除最后一位时候不需要移动元素)
     */
    @Test
    public void test2_2() {
        ArrayList<Object> container = new ArrayList();
        container.add("first");
        container.add('@');
        container.add(true);
        container.add(true);
        container.add("first");
        container.add("last");
        System.out.println(container);

        boolean isRemove = container.remove("first");
        System.out.println("是否删除："+isRemove);
        System.out.println(container);

        boolean isRemove2 = container.remove("lastone");
        System.out.println("是否删除："+isRemove2);
        System.out.println(container);
    }

    /**
     * 删除元素：
     * 按集合删除：public boolean removeAll(Collection<?> c)
     */
    @Test
    public void test2_3() {
        ArrayList<Object> container = new ArrayList();
        container.add("first");
        container.add('@');
        container.add(true);
        container.add(true);
        container.add("last");
        System.out.println(container);

        Collection c = new ArrayList();
        c.add(true);
        c.add("second");
        c.add('@');
        container.removeAll(c);//两个true都会被删除
        System.out.println(container);
    }


    /**
     * 删除全部元素：public void clear()
     */
    @Test
    public void test2_4() {
        ArrayList<Object> container = new ArrayList();
        container.add("first");
        container.add('@');
        container.add(true);
        container.add(true);
        container.add("last");
        System.out.println(container);

        container.clear();
        System.out.println(container);
        System.out.println(container.size());
        System.out.println(container.isEmpty());
    }


    /**
     * remove通过object删除元素时候，底层用的是equals
     */
    @Test
    public void test2_5() {
        ArrayList<String> container = new ArrayList();
        container.add(new String("first"));
        System.out.println(container);
        boolean isRemoved = container.remove(new String("first"));//String底层重写了equals,所以这里会删除成功
        System.out.println(isRemoved);
        System.out.println(container);
    }



    /**
     * remove通过object删除元素时候，底层用的是equals
     */
    @Test
    public void test2_6() {
        ArrayList<Apple> container = new ArrayList();
        container.add(new Apple());
        System.out.println(container);
        boolean isRemoved = container.remove(new Apple());//Apple底层没有重写equals,因此容器里不会包含传入的new Apple(),所以这里会删除失败
        System.out.println(isRemoved);
        System.out.println(container);
    }


    /**
     * 更新/修改元素：public E set(int index, E element)
     */
    @Test
    public void test3() {
        ArrayList<Product> container = new ArrayList();
        Product pd1 = new Product(1, "木糖醇", 10);
        Product pd2 = new Product(2, "洗发水", 12);
        Product pd3 = new Product(3, "热水壶", 49);
        Product pd4 = new Product(4, "电脑", 3000);
        container.add(pd1);
        container.add(pd2);
        container.add(pd3);
        System.out.println(container);
        container.set(1, pd4);//修改第二个元素
        System.out.println(container);
    }


    /**
     * public boolean retainAll(Collection<?> c)
     * <p>
     * A.retainAll(B):
     * 改变了集合A中的元素，将存在于集合A中但不存在于集合B中的元素移除,也就是集合A中仅保留交集元素
     * 集合B中的元素保持不变
     * 如果集合A的大小发生了改变，返回true，、
     * 如果集合A的大小没有发生改变，返回false
     */
    @Test
    public void test3_2() {
        ArrayList<Object> container = new ArrayList();
        container.add("first");
        container.add(true);
        container.add("last");
        ArrayList<Object> container2 = new ArrayList();
        container2.add("first");
        container2.add(false);
        container2.add("last");
        boolean b = container.retainAll(container2);
        System.out.println(b);
        System.out.println(container);
        System.out.println(container2);


        System.out.println();
        ArrayList<Object> container3 = new ArrayList();
        container3.add(1);
        container3.add(2);
        ArrayList<Object> container4 = new ArrayList();
        container4.add(3);
        container4.add(4);
        boolean b2 = container3.retainAll(container4);
        System.out.println(b2);
        System.out.println(container3);
        System.out.println(container4);
    }


    /**
     *
     * subList(int fromIndex, int toIndex): 截取 List 集合中部分元素时,新的集合中包含起始索引位置的元素，但是不包含结束索引位置的元素。
     * [fromIndex,toIndex)
     */
    @Test
    public void test3_3(){
        List list = new ArrayList();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");
        // 从list集合中截取索引1~2的元素，保存到sublist集合中
        List sublist = list.subList(1, 3);
        System.out.println(sublist);
    }


    /**
     * 查询
     */
    @Test
    public void test4() {
        ArrayList<Object> container = new ArrayList();
        container.add("first");
        container.add('@');
        container.add(true);
        container.add('@');
        container.add("last");
        System.out.println("c.size:" + container.size());
        System.out.println("c.contains('@'):" + container.contains('@'));//底层调用equals方法
        System.out.println("fisrt elem:" + container.get(0));
        System.out.println("last elem:" + container.get(container.size() - 1));
        System.out.println("@ of firstIndex:" + container.indexOf('@'));//返回指定元素首次出现的索引
        System.out.println("@ of firstIndex:" + container.lastIndexOf('@'));//返回指定元素首次出现的索引
        System.out.println("c2 clear:" + container.isEmpty());
    }


    /**
     * 通过分析contains源码可知，此方法会把传入的元素依次和集合中的每个元素进行equals比较
     * 像String重写了equals方法，那么依次比较的就是内容
     * 若没有重写，那么依次比较的就是引用地址
     */
    @Test
    public void test4_2() {
        ArrayList<Object> container = new ArrayList();

        //String重写了equals方法
        String s = new String("first");
        container.add(s);
        String s2 = new String("first");
        System.out.println(container.contains(s2));

        //Resource重写了equals方法
        Resource t1 = new Resource();
        container.add(t1);
        Resource t2 = new Resource();
        System.out.println(container.contains(t2));

        //Apple没有重写equals方法
        Apple apple = new Apple();
        container.add(apple);
        Apple apple2 = new Apple();
        System.out.println(container.contains(apple2));

    }


    /**
     * public Object clone()
     */
    @Test
    public void test5() {
        ArrayList<Number> container = new ArrayList();
        container.add(0, 1);
        container.add(1, 2.1);
        container.add(2, 3.1f);
        System.out.println(container);

        Object clone = container.clone();
        ArrayList container2 = (ArrayList) clone;
        System.out.println(container2);
        System.out.println(container == container2);//false
    }


    /**
     * 遍历
     */
    @Test
    public void test6() {
        ArrayList<String> ls = new ArrayList();
        ls.add("Aaa");
        ls.add("Bbb");
        ls.add("Ccc");

        //重写toString
        System.out.println(ls);

        //fori循环
        System.out.println();
        for (int i = 0; i < ls.size(); i++) {
            System.out.print(ls.get(i).toUpperCase() + " ");
        }
        System.out.println();


        //增强for循环,底层实际上使用的是iterator迭代器进行遍历
        System.out.println();
        for (String str : ls) {//系统会把迭代元素的值
            System.out.print(str.toLowerCase() + " ");
        }
        System.out.println();

        //Iterator必须依附于Collection对象，也就是若有一个Iterator对象，必然有一个与之对应的Collection对象；
        //public interface Collection<E> extends Iterable<E>， 所以所有Collection对象都是可迭代的(可遍历的)
        //Iterator就是用来遍历迭代集合的
        System.out.println();
        Iterator<String> it = ls.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println(it.hasNext());//false
        //System.out.println(it.next()); //NoSuchElementException
        //如果想要再次迭代遍历，需要重置迭代器，也就是重新获得新的迭代器 it = ls.iterator();




        //lambda遍历
        System.out.println();
        ls.forEach(ele -> System.out.print(ele + " "));

        //::可以把参数都省掉
        System.out.println();
        ls.forEach(System.out::print);
    }


    /**
     * 使用 Iterator 迭代器遍历集合的过程中不能添加、删除或修改元素
     * 但是使用迭代器的删除方法(remove()) 删除是正确可行的，也是开发中推荐使用的。
     */
    @Test
    public void test6_2() {
        ArrayList<String> ls = new ArrayList();
        ls.add("Aaa");
        ls.add("Bbb");
        ls.add("Ccc");

        Iterator it = ls.iterator();
        while (it.hasNext()) {
            String item = (String) it.next();
            if (item.equals("Aaa")) {
                it.remove();//List集合的遍历删除建议使用迭代器遍历删除操作。
            }
        }
        System.out.print(ls);
    }


    /**
     * 遍历集合的过程中不能添加、删除或修改元素，
     * 但是使用迭代器的删除方法(remove()) 删除是正确可行的
     */
    @Test
    public void test6_3() {
        ArrayList<String> ls = new ArrayList();
        ls.add("Aaa");
        ls.add("Bbb");
        ls.add("Ccc");

        Iterator<String> it = ls.iterator();
        while (it.hasNext()) {
            String item =  it.next();
            if (item.equals("Aaa")) {
                ls.remove(item);//使用迭代器遍历，却使用集合的方法删除元素: ConcurrentModificationException
            }
        }
        System.out.print(ls + " ");
    }


    /**
     * 遍历集合的过程中不能添加、删除或修改元素，
     */
    @Test
    public void test6_4() {
        ArrayList<String> ls = new ArrayList();
        ls.add("Aaa");
        ls.add("Bbb");
        ls.add("Ccc");

        Iterator<String> it = ls.iterator();
        while (it.hasNext()) {
            String item = it.next();
            if (item.equals("Ccc")) {
                //使用 Iterator 迭代器遍历集合的过程中不能添加、删除或修改元素，
                ls.add("Ddd");
            }
        }
        System.out.print(ls + " ");
    }

    /**
     * 使用 Iterator 迭代器遍历集合的过程中不能添加、删除或修改元素，
     * 运行通过？
     */
    @Test
    public void test6_5() {
        ArrayList<String> ls = new ArrayList();
        ls.add("Aaa");
        ls.add("Bbb");
        ls.add("Ccc");

        Iterator<String> it = ls.iterator();
        while (it.hasNext()) {
            ls.set(1, "aaa");
            System.out.println(it.next());
        }
        System.out.print(ls);
    }

    /**
     * 泛型(about generic)
     */
    @Test
    public void test7() {

        //泛型(about generic)
        ArrayList<Fruit> fruit = new ArrayList<>();
        fruit.add(new Apple());
        fruit.add(new Grape());
        fruit.add(new Orange());
        System.out.println(fruit);

        //List--->array， 无论是否使用泛型都会返回Object[],因为运行时会进行泛型擦除，所以必须用Object数组接收
        Object[] objects1 = fruit.toArray();
        Fruit[] object2 = (Fruit[]) fruit.toArray();
    }

}
