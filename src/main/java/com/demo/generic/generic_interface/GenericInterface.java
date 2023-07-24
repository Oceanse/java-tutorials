package com.demo.generic.generic_interface;


import com.demo.oop.oopintrouce.oopintroduce.Person;

/**
 * 子类继承泛型类或者实现泛型接口有两种方式：
 * 若子类是泛型类，那么子类中至少一个类型形参/类型变量要和父类的类型形参/类型变量一致: class GenericSub1<T> extends Wildcard<T>
 * 若子类不是泛型类，那么父类必须要明指定具体的类型:class GenericSub3 extends Wildcard<Product>
 *
 * @param <T>
 * @author epanhai
 */
public interface GenericInterface<T> {
    T getT();
}


/**
 * 子类继承父类时候，若子类不是泛型类，那么父类必须要明指定具体的类型
 */
class SubClass implements GenericInterface<String> {

    String t;

    public SubClass(String t) {
        this.t = t;
    }

    @Override
    public String getT() {
        return t;
    }

    public static void main(String[] args) {
        GenericInterface<String> g = new SubClass("persist");
        String t = g.getT();
    }
}


/**
 * 子类继承父类时候，若子类是泛型类，那么子类中至少一个类型形参/类型变量要和父类的类型形参/类型变量一致:
 * @param <T>
 */
class SubClass2<T> implements GenericInterface<T> {

    T t;

    public SubClass2(T t) {
        this.t = t;
    }

    @Override
    public T getT() {
        return t;
    }

    public static void main(String[] args) {
        GenericInterface<Person> g = new SubClass2<>(new Person());
        Person p = g.getT();
    }
}


/**
 * 子类继承父类时候，若子类是泛型类，那么子类中至少一个类型形参/类型变量要和父类的类型形参/类型变量一致:
 * @param <K>
 * @param <T>
 */
class SubClass3<K, T> implements GenericInterface<T> {

    T t;
    K k;

    public SubClass3(K k, T t) {
        this.k = k;
        this.t = t;
    }

    @Override
    public T getT() {
        return t;
    }

    public K getK() {
        return k;
    }

    public static void main(String[] args) {
        SubClass3<String, Integer> sub = new SubClass3<>("ocean", 32);
        String name = sub.getK();
        Integer age = sub.getT();
    }
}