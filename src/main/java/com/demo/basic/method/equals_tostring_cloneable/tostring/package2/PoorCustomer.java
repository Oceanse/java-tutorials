package com.demo.basic.method.equals_tostring_cloneable.tostring.package2;

public class PoorCustomer extends Customer {
    public static void main(String[] args) {
        //PoorCustomer继承Customer，所以会继承拥有Customer的toString方法(Customer的toString方法已经被重写)
        System.out.println(new PoorCustomer());
    }
}
