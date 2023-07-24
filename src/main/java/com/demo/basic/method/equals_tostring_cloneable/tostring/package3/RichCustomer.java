package com.demo.basic.method.equals_tostring_cloneable.tostring.package3;

import com.demo.basic.method.equals_tostring_cloneable.tostring.package2.Customer;
import org.testng.annotations.Test;

/**
 * @author epanhai
 */
public class RichCustomer extends Customer {
    private long balance;
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    /**
     * 重写父类Customer的toString方法
     * @return
     */
    @Override
    public String toString() {
        return "Customer [order=" + order + ", FirstName()=" + getFirstName()   + ", LastName()=" + getLastName()
                + ", balance()=" + getBalance() + "]";
    }




    /**
     * 子类如果重写了父类Customer的toString方法，那么会调用重写后的toString方法
     */
    @Test
    public void testToStringWithOverride(){
        RichCustomer customer = new RichCustomer();
        customer.setFirstName("Rajesh");
        customer.setLastName("Bhojwani");
        customer.setBalance(110);
        Order order = new Order("A1111","Game",100,"In-Shiping");
        customer.setOrder(order);

        //子类重写父类Customer的toString方法
        System.out.println(customer);//Customer [order=Order{orderId='A1111', desc='Game', value=100, status='In-Shiping'}, FirstName()=Rajesh, LastName()=Bhojwani, balance()=110]
        System.out.println(new RichCustomer());//Customer [order=null, FirstName()=null, LastName()=null, balance()=0]

    }


    /**
     * 如果Order没有重写toString方法，也就是注释掉Order的toString方法，
     */
    @Test
    public void testToStringWithoutOverride(){
        RichCustomer customer = new RichCustomer();
        customer.setFirstName("Rajesh");
        customer.setLastName("Bhojwani");
        customer.setBalance(110);
        Order order = new Order("A1111","Game",100,"In-Shiping");
        customer.setOrder(order);
        System.out.println(customer);//Customer [order=com.demo.basic.method.equals_tostring_cloneable.tostring.package3.Order@6f4a47c7, FirstName()=Rajesh, LastName()=Bhojwani, balance()=110]
        System.out.println(new RichCustomer());//Customer [order=null, FirstName()=null, LastName()=null, balance()=0]
    }





}


class Order{
    private String orderId;
    private String desc;
    private long value;
    private String status;

    public Order(String orderId, String desc, long value, String status) {
        this.orderId = orderId;
        this.desc = desc;
        this.value = value;
        this.status = status;
    }

//    @Override
//    public String toString() {
//        return "Order{" +
//                "orderId='" + orderId + '\'' +
//                ", desc='" + desc + '\'' +
//                ", value=" + value +
//                ", status='" + status + '\'' +
//                '}';
//    }
}