package com.demo.IO.others.otherstream.object_stresm;

import org.testng.annotations.Test;

import java.io.*;


/**
 * 序列化和反序列化的时候会将transient修饰的字段被忽略
 *
 * @author epanhai
 */
public class ObjectStreamDemo2 {
    @Test
    public static void test() throws IOException, ClassNotFoundException {

        //object输出流建立在文件输出流之上
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("testResource/test.txt"));
        //创建java对象
        Account account = new Account("369", "ocean");
        //这里将java对象写入到序列化流(输出流)，从而存储到硬盘文件中
        oos.writeObject(account);
        oos.flush();
        oos.close();

        //object输入流建立在文件输入流之上，文件中保存着对象的序列化数据
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testResource/test.txt"));
        //从object输入流读取数据，然后进行反序列化
        Object object = ois.readObject();

        //序列化合反序列化的对象类型对应的包名 类名合类结构必须严格一致， 如果类名合类结构相同，只是包名不同，也会报错
        Account account2 = (Account) object;
        System.out.println(account2);
        ois.close();
    }

}

class Account implements Serializable {

    /**
     * 序列化和反序列化的时候会将transient修饰的字段被忽略
     * 业务需要，不宜做序列化
     * 如银行密码等 信息不希望在网络和磁盘等地方存储，所以可以用 transient 声明，从而保证相应信息无法从磁盘读取。
     */
    transient String password;
    String name;

    public Account(String password, String name) {
        this.password = password;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}