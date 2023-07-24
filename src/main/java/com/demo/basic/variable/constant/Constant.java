package com.demo.basic.variable.constant;

import com.demo.oop.oopintrouce.oopintroduce.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 定义：
 * 常量是指在程序的整个运行过程中值保持不变的量；
 * final修饰变量就变成了常量，其语法: final dataType variableName = value
 * <p>
 * 特点：
 * 常量可以看作一种特殊的变量，只能赋值一次的变量，一旦初始化就不可以被修改
 * 声明明常量的同时一点要赋予一个初始值，即使是成员变量
 * <p>
 * 分类：
 * 作用范围：静态常量、实例常量和局部常量
 * 类型：整型常量值 实型常量值 布尔型常量值 字符型 字符串常量值
 * <p>
 * 命名：
 * 为了与变量区别，常量取名一般都用大写字符；常量名由多单词组成时，每个单词用下划线连接，例如：XXX_YYY_ZZZ、GOOD_LUCK。
 * <p>
 * 赋值：
 * 静态常量和实例常量必须显式初始化
 * 局部常量使用前必须初始化，不使用的话可以不初始化
 *
 * 常量和常量值
 * 常量和常量值是不同的概念，常量可以理解为只能赋值一次的特殊的变量，常量值是常量内存空间保存的数据内容
 * 通常在程序中既可以直接使用常量值，也可以使用常量；
 */
public class Constant {

    //final int M;即使是成员变量也必须要初始化,可以直接初始化或者在代码块中初始化

    /**
     * 实例常量显式初始化
     */
    final String ID = "370784";
    private static final Logger LOG = LogManager.getLogger(Constant.class);
    /**
     * 实例常量代码块中初始化
     */
    final String IP;
    final String PORT;


    /**
     * 实例代码块在对象创建的时候一定会被调用，所以能保证成员常量在对象创建时候一定会被初始化
     */
    {
        IP = "localhost";
        PORT="8088";
        LOG.info("ip:{},port:{}",IP,PORT);
    }


    /**
     * 因为实例代码块在构造函数之前执行，并且已经对常量IP和PORT进行了初始化，所以在构造函数不能再对其进行初始化
     * 常量ID在之前也已经显示初始化，所以在构造函数不能再对其进行初始化
     * 打开下面注释会编译报错
     * @param IP
     * @param PORT
     */
    public Constant(String IP, String PORT, String ID) {
        //this.IP = IP;
        //this.PORT = PORT;
        //this.ID=ID;
    }

    public Constant() {
    }

    /**
     * 静态常量显式初始化
     */
    public static final double PI = 3.14;

    /**
     * 静态常量静态代码块中初始化
     */
    public static final String COMPANY;

    static {
        COMPANY = "ericsson";
    }

    /**
     * 局部常量
     * 常量初始化之后便不能进行修改
     */
    public void testLocalConstant(final int NUMBER) {
        // 局部常量
        final String NATION = "China";

        // 局部常量
        final Person[] PERSON=new Person[NUMBER];

        //NATION="America";  //不能修改常量的值
        //NUMBER=10; //参数NUMBER只能通过参数传递进行初始化，不能在方法中初始化
    }



    /**
     * 常量是一种只能赋值一次的变量，如果没有使用，可以暂时不用初始化
     */
    @Test
    public void testConstantInitialize() {
        final String PORT;//局部常量和实例常量可以重名
        System.out.println("如果没有使用局部常量，可以不进行初始化");
    }

    final int COUNT = 100;

    @Test
    public void testConstantUsage() {
        //静态常量的使用
        double square = Constant.PI * 1 * 1;

        //实例常量的使用
        //实例常量值的副本自动类型转换然后参与算数运算
        double totalPrice = 100.0;
        double unitPrice=totalPrice/this.COUNT;
    }


    @Test
    public void testConstantNoChangeable2() {
        //这里引用类型变量phone 是常量，也就是保存的对象地址永远不会改变，也就是指向的对象不会改变； 但是指向对象的内容可以改变
        final CellPhone phone = new CellPhone("nova5 pro");
        System.out.println(phone);
        //phone = new CellPhone("nova5 pro"); //phone不能重新赋值，不能指向一个新的对象
        //被指对象的内容可以改变
        phone.setBrand("iphone");
        System.out.println(phone);

        //这里workChannels 是常量，永远指向new ConcurrentHashMap<>()
        final Map<String, String> workChannels = new ConcurrentHashMap<>();
        //被指对象的内容可以改变
        workChannels.put("name", "tom");
        workChannels.put("age", "22");
    }
}


class CellPhone {
    String brand;

    public CellPhone(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "CellPhone{" +
                "brand='" + brand + '\'' +
                '}';
    }
}