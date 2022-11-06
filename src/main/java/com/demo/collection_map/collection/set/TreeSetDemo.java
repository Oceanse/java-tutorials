package com.demo.collection_map.collection.set;

import com.demo.collection_map.model.comparable_model.Students;
import com.demo.collection_map.model.comparable_model.Students2;
import com.demo.collection_map.model.comparable_model.Students3;
import com.demo.collection_map.model.comparator_model.Students4;
import com.demo.collection_map.model.comparator_model.Students4Comparator;
import org.testng.annotations.Test;

import java.util.*;

/**
 * TreeSet 类同时实现了 Set 接口和 SortedSet 接口。SortedSet 接口是 Set 接口的子接口，
 * 可以实现对集合进行自然排序，因此使用 TreeSet 类实现的 Set 接口默认情况下是自然排序的，这里的自然排序指的是升序排序。
 * <p>
 * TreeSet 只能对实现了 Comparable 接口的类对象进行排序，因为 Comparable 接口中有一个
 * compareTo(Object o) 方法用于比较两个对象的大小。
 * 例如 a.compareTo(b):
 * 如果 a 和 b 相等，则该方法返回 0；
 * 如果 a 大于 b，则该方法返回大于 0 的值；
 * 如果 a 小于 b，则该方法返回小于 0 的值。
 * <p>
 * <p>
 * 表 1 列举了 JDK 类库中实现 Comparable 接口的类，以及这些类对象的比较方式。
 * 类	                                          比较方式
 * Byte、Double、Float、Integer、Long、hort	    按数字大小比较
 * Character                    	    按字符的 Unicode 值的数字大小比较
 * String	                           按字符串中字符的 Unicode 值的数字大小比较
 *
 * @author epanhai
 */
public class TreeSetDemo {


    /**
     * TreeSet集合不仅可以保证集合元素的唯一性，还可以排序。
     */
    @Test
    public void test() {
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(2);
        ts.add(1);
        ts.add(3);
        ts.add(5);
        ts.add(5);
        ts.add(4);
        System.out.println(ts);

        TreeSet<Character> ts2 = new TreeSet<>();
        ts2.add('b');
        ts2.add('a');
        ts2.add('c');
        System.out.println(ts2);


        TreeSet<String> ts3 = new TreeSet<>();
        ts3.add("dbb");
        ts3.add("caa");
        ts3.add("azz");
        System.out.println(ts3);
    }


    /**
     * 如果TreeSet里面存储不可比较的对象
     * 报错； ava.lang.ClassCastException: Students cannot be cast to java.lang.Comparable
     * 自定义类型(例如Person类,Book类)想要存入TreeSet集合,需要实现Comparable接口,重写该接口的compareTo方法，让自定义对象具备比较性.
     * 这种方式也作为元素的自然排序，也可称为默认排序。
     */
    @Test
    public void testSort() {
        TreeSet<Students> ts = new TreeSet<>();
        ts.add(new Students("张三", 13));
        ts.add(new Students("李四", 14));
        ts.add(new Students("王五", 15));
        System.out.println(ts);
    }


    /**
     * 如果想根据年龄排序,可以让Students类实现Comparable接口的compareTo方法,这样Students对象就成为了可比较对象了。
     * 这种方式也作为元素的自然排序，也可称为默认排序。
     */
    @Test
    public void testSort2() {
        TreeSet<Students2> ts = new TreeSet<>();
        ts.add(new Students2("李四", 14));
        ts.add(new Students2("张三", 13));
        ts.add(new Students2("王五", 15));
        System.out.println(ts);
    }


    /**
     * Students3先按照年龄排序，年龄相同的再按照字符串排序。
     * 这样就可以把四个不同的对象存储进来，
     */
    @Test
    public void testSort3() {
        TreeSet<Students3> ts = new TreeSet<>();
        ts.add(new Students3("李四", 14));
        ts.add(new Students3("张三", 13));
        ts.add(new Students3("王五", 15));
        ts.add(new Students3("赵六", 13));
        System.out.println(ts);
    }


    /**
     * 如果TreeSet里面存储不可比较的对象
     * 报错； ava.lang.ClassCastException: Students cannot be cast to java.lang.Comparable
     * 实现了Comparable接口的类的对象的列表或数组才可以通过Collections.sort或Arrays.sort进行自动排序。
     */
    @Test
    public void testSort4() {
        List hs = new ArrayList<>();
        hs.add(new Students("李四", 14));
        hs.add(new Students("张三", 13));
        hs.add(new Students("王五", 15));
        System.out.println(hs);
        Collections.sort(hs);
    }

    /**
     * 如果TreeSet里面存储不可比较的对象
     * 报错； ava.lang.ClassCastException: Students cannot be cast to java.lang.Comparable
     * 实现了Comparable接口的类的对象的列表或数组才可以通过Collections.sort或Arrays.sort进行自动排序。\
     * TreeSet添加元素的时候没有和Comparator相关的参数，所以虽然Students4有对应的Comparator，但这是一个外部比较器
     * 对TreeSet来说，依然是不可比较对象
     */
    @Test
    public void testSort5() {
        TreeSet<Students4> ts = new TreeSet<>();
        ts.add(new Students4("李四", 14));
        ts.add(new Students4("张三", 13));
        ts.add(new Students4("王五", 15));
        ts.add(new Students4("赵六", 13));
        System.out.println(ts);
    }


    /**
     * 我们可以通过实现Comparator来新建一个比较器，然后通过这个比较器对类进行排序
     */
    @Test
    public void testSort6() {
        List hs = new ArrayList<>();
        hs.add(new Students4("李四", 14));
        hs.add(new Students4("张三", 13));
        hs.add(new Students4("王五", 15));
        System.out.println(hs);
        Collections.sort(hs, new Students4Comparator());
        System.out.println(hs);
    }


    /**
     * 我们可以通过实现Comparator来新建一个比较器，然后通过这个比较器对类进行排序
     * 通过匿名内部类创建Students类的比较器
     */
    @Test
    public void testSort7() {
        List hs = new ArrayList<>();
        hs.add(new Students("李四", 14));
        hs.add(new Students("张三", 13));
        hs.add(new Students("王五", 15));
        System.out.println(hs);
        Collections.sort(hs, new Comparator<Students>() {
            @Override
            public int compare(Students o1, Students o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println(hs);
    }

    /**
     * 我们可以通过实现Comparator来新建一个比较器，然后通过这个比较器对类进行排序
     * 通过lambda创建Students类的比较器
     */
    @Test
    public void testSort8() {
        List hs = new ArrayList<>();
        hs.add(new Students("李四", 14));
        hs.add(new Students("张三", 13));
        hs.add(new Students("王五", 15));
        System.out.println(hs);
        Collections.sort(hs, (Comparator<Students>) (o1, o2) -> o1.getAge() - o2.getAge());
        System.out.println(hs);
    }


}
