package com.demo.basic.keywords.statics.static_variable;

/**
 * @author epanhai
 */
public class Employee {
    private int id;
    private static int nextId=0;
    private static String company="Tecent";
    private double salary;

    public Employee(double salary) {
        this.salary=salary;
        //构造函数可以引用静态变量
        this.id=nextId;
        nextId++;
    }


    public void raiseSalary(double byPercent){
        this.salary=salary+salary*byPercent/100;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", salary=" + salary +
                '}';
    }

    public static void main(String[] args) {
        Employee employee = new Employee(10000);
        Employee employee2 = new Employee(10000);
        Employee employee3 = new Employee(10000);
        System.out.println(employee);
        System.out.println(employee2);
        System.out.println(employee3);
    }
}
