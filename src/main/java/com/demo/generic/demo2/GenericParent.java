package com.demo.generic.demo2;


import com.demo.basic.method.equals_tostring.tostring.package1.Apple;
import com.demo.collection_map.model.Product;

/**
 * 子类继承泛型类或者实现泛型接口有两种方式：
 * 子类继承父类时候，若子类是泛型类，那么子类中至少一个类型形参/类型变量要和父类的类型形参/类型变量一致: class GenericSub1<T> extends Wildcard<T>
 * 子类继承父类时候，若子类不是泛型类，那么父类必须要明指定具体的类型:class GenericSub3 extends Wildcard<Product>
 * @author epanhai
 * @param <T>
 */
public class GenericParent<T> {//类类型变量或者类类型形参，之后可以在属性或者方法返回值/形参中使用

    //带泛型的属性
    private T t;


    /**
     * 带泛型的方法
     * @return
     */
    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

}


/**
 * 子类继承父类时候，若子类是泛型类，那么子类中至少一个类型形参/类型变量要和父类的类型形参/类型变量一致:
 */
class GenericSub1<T> extends GenericParent<T> {//子类和父类类型形参一致
    public static void main(String[] args) {
        //创建对象时候知名具体的类类型实参
        GenericSub1<Integer> s=new GenericSub1();
        s.setT(123);
        Integer no = s.getT();
    }
}


/**
 * 子类继承父类时候，若子类是泛型类，那么子类中至少一个类型形参/类型变量要和父类的类型形参/类型变量一致
 * @param <T>
 * @param <E>
 */
class GenericSub2<T,E> extends GenericParent<T> {
    E e;

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }


    public static void main(String[] args) {
        GenericSub2<String,Integer> s=new GenericSub2<>();
        s.setT("ocean");
        s.setE(31);
    }
}

/**
 * 子类继承父类时候，若子类不是泛型类，那么父类必须要明指定具体的类型
 */
class GenericSub3 extends GenericParent<Product> {

    public static void main(String[] args) {
        GenericSub3 s=new GenericSub3();
        s.setT(new Product(2, "洗发水", 12));
        Product product = s.getT();
    }
}


/**
 * 子类继承父类时候，若父类子类不是泛型类，父类没有指定具体的类型，
 * 那么等价于class GenericSub4 extends GenericParent<Object>
 */
class GenericSub4 extends GenericParent {

    public static void main(String[] args) {
        GenericSub4 s=new GenericSub4();
        s.setT(new Apple("green"));
        Object t = s.getT();
    }
}








