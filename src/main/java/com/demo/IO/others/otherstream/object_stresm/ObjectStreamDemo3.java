package com.demo.IO.others.otherstream.object_stresm;

import org.testng.annotations.Test;

import java.io.*;

/**
 * 序列化过程：
 * 1 序列化操作的时候系统会把当前类的serialVersionUID写入到序列化文件中，
 * 2 反序列化时系统会去判断文件中的serialVersionUID与当前java类的serialVersionUID是否一致，
 *  如果一致就说明序列化类的版本与当前java类版本是一样的，可以反序列化成功，否则报InvalidClassException异常。
 * jdk文档中有解释，建议我们显式声明serialVersionUID，因为如果不声明，JVM会为我们自动产生一个值
 *
 *
 * serialVersionUID是根据类名、接口名、成员方法及属性等来生成一个64位的哈希字段
 * jdk文档中有解释，建议我们显式声明serialVersionUID，因为如果不显式声明，JVM会为我们自动产生一个serialVersionUID,
 * 这样可能存在两个问题：
 * 1这个默认生成的值和编译器的实现相关，并不稳定，这样就可能在不同JVM环境下出现反序列化时报
 * 2 类结构发生变化的时候，jvm会为实体类重新设置一个新的序列化号serialVersionUID，那么就会和文件中的对象字节流的serialVersionUID不一致，
 * 因此反序化成旧有实例时候会抛出异常
 *
 * 显式声明serialVersionUID ，可以很大程度上避免反序列化过程的失败。
 * 比如当版本升级后，我们可能删除了某个成员变量，也可能增加了一些新的成员变量，
 * 这个时候我们的反序列化依然能够成功，程序依然能够最大程度地恢复数据，保证版本的兼容性，新增增的值则会设置成null，删除的值则不会显示，原有的值正常匹配；
 * 相反，如果不显式指定serialVersionUID ，反序列化时候就会产生异常；
 * 当然，如果类结构发生了非常规性改变，比如修改了类名，类型等，这个时候尽管serialVersionUID 验证通过了，但是反序列化过程
 * 还是会失败，因为类结构有了毁灭性的改变。
 *
 * @author epanhai
 */
public class ObjectStreamDemo3 {
    /**
     * 完成对象序列化，使其可以存储到硬盘
     * @throws IOException
     */
    @Test
    public static void serialize() throws IOException {

        //object输出流建立在文件输出流之上
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("testResource\\test.txt"));

        //创建java对象
        Order order=new Order("123");

        //这里将java对象写入到序列化流(输出流)，从而存储到硬盘文件中
        oos.writeObject(order);

        oos.flush();
        oos.close();
    }


    /**
     * 文件中的字节流反序列化成java对象
     *  序列化之后，如果在Order类中添加了一个新的字段，那么反序列化异常
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public static void deserialize() throws IOException, ClassNotFoundException {

        //object输入流建立在文件输入流之上，文件中保存着对象的序列化数据
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("testResource\\test.txt"));

        //从object输入流读取数据，然后进行反序列化
        Object object = ois.readObject();

        //序列化合反序列化的对象类型对应的包名 类名合类结构必须严格一致， 如果类名合类结构相同，只是包名不同，也会报错
        Order order=(Order)object;
        System.out.println(order.toString());
        ois.close();
    }



}

/**
 * 如果不显式声明serialVersionUID,，JVM会为我们自动产生一个serialVersionUID,
 */
class Order implements Serializable {
    //订单ID
    public String ordId;

    //订单应付金额
   // public float payAmt;


    public Order(String ordId) {
        this.ordId = ordId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ordId='" + ordId + '\'' +
                '}';
    }
}