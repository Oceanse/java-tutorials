package com.demo.generic.demo5;


import com.demo.collection_map.model.Apple;
import com.demo.collection_map.model.Product;


public class GenericClass_MethodDemo<T> {//类参数变量<T>，之后可以在属性或者方法返回值或者形参中使用

    /**
     * 带泛型的属性
     */
    private T t;

    /**
     * 带泛型的方法
     *
     * @return
     */
    public T getT() {
        return t;
    }

    /**
     * 带泛型的方法
     *
     * @return
     */
    public void setT(T t) {
        this.t = t;
    }

    /**
     * 泛型方法，独立于泛型类
     * @param e
     * @param <E>
     * @return
     */
    public <E> E getE(E e) {
        return e;
    }


    /**
     * 泛型方法的类型形参和类类型形参名字相同，但是实际上两者之间并没关系，依然相互独立，也就是此T非彼T
     * @param e
     * @return
     * @param <T>
     */
    public <T> T getT(T e) {
        return e;
    }//方法参数变量<E>

    public static void main(String[] args) {
       //泛型类在创建对象的时候传入类型形参
        GenericClass_MethodDemo<Apple> gm = new GenericClass_MethodDemo();
        Apple t = gm.getT();
        gm.setT(new Apple());

        //泛型方法通常在被调用的时候传入类型实参，泛型方法独立于泛型类
        Product 木糖醇 = gm.getE(new Product(1, "木糖醇", 10));
        Product 阿克苏 = gm.getE(new Product(1, "阿克苏", 10));
    }

}


