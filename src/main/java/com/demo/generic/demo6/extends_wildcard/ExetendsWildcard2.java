package com.demo.generic.demo6.extends_wildcard;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *上界通配符：带有上限类型形参的泛型类
 * @author epanhai
 */
    public class ExetendsWildcard2<T extends Number> {//类类型变量(类类型形参)T的上限是Number

    T t;

    public ExetendsWildcard2(){

    }

    public ExetendsWildcard2(T t){
        this.t=t;
    }

    public void setT(T t){
        this.t=t;
    }

    public T getT(){
        return t;
    }


    /**
     * 泛型方法，其中的方法类型形参和类类型形参无关，也就是泛型方法和泛型类是相互独立的
     * 数组复制到list
     */
    public <E> List<E> fromArrayToList(E[] t,List<E> list){
        for(E t1 : t){
            list.add(t1);
        }
        return list;
    }


    @Test
    public void test(){
        //实例化泛型类时(创建泛型类对象时候)传入具体类型实参，但是这里的类型实参必须是Number类型或者其子类类型
        ExetendsWildcard2<Double> t=new ExetendsWildcard2<>(1.2);
        ExetendsWildcard2<Integer> t2=new ExetendsWildcard2<>(1);

        Double t3 = t.getT();
        Integer t4 = t2.getT();

        //ExetendsSuper3<String> t4=new ExetendsSuper3<>("abc"); //报错
    }



    @Test
    public void test2(){
        //如果实例化时不传入泛型类型实参的话，类型变量默认是Object
        ExetendsWildcard2 t=new ExetendsWildcard2();
        Object t1 = t.getT();
    }


    /**
     * 调用泛型方法
     */
    @Test
    public void test3(){
        //实例化泛型类时(创建泛型类对象时候)传入具体类型实参，但是这里的类型实参必须是Number类型或者其子类类型
        ExetendsWildcard2<Integer> t=new ExetendsWildcard2<>();

        List<Integer> l=new ArrayList<>();
        l.add(1);
        List<Integer> integers = t.fromArrayToList(new Integer[]{1, 2, 3}, new ArrayList<Integer>());
    }
}