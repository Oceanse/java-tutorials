package com.demo.collection_map.model.comparator_model;

import java.util.Comparator;

/**
 * 定制排序：构造一个Students4类的“比较器"。
 * 用Comparator 的好处是不需要修改Students4源代码， 而是实现一个Comparator比较器
 * 总结一下，两种比较器Comparable和Comparator，后者相比前者有如下优点：
 * 1、如果实现类没有实现Comparable接口或者实现类实现了Comparable接口，但是对compareTo方法内的比较算法不满意
 *    那么可以实现Comparator接口，自定义一个比较器，写比较算法
 * 2、实现Comparable接口的方式比实现Comparator接口的耦合性 要强一些，如果要修改比较算法，
 *  要修改Comparable接口的实现类，也就是model类的源码，而实现Comparator的类是在外部进行比较的，
 *  通过外部创建一个比较器实现比较，不需要对model类有任何修改，符合对扩展开放，对修改关闭的原则
 *  开发者还是要在具体场景下选择最合适的那种比较器而已。
 *
 *  Comparator使用：
 *  Arrays.sort(Students4[],new Students4Comparator());
 *  Collections.sort(HashSet<Students4>,new Students4Comparator());
 *
 * @author epanhai
 */
public class Students4Comparator implements Comparator<Students4> {
    @Override
    public int compare(Students4 o1, Students4 o2) {
        int num = o1.getAge() - o2.getAge();
        // String类里面已经重写了compareTo方法
        // int compareTo(String anotherString)  按字典顺序比较两个字符串
        return num == 0 ? o1.getName().compareTo(o2.getName()) : num;
    }
}
