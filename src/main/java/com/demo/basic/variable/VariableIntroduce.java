package com.demo.basic.variable;

import com.demo.basic.variable.classification.enums.demo2.ColorEnum;
import com.demo.oop.aggregation.Address;
import com.demo.oop.inherit.demo2_this_super.demo1.Person;
import org.testng.annotations.Test;
import java.util.*;

/**
 * 变量定义：本质是申请一块特定类型及特定名字的内存空间存储特定类型的数据
 * <p>
 * 变量三要素：数据类型 变量名 变量值
 * 变量类型: 决定内存空间大小
 * 变量名：决定内存空间的地址
 * 变量值: 决定内存空间存储的内容
 * <p>
 * 定义格式：变量类型 变量名(DataType identifier)
 * <p>
 * 变量类型：
 * 变量分类：
 * 按类型：八种基本数据类型(Primitive Data Type：数值型，字符型，布尔型)   三种引用数据类型(Reference Data Type:类似于指针，类 接口 数组)
 * 按作用域：局部变量 全局变量(类变量和实例变量)
 * <p>
 * 变量类型转换：
 * 基本数据类型：自动类型转换和强制类型转换，本质是对变量空间存储的变量值/数据的副本进行类型转换，变量类型(接收的数据类型)和变量存储的变量值类型并没有发生改变，也就是变量本身没有发生变化
 * 引用数据类型：向上转型和向下转型
 * 基本数据类型和引用数据类型：装箱/拆箱
 * <p>
 * 变量名：
 * 变量名就是这块内存空间的名字(同一个作用域内，变量名不能相同)，个人理解变量名就是一块内存空间的地址
 * 标识符(identifier)：本意是标识某个实体的符号，可以划分为普通标识符和特殊标识符
 * 关键字(keywords)：特殊标识符就是关键字，对Java编译器具备特殊意义的符号(特殊标识符)，不能用作 变量名、方法名等普通标识符
 * 普通标标识符：标识符通常是指普通标识符，java中所有自己命名的位置所用的都是普通标识符: 包名 接口名 类名 变量名 方法名 参数名，因此变量名就是标识符的一种
 * 标识符命名规则：字母(含英、中、日、俄等)，0-9 ，_或 $，不能以数字开头，不能是关键字、保留字
 * 变量名命名规则和规范：
 * 1 遵循标识符命名规则
 * 2 驼峰命名法，第一个单词以小写字母开始；从第二个单词开始以后的每个单词的首字母都采用大写字母，比如totalPrice
 * 3 变量名要尽量见名知意，如订单 order，不要写 a、b、c 这种名称；
 * 4 同一作用域内，变量名不能重复
 * <p>
 * 变量值：
 * 变量值就是变量内存空间存储的内容数据；
 * Java语言是强类型语言，变量(存储空间)和变量值(存储数据)都具备类型属性，特定类型的变量只能存储特定类型的数据，也就是变量和存储数据要类型匹配，float f=1,这里会先把变量值自动类型转换，再赋值
 * 基本类型变量存储字面值(包括基本类型数值)，引用类型变量存储对象的地址；
 * 特殊变量值null：可以看作一种特殊的任意引用类型的变量值，可以赋值给任意引用类型的变量，不能赋值给基本类型变量，可以理解为内存空间存储着null这种不指向任何位置的特殊引用数据
 * 变量空间在程序运行期间可以反复写入赋值，所以称之为变量， 后面写入的值会覆盖前面的值。编程的本质就是对内存数据进行访问和修改。
 *
 * @author epanhai
 */
public class VariableIntroduce {


    /**
     * 变量的声明和初始化：开辟内存空间，存储数据
     * 通过变量名访问和修改
     */
    @Test
    public void testVariableDeclareAndAssign() {

        //先声明，后初始化
        int 年龄;  //声明只是分配内存空间，但是内存空间没有存储内容，并起名为"年龄"
        年龄 = 31;    //给变量名为"年龄"的内存空间赋值31
        System.out.println(年龄);//这里进行值传递，把变量值31拷贝一份传递给System.out.println(int x)，打印变量"年龄"存储的字面值

        Person person;
        person = new Person();
        System.out.println(person);//这里进行值传递，把变量person存储的对象地址传递给System.out.println(Object x)，打印变量person存储的对象地址

        String[] myHobbies;
        myHobbies = new String[]{"coding", "reading", "travel"};


        //批量声明多个同类型变量，然后再初始化
        String username, city, phone;
        username = "ocean";
        city = "Shanghai";
        phone = "18800203512";
        System.out.println("My name is " + username + " and come from " + city + " and my phone no is " + phone);

        Person human1, human2;
        human1 = new Person();
        human2 = new Person();

        Person[] humans1, humans2;
        humans1 = new Person[]{human1};
        humans2 = new Person[]{human2};


        //同时声明初始化
        double score = 99.5;//声明double类型变量的同时赋值为1.0
        Address address = new Address("Shandong", "Weifang");
        String[] subjects = {"chinese", "math", "english"};


        //批量声明初始化多个同类型变量
        int x = 5, y = 6, z = 50;
        Address address1 = new Address("Qinghai", "Xining"), address2 = new Address("Guangdong", "Shenzhen");
        double[] myList1 = {1.1, 2.2}, myList2 = {3.3, 4.4};


        //反复修改变量值,包括字面值和引用值
        char letter = 'a';//声明char类型变量的同时赋值为'a'
        letter = 'b';     //将变量c存储的值重新赋值为'b'
        System.out.println(letter);

        Person p = new Person("xm", 54);//p指向xm对象
        p = new Person("xh", 64);//p重新指向xh对象
        p.info();
    }


    /**
     * 变量按照类型分类：八种基本数据类型和三种引用类型接口
     */
    @Test
    public void testVariableType() {

        //八种基本数据类型：数值型 字符型 布尔型 或者数值型和布尔型(字符型可以看作是数值型)
        //所有的基本数据类型的大小（所占用的字节数）都已明确规定，在各种不同的平台上保持不变，这一特性有助于提高 Java 程序的可移植性
        byte b = 123;   //byte占有内存1个字节（8bit）,取值范围-2^7~2^7-1也就是-128~127(2^8=256)
        short s = 256;  //2字节 取值范围-2^15~2^15-1
        int i = 456888; //4字节 取值范围-2^31~2^31-1
        long l = 234L;  //8字节 取值范围-2^63~2^63-1
        float f = 1.0F; //4字节, 数值后面紧跟F或者f
        double d = 2.0; //8字节
        char c = '女';   //2字节(2^16=65536: 0~65535, \u0000~\uffff)
        boolean flag = true;

        //三种引用数据类型: 接口 类 数组
        //引用类型变量存储对象的引用(地址)
        String myName = "ocean";
        Map<String, String> envVariable = new HashMap<>();
        String[] nameList = {"Tom", "Marry"};
        ColorEnum blue = ColorEnum.BLUE;//枚举也是一种特殊的类

        //变量值null属于任何引用类型，是一种不指向任何位置的特殊引用类型数据
        Comparator comparator = null;
        Object object = null;
        Double[] numbers = null;
        System.out.println(null instanceof Object);//false，instanceof可以理解为堆对象/方法区对象是否属于指定类型，null不指向任何堆对象/方法区对象，所以返回false
    }


    /**
     * 变量类型转换本质：
     * 无论是自动类型转换还是强制类型转换，本质是对变量空间存储的变量值复制一份然后进行类型转换(对变量值的副本进行转换)，变量本身(变量类型和变量存储的变量值类型)并没有发生改变
     * 另外可以直接对字面值进行类型转换
     */
    @Test
    public void testTypeConversion() {
        //这里本质是对变量i存储的变量值"1"的副本进行自动类型转换(先复制，再转换)，变量i的类型和存储的变量值并没有改变；
        //可以理解为读取变量i存储的变量值"1"，然后复制一份，然后对副本进行类型转换
        int i = 1;
        double result = i / 1.0;
        int i2 = i; //这里说明i变量值类型是int
        System.out.println(i);//i变量类型和存储的变量值都没有变(ctrl + 点击println验证)
        System.out.println(result);//ctrl + 点击println验证result是double类型

        //这里本质是对变量d存储的变量值"99.1"的副本进行强制类型转换，变量d的类型和存储的变量值并没有改变；
        double d = 99.1;
        int i3 = (int) d;
        double d2 = d;//这里说明d变量值类型是double
        System.out.println(d);//d变量类型和存储的变量值都没有变(ctrl + 点击println验证)
        System.out.println(i3);
    }


    /**
     * 自动类型转换
     * 自动类型转换场景：赋值，算数运算，参数值传递，返回值
     * 本质：变量空间存储的变量值复制一份然后进行类型转换，变量本身(变量类型和变量值)并没有发生改变
     * 自动类型转换的对象：变量或者字面值
     * 容量小的类型自动转换为容量大的数据类型: byte-->short、char-->int-->long-->float-->double(也可以跨越转换)
     * 赋值场景自动类型转换原因：变量类型和变量值类型要保持一致
     */
    @Test
    public void testTypeAutoConversionWhenAssign() {
        byte b=127;
        //把b变量存储的字面值的副本127自动转换成short类型后赋值给short变量
        short s=b;

        //short和char之间不能自动类型转换，因为一个是带符号，一个是无符号
        //char c=s;
        char c='c';
        //s3=c3;

        //short和char自动转换成int类型
        int i =s;
        i = c;

        //long-->float-->double
        long l = i;
        float f = l;
        double d = f;
        System.out.println(b);
        System.out.println(s);
        System.out.println(c);
        System.out.println(i);
        System.out.println(l);
        System.out.println(f);
        System.out.println(d);

        //byte类型自动转换成long类型，自动类型转换可以跨越转换
        long l2=(byte)1;
    }


    /**
     * 自动类型转换
     * 自动类型转换场景：赋值，算数运算，参数值传递，返回值
     * 本质：变量空间存储的变量值复制一份然后进行类型转换，变量本身(变量类型和变量值)并没有发生改变
     * 自动类型转换的对象：变量或者字面值
     * 容量小的类型自动转换为容量大的数据类型: byte-->short、char-->int-->long-->float-->double(也可以跨越转换)
     * 算数运算景自动类型转换：算数运算有多种类型的数据混合运算时， 系统首先自动将所有数据转换成容量最大的那种数据类型，然后再进行计算
     * 算术运算场景自动类型转换原因：运算符两侧的数据类型要保持一致
     */
    @Test
    public void testTypeAutoConversionWhenArithmetic() {
        System.out.println("自动类型转换场景：算数运算");
        //若参与运算的数据类型不同，则先自动转换成同一类型，然后进行运算。
        //long自动转为float(5->5.0f),然后进行运算
        System.out.println(12.0f / 5);
        //long自动转为double(5->5.0),然后进行运算
        System.out.println(12.0 / 5L);
    }


    /**
     * byte、short、char之间进行算数运算不会互相转换，而是转换成int再计算，即使是同类型间计算也会先转换成int再计算
     * byte、short在赋值场景下可以自动类型转换：byte--->short
     * 但是short和char之间任何场景都不能自动类型转换,因为两个虽然都是双字节，但是一个是带符号，一个是无符号，两者最小值和最大值之间的取值范围不同
     */
    @Test
    public void testTypeAutoConversionWhenArithmetic2() {
        //byte,short,char三者在计算时首先转换为int类型, 再进行计算，即使是同类型之间的运算;
        byte b = 1;
        short s = 2;
        char c = 'a';
        //byte bb=b+b; //报错
        //short ss=s+s; //报错
        //char cc = c + c; //报错

        int b2 = b + b;
        int s2 = s + s;
        int c2 = c + c;
        int sum = b + s + c;

        //ctrl + 点击println验证b + b是int类型
        System.out.println(b + b);
        //ctrl + 点击println验证s + s是int类型
        System.out.println(s + s);
        //ctrl + 点击println验证c + c是int类型
        System.out.println(c + c);
        //ctrl + 点击println验证b + s + c是int类型
        System.out.println(b + s + c);
    }


    /**
     * 任何基本类型的值和字符串值进行连接运算时(+)，基本类型的值将自动转化为字符串类型，此时的+就是连接符
     * 注意区分区分运算符和连接符
     */
    @Test
    public void testTypeAutoConversionWhenArithmetic3() {
        System.out.println('a' + 1 + "Hello!");//第一个+是运算符，第二个+是字符串连接符
        System.out.println("Hello!" + 'a' + 1);//这里的两个+都是字符串连接符
    }


    /**
     * 参数值传递时候可以自动类型转换以匹配形参类型，本质也是赋值场景自动类型转换，
     * 目的是使得变量类型和变量值类型要保持一致
     */
    @Test
    public void testTypeAutoConversionWhenPassParameter() {
        sum(1, 2);//字面值1和2会自动转换从long类型，然后传给形参
        int a = 3;
        int b = 4;
        sum(a, b);//变量a和b存储的变量值的副本会自动转换成long类型，然后传给形参
    }

    public long sum(long n1, long n2) {
        return n1 + n2;
    }


    /**
     * 返回值会自动类型转换以匹配返回值类型
     *
     * @param n1
     * @param n2
     * @return
     */
    public long testTypeAutoConversionWhenReturn(int n1, int n2) {
        return n1 + n2;
    }


    /**
     * 强制类型转换:强制显示的把一个数据类型转换为另外一种数据类型,也称之为“narrow conversion”:缩小转换
     * 变量空间存储的变量值复制一份然后进行类型转换，变量本身(变量类型和变量值)并没有发生改变，也就是对变量值的副本进行强制转换
     * 常见：实数--->整数， 大整数--->小整数，  整数--->char
     * 语法：（targetType）value
     */
    @Test
    public void testTypeExplicitConversion() {
        //这里本质是对变量totalPrice存储的变量值"9.1"的副本进行强制类型转换， 变量totalPrice的类型和存储的值并没有改变；
        //可以理解为读取变量totalPrice存储的变量值"9.1"，然后复制一份，然后对副本进行类型转换
        double totalPrice = 9.1;
        int unitPrice = (int) totalPrice / 3;
        //ctrl + 点击println验证old2是double类型
        System.out.println(totalPrice);
        //ctrl + 点击println验证result2是int类型
        System.out.println(unitPrice);
    }


    /**
     * 强转可能会引起溢出()，造成数据丢失
     */
    @Test
    public void testTypeExplicitConversion2() {
        /*
         * 强转过程：
         * int类型 22555底层以补码存储(正数原码反码补码一致)：00000000 00000000 01011000 00011011
         * 强转成byte,补码截取掉高位三个字节，保存低位一个字节00011011，而此数据还是补码表示
         * 00011011 最高位是0，表示正数，补码等于反码等于原码：0O0011011，转化成十进制就是27
         * 补充：原码、反码、补码是有符号位的概念的
         * */
        int i = 22555;
        byte bt = (byte) i;
        byte bt2 = (byte) 22555;
        //i的类型和保存的值并不会改变
        System.out.println("i: " + i);
        System.out.println("bt:" + bt);
        System.out.println("bt2:" + bt2);
    }


    /**
     * 强转可能会引起溢出()，造成数据丢失
     */
    @Test
    public void testTypeExplicitConversion3() {
        /*
         * 强转过程：
         * int类型 130底层以补码存储(正数原码反码补码一致)：00000000 00000000 00000000 10000010
         * 强转成byte,补码截取掉高位三个字节，保存低位一个字节10000010，最高位是0，表示正数，而此数据还是补码表示，我们把它转换为原码
         * 补码的补码就是原码
         * 先求反码：首先符号位不变，取其余取反：11111101(负数的反码为符号位不变,数值位按位取反)
         * 再求补码：加1：11111110，转换为十进制 -126
         * 补充：原码、补码是有符号位的概念的
         * */
        byte bt = (byte) 130;
        System.out.println("bt:" + bt);
    }

    public static void main(String[] args) {
        System.out.println("good luck!");
    }

}


