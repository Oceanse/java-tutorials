package com.demo.basic.method.equals_tostring_cloneable.cloneable;

/**
 * clone()方法保存用于创建对象的精确副本的额外处理任务,不需要编写显式代码将对象的值复制到另一个对象。
 * 如果我们使用new关键字执行它，它将需要执行大量的处理，这就是为什么我们使用对象克隆。
 * 设计模式里有一个模式为原型模式，用原型实例指定创建对象的种类,并且通过拷贝这些原型创建新的对象.
 *
 * Cloneable是标记接口（其方法体为空），它用来表示一个类拥有某些希望具有的特征。实现Cloneable接口的类被标记为可克隆的，而且其对象可以使用Object类中定义的clone()方法克隆。
 * Java 中 一个类要实现clone功能 必须实现 Cloneable接口，否则在调用 clone() 时会报 CloneNotSupportedException 异常。
 * java.lang.Object类中有一个方法clone()，这个方法将返回Object对象的一个拷贝。
 */
public class Product implements Cloneable{
    String name;
    String producer;


    public Product() {
    }

    public Product(String name, String producer) {
        this.name = name;
        this.producer = producer;
    }

    //当前类仍然拥有继承过来的clone方法，不重写可以直接调用，重写后只能通过super调用
    //如果不对其进行重写，这里可以省略
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Product iphone=new Product("Iphone14 Pro","Apple");//原型
        Product iphoneCopy = (Product)iphone.clone(); //副本
        System.out.println(Integer.toHexString(iphone.hashCode())+": "+iphone);//hashCode的16进制数
        System.out.println(Integer.toHexString(iphoneCopy.hashCode())+": "+iphoneCopy);//hashCode的16进制数
        System.out.println(iphone==iphoneCopy);
    }
}
