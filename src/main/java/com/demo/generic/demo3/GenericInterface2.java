package com.demo.generic.demo3;

/**
 * 其实在以后的开发中我们开发一个项目最基本的目的是将数据库中的数据显示到客户端或者客户端可以将数据录入数据库实现数据的交互，
 * 那么数据库分为关系型数据库(比如mysql)和非关系型数据库(redis)， 关系型数据库是由表组成的，一张数据表一般保存一类信息，
 * 那么张数据表在Java中就对应一个简单Java类(vo类),而且我们会定义一个接口来规范操作这张数据表的实现类开发。
 */

class Department{ }
class Employee{}

interface DepartmentDao{
    int daoCreate(Department vo);
    int daoUpdate(Department vo);
    Department[] findAll();
}


interface EmploymeeDao{
    int daoCreate(Employee vo);
    int daoUpdate(Employee vo);
    Employee[] findAll();
}




/**
 * 以上定义了一个操作部门数据的接口,一个操作雇员信息数据的接口;
 * 发现了以上的只有接口的名称以及每个方法的参数类型不一样，方法的名称以及形式都是一样的，如果有一百张数据表就意味着要定义
 * 一百个这样的接口。此时就出现了代码重复的现象，最好的做法是使用一个接口实现多张数据表的数据操作。要实现这样的操作需要使用
 * 泛型接口。之前每个接口只能操作一种类型的数据，现在使用泛型接口之后，把要操作的数据类型使用占位符标记，具体使用接口的时
 * 候再根据需求指定泛型的类型。
 *
 *
 * 子类实现父接口时候，若子类是泛型类，那么子类中至少一个类型形参/类型变量要和父接口的类型形参/类型变量一致: class GenericSub1<T> implements Wildcard<T>
 * 子类实现父接口时候，若子类不是泛型类，那么父接口必须要明指定具体的类型:class GenericSub1 implements Wildcard<Product>
 * 接口的泛型常用第二种方式，直接在实现类中指定泛型的具体类型；
 * 如果在实现类中继续使用泛型，在实例化实现类对象的时候指定泛型的具体类型
 *  */
interface IBaseDao<T>{
    T daoCreate(T vo);
    T daoUpdate(T vo);
    T[] findAll();
}


/**
 * 子类继承父接口时候，若子类不是泛型类，那么父接口必须要明指定具体的类型
 */
class DepDao implements IBaseDao<Department>{
    @Override
    public Department daoCreate(Department vo) {
        return null;
    }

    @Override
    public Department daoUpdate(Department vo) {
        return null;
    }

    @Override
    public Department[] findAll() {
        return new Department[0];
    }
}


/**
 * 子类继承父接口时候，若子类不是泛型类，那么父接口必须要明指定具体的类型
 */
class EmpDao implements IBaseDao<Employee>{
    @Override
    public Employee daoCreate(Employee vo) {
        return null;
    }

    @Override
    public Employee daoUpdate(Employee vo) {
        return null;
    }

    @Override
    public Employee[] findAll() {
        return new Employee[0];
    }
}




/**
 * @author epanhai
 */
public class GenericInterface2 {
    public static void main(String[] args) {
        DepDao depDao=new DepDao();
        EmpDao empDao=new EmpDao();
    }
}