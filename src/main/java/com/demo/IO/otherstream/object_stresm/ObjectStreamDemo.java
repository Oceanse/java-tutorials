package com.demo.IO.otherstream.object_stresm;

import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 序列化(serial):把对象转换为字节序列的过程称为对象的序列化。
 * 反序列化(deserial)把字节序列恢复为对象的过程称为对象的反序列化。
 *
 * 对象的序列化主要有两种用途：
 * 1） 把对象的字节序列(字节流)永久地保存到硬盘上，通常存放在一个文件中；
 * 2） 在网络上传送对象的字节序列(字节流)。
 * 所有在网络上传输的对象都是可序列化的，比如远程方法调用过程中的参数和返回值都必须实现序列化；
 * 因此程序创建的每个javabean都建议实现serializable接口
 *
 *
 * ObjectOutputStream：序列化java对象到硬盘
 * ObjectInputStream： 将硬盘中的数据反序列化到内存
 * @author epanhai
 */
public class ObjectStreamDemo {

    /**
     * 完成对象序列化，使其可以存储到硬盘
     * @throws IOException
     */
    @Test
    public static void test() throws IOException {

        //object输出流建立在文件输出流之上
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("testResource/test.txt"));

        //创建java对象
        Flower flower=new Flower("rose");

        //这里将java对象写入到序列化流(输出流)，从而存储到硬盘文件中
        oos.writeObject(flower);

        oos.flush();
        oos.close();
    }


    /**
     * 完成对象序列化，使其可以存储到硬盘
     * @throws IOException
     */
    @Test
    public static void test1_2() throws IOException {

        //object输出流建立在文件输出流之上
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("testResource/test.txt"));

        //ArrayList也实现了Serializable
        ArrayList flowers=new ArrayList<Flower>();
        flowers.add(new Flower("rose"));
        flowers.add(new Flower("lily"));

        //这里将所有的java对象写入到序列化流(输出流)，从而存储到硬盘文件中
        oos.writeObject(flowers);
        oos.flush();
        oos.close();
    }



    /**
     * 反序列化，将文件中的字节码还原成java对象
     * 先执行test()
     * @throws IOException
     */
    @Test
    public static void test2() throws IOException, ClassNotFoundException {

        //object输入流建立在文件输入流之上，文件中保存着对象的序列化数据
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("testResource/test.txt"));

        //从object输入流读取数据，然后进行反序列化
        Object object = ois.readObject();

        //序列化合反序列化的对象类型对应的包名 类名合类结构必须严格一致， 如果类名、类结构相同，包名不同，也会报错
        Flower flower=(Flower)object;
        System.out.println(flower.toString());
        ois.close();
    }

    /**
     * 反序列化，将文件中的字节码还原成java对象
     * 先执行test1_2()
     * @throws IOException
     */
    @Test
    public static void test2_2() throws IOException, ClassNotFoundException {

        //object输入流建立在文件输入流之上，文件中保存着对象的序列化数据
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("testResource/test.txt"));

        //从object输入流读取数据，然后进行反序列化
        Object object = ois.readObject();

        //序列化合反序列化的对象类型对应的包名 类名合类结构必须严格一致， 如果类名合类结构相同，只是包名不同，也会报错
        List<Flower> flowers=(List<Flower>)object;
        System.out.println(flowers);
        ois.close();

    }

}


/**
 * Serializable是一个标识接口，不显示定义 serialVersionUID ，jvm会给这个接口的实现类默认添加一个序列化版本号，如果类中显式的指定了序列化版本号，则jvm不再添加
 *
 *
 * Java的序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的。在进行反序列化时，
 * JVM会把传来的字节流中的serialVersionUID与本地相应实体(类)的serialVersionUID进行比较：
 * 如果相同就认为是一致的，可以进行反序列化，否则就会出现序列化版本不一致的异常。
 *
 * 不显示定义 serialVersionUID， jvm 会根据类的内容自动生成 serialVersionUID，如果对类的源代码作了修改，再重新编译，新生成的类文件的serialVersionUID值会发生变化。
 */
class Flower implements Serializable {
    String name;

    public Flower(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                '}';
    }
}