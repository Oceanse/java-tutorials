package com.demo.basic.method.equals_tostring_cloneable.tostring.package2;

/**
 * @author epanhai
 */
public class Customer {
    private String firstName;
    private String lastName;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 重写原生的toString方法
     * @return
     */
    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public void info(){
        //等价于System.out.println("this.toString: "+this.toString());
        System.out.println("this: "+this);
    }

    /**
     * 重写前的toString方法可以认为还会存在子类中，只不过只能通过super访问
     */
    public void info2(){
        //调用当前对象的父类对象的原生toString
        System.out.println("super.toString: "+super.toString());
    }

    public static void main(String[] args) {
        Customer customer=new Customer("haiyang","pan");

        //打印重写后的toString
        System.out.println("customer: "+customer);
        System.out.println("customer.toString: "+customer.toString());
        customer.info();

        //打印原生的toString
        customer.info2();//Customer对象调用父类对象的原生toString
    }
}
