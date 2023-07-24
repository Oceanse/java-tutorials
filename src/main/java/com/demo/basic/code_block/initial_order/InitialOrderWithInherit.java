package com.demo.basic.code_block.initial_order;


/**
 * Java对象的初始化顺序:
 * <p>
 * 父类静态代码块，父类静态变量 （并列优先级，按代码中出现先后顺序执行）
 * 子类静态代码块，子类静态变量 （并列优先级，按代码中出现先后顺序执行）
 * 父类实例代码块，父类实例变量（并列优先级，按代码中出现先后顺序执行）
 * 父类构造函数
 * 子类实例代码块，子类实例变量 （并列优先级，按代码中出现先后顺序执行）
 * 子类构造函数
 * Note：可以看出先完成基类对象的初始化后(实例代码块+构造)，才会进行子类对象的初始化(构造+实例代码块)
 * <p>
 * 静态代码块/静态变量随着类的加载而被执行初始化，只要类被加载了就会执行，所以在加载父类子类.class到内存时候(main方法之前)就会被执行初始化，所以首先会执行父类和子类中的静态代码块/静态变量
 * 实例代码块/实例变量是用来初始化对象的，是在创建对象的时候被调用，而实例代码块/实例变量又在构造器之前被执行，所以会接着调用父类实例代码块/实例变量和构造器，完成父类对象创建
 * 然后调用子类类实例代码块/实例变量和构造器，完成子类对象创建
 *
 * @author epanhai
 */
public class InitialOrderWithInherit {

    private static String nation = staticSetAndGetNation();

    static {
        System.out.println("父类静态代码块");
        nation = "China";
    }

    {
        city = "Beijing";
        System.out.println("SuperClass 实例代码块");
    }

    private String city = setAndGetCity();


    public InitialOrderWithInherit() {
        city = "Shanghai";
        System.out.println("SuperClass()构造函数");
    }

    public static String staticSetAndGetNation() {
        String nation = "America";
        System.out.println("父类 staticSetAndGetNation() is invoked");
        return nation;
    }


    public String setAndGetCity() {
        String city = "Weifang";
        System.out.println("setAndGetCity() is invoked");
        return city;
    }

    public static String getNation() {
        return nation;
    }

    public String getCity() {
        return city;
    }
}

class SubClass extends InitialOrderWithInherit {

    static {
        System.out.println("子类静态代码块");
        company = "Tencent";
    }

    private static String company = staticSetAndGetCompany();

    private String department = setAndGetDepartment();

    public SubClass() {
        department = "dev";
        System.out.println("SubClass()构造函数");
    }

    {
        department = "test";
        System.out.println("SubClass 实例代码块");
    }


    public static String staticSetAndGetCompany() {
        String company = "Ericsson";
        System.out.println("子类 staticSetAndGetCompany() is invoked");
        return company;
    }

    public String setAndGetDepartment() {
        String department = "operation";
        System.out.println("setAndGetDepartment() is invoked");
        return department;
    }

    public static String getCompany() {
        return company;
    }

    public String getDepartment() {
        return department;
    }
}


class OrderTest {
    public static void main(String[] args) {
        SubClass obj = new SubClass();
        String nation = obj.getNation();
        String city = obj.getCity();
        String department = obj.getDepartment();
        String company = obj.getCompany();
        System.out.println("nation = " + nation);
        System.out.println("city = " + city);
        System.out.println("company = " + company);
        System.out.println("department = " + department);
    }
}