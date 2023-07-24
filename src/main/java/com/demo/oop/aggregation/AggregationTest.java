package com.demo.oop.aggregation;


public class AggregationTest {

    public static void main(String[] args) {
        Address address1 = new Address("广州", "广东");
        Address address2 = new Address("海口", "海南");

        Employee e = new Employee(111, "Wang", address1);
        Employee e2 = new Employee(112, "Zhang", address2);

        e.display();
        e2.display();

    }

}
