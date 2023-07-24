package com.demo.IO.others.otherstream.object_stresm;

import org.testng.annotations.Test;

import java.io.*;

/**
 * 如果使用序列化机制向文件中写入了多个Java对象，使用反序列化机制恢复对象时必须按实际写入的顺序读取
 * @author epanhai
 */
public class ObjectStreamDemo5 {


    @Test
    public void serialize() throws IOException {
        //object输出流建立在文件输出流之上，try语句会在程序结束时自动关闭资源。
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("testResource\\test.txt"))){
            //创建java对象
            Address address=new Address("Shanghai");
            Customer customer=new Customer(1,address);

            Address address2=new Address("Suzhou");
            Customer customer2=new Customer(2,address2);

            //这里将多个java对象写入到序列化流(输出流)，从而存储到硬盘文件中
            oos.writeObject(customer);
            oos.writeObject(customer2);
            oos.writeObject(customer2);

            oos.flush();
            //oos.close(); 这里不再需要手动关闭
        }
    }


    @Test
    public static void deserialize() throws IOException, ClassNotFoundException {

        //object输入流建立在文件输入流之上，文件中保存着对象的序列化数据
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("testResource\\test.txt"))) {

            //如果使用序列化机制向文件中写入了多个Java对象，使用反序列化机制恢复对象时必须按实际写入的顺序读取
            Customer customer1 = (Customer) ois.readObject();
            Customer customer2 = (Customer) ois.readObject();
            Customer customer3 = (Customer) ois.readObject();

            //false
            System.out.println(customer1 == customer2);
            //false
            System.out.println(customer1 == customer3);
            //true
            System.out.println(customer2 == customer3);
           // ois.close(); 这里不再需要手动关闭
        }
    }




}


/**
 * 这里序列化Customer对象时候也会序列化Address对象，
 * 所以也要保证Address可序列化
 */
class Customer implements Serializable {
    int id;
    Address address;

    public Customer() {
    }

    public Customer(int id, Address address) {
        this.id = id;
        this.address = address;
    }
}

class Address implements Serializable{
    String city;

    public Address() {
    }

    public Address(String city) {
        this.city = city;
    }
}