package com.demo.IO.others.otherstream.object_stresm;

import org.testng.annotations.Test;

import java.io.*;

/**
 * @author epanhai
 */
public class ObjectStreamDemo4 {
    /**
     * 完成对象序列化，使其可以存储到硬盘
     * @throws IOException
     */
    @Test
    public static void serialize() throws IOException {

        //object输出流建立在文件输出流之上
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("testResource/test.txt"));

        //创建java对象
        User user=new User("1","ocean","vip");

        //这里将java对象写入到序列化流(输出流)，从而存储到硬盘文件中
        oos.writeObject(user);

        oos.flush();
        oos.close();
    }


    /**
     * 文件中的字节流反序列化成java对象
     * 当你添加了serialVersionUID，在反序列旧有实例时，更改的字段值将设为初始化值（对象为null，基本类型为相应的初始默认值），新添加或者删除字段将不显示
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public static void deserialize() throws IOException, ClassNotFoundException {

        //object输入流建立在文件输入流之上，文件中保存着对象的序列化数据
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("testResource/test.txt"));

        //从object输入流读取数据，然后进行反序列化
        Object object = ois.readObject();

        //序列化合反序列化的对象类型对应的包名 类名合类结构必须严格一致， 如果类名合类结构相同，只是包名不同，也会报错
        //新增一个字段，反序列化时候User{CustId='1', CustName='ocean', CustLevel='vip'}
        User user=(User)object;
        System.out.println(user.toString());
        ois.close();
    }


}

/**
 * 手动添加了serialVersionUID，在反序列旧有实例时，
 * 更改的字段值将设为初始化值（对象为null，基本类型为相应的初始默认值），新添加或者删除字段将不显示
 */
class User implements Serializable {

    private static final long serialVersionUID = 4148512431962762557L;

    //序列化之后新增字段
    public String newField="nv";

    /**
     * 用户ID
     */
    public String custId;


    public String custName;

    /**
     * 用户会员等级
     */
    public String custLevel;

    public User(String custId, String custName, String custLevel) {
        this.custId = custId;
        this.custName = custName;
        this.custLevel = custLevel;
    }



    @Override
    public String toString() {
        return "User{" +
                "CustId='" + custId + '\'' +
                ", CustName='" + custName + '\'' +
                ", CustLevel='" + custLevel + '\'' +
                '}';
    }
}