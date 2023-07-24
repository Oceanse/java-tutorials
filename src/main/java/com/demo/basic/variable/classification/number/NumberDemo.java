package com.demo.basic.variable.classification.number;

import org.testng.annotations.Test;

/**
 * 整型：byte short int long
 * 浮点型：浮点类型用来表示有小数部分的数值，可以用小数和科学计数法两种方式表示
 * java中的浮点类型包括float和double
 * double类型比float具有更高的数据精度和范围
 *
 * https://blog.csdn.net/weixin_43844995/article/details/125894333
 * 浮点数的精度指的是它所能表示的小数的精确程度或有效位数。精度越高，浮点数可以表示的小数范围越广且更精确。
 * 在浮点数中，精度通常以有效位数(SignificantDigits)来衡量，即数值中能够准确表达的数字位数。
 * 在java中，单精度浮点数f1oat的精度约为6-7位有效数字，双精度浮点数double的精度约为15位有效数字
 *
 * 需要注意的是，由于浮点数采用二进制表示，无法精确地表示所有的十进制小数。有些小数
 * 可能会出现舍入误差。因此，在进行精确计算时，特别是涉及到金融或淇他需要高精度的领
 * 域时，应该使用专门的精确计算库，如BigDecimal类。
 */
public class NumberDemo {
    /**
     * 数值型变量占用的bit
     * 取值范围
     */
    @Test
    public void testMinAndMax() {
        //Byte
        System.out.println(Byte.MIN_VALUE);//-128
        System.out.println(Byte.MAX_VALUE);//127
        System.out.println(Byte.SIZE);//8
        System.out.println();

        //Short
        System.out.println(Short.MIN_VALUE);//-32768
        System.out.println(Short.MAX_VALUE);//32767
        System.out.println(Short.SIZE);//16
        System.out.println();

        //Character
        System.out.println((int) Character.MIN_VALUE);//0
        System.out.println((int) Character.MAX_VALUE);//65535
        System.out.println(Character.SIZE);//16
        System.out.println();

        /*
          双精度类型 double 比单精度类型 float 具有更高的精度和更大的表示范围
          浮点数有两种表达方式：小数和科学计数法
          Float和Double的最小值和最大值都是以科学记数法的形式输出的，结尾的"E+数字"表示E之前的数字要乘以10的多少次方。
          比如3.14E3就是3.14 × 103 =3140，3.14E-3 就是 3.14 x 10-3 =0.00314。
          */
        //Float
        System.out.println();
        System.out.println(Float.MIN_VALUE); //最小正数
        System.out.println(Float.MAX_VALUE);//最大正数
        System.out.println(Float.SIZE);//32
        System.out.println();

        //Double
        System.out.println(Double.MIN_VALUE);//最小正数
        System.out.println(Double.MAX_VALUE);//最大正数
        System.out.println(Double.SIZE);//64
        System.out.println();
    }


    /**
     * 整数类型有四种表示方式(数制)：
     * 二进制(binary) 八进制(octal) 十进制(decimal) 十六进制(hexadecimal)；
     * <p>
     * 二进制以0b/0B开头；java7新增了对二进制的支持
     * 八进制以0开头；
     * 十六进制以0x/0X开头，其中10~15以a~f(不区分大小写)表示
     * <p>
     * 原码：一个数的二进制表示
     * 反码：原码按位取反（符号位不变）
     * 补码：反码加1
     * <p>
     * 计算机底层数字以补码形式存储；
     * 对于正数来说：原码 反码 补码三者一致
     * 对于负数来说：原码 --->反码--->补码
     * <p>
     * 正整数的原码、反码和补码是一样的，即看到符号位（第一位）是0，就可以照着写出其他两种码。
     * 负数原码转补码：符号位不变，其余各位按位取反，取反后整体加1
     * 负数补码转原码：符号位不变，其余各位按位取反，然后再整体加1，即补码的补码等于原码
     * 补充：原码、反码、补码是有符号位的概念的
     * <p>
     * 在计算机的系统当中,数值都是用补码来表示存储和运算的
     */
    @Test
    public void testNumberSystem() {
        byte decimal = 97;//十进制 97是byte类型数据
        short binary = 0B01100001;// 二进制  0B01100001是short类型;
        int octal = 0141;//八进制 0141是int类型数据
        long hexadecimal = 0X61L;//十六进制  0X61是int类型数据,自动转换成long类型

        //十进制形式打印
        System.out.println(decimal);
        System.out.println(binary);
        System.out.println(octal);
        System.out.println(hexadecimal);

        //某些时候程序需要直接使用二进制，二进制"更真实"，更能体现出整数在内存中的存在形式
        //使用二进制时候，字面值表示一个数的补码，字面值就是计算机底层的存储编码
        int i=0B01100001;//二进制int类型数据，前面的0都省略，补码中最高位是符号位，0正1负，所以该数是正数，正数的原码=补码
        long l=0B01100001L;//二进制long类型数据，前面的0都省略，补码中最高位是符号位，0正1负，所以该数是正数，正数的原码=补码
        System.out.println(i);
        System.out.println(l);

        //二进制负数，这里的字面值表示一个数的补码，补码再求补码就得到原码
        // 先求反码：符号位不变，其余取反， 11111111111111111111111111111110
        // 再求补码(补补得原)：整体加1：11111111111111111111111111111111，其中最高位是符号位，对应的十进制是-2147483647
        int b = 0B10000000000000000000000000000001;
        System.out.println("b = " + b);//-2147483647

        //Integer.toBinaryString() 输出的结果是计算机内存中真正存储的形式(补码)
        String binary2 = Integer.toBinaryString(-2147483647);
        System.out.println("binary = " + binary2);//10000000000000000000000000000001，和上面的结果一致，所以这里获取的是对应的补码
    }

    /**
     * 浮点型数据的表示方法：
     * 小数和科学计数法
     */
    @Test
    public void testFloatAndDoubleDef() {
        float f1 = 3500.0f;
        float f2 = 3.5e3f;//3.5 x 10^3f
        System.out.println(f1);
        System.out.println(f2);

        double d1 = 12000.0;
        double d2 = 12E3;//12 x 10^3
        System.out.println(d1);
        System.out.println(d2);
    }


    /**
     * 对于不带任何后缀的整数:
     * 1 如果将一个 整数常量(整数字面值) 或者 整数常量表达式结果(整数字面值表达式结果) 在byte/short/char范围内的赋值给byte/short/char变量，那么系统会自动把这个整数常量或者整数常量表达式当成byte/short/char类型
     * 2 其他情况下这个整数常量或者整数常量表达式都是int类型(把任意一个整数赋值给int/long/float/double变量,系统会把这个变量当成int)
     * <p>
     * 对于不带任何后缀的浮点数：都是double类型数据
     */
    @Test
    public void testDataType() {

        byte b = 127; //这里127是byte类型数据,因为int类型不能自动转换为byte，所以这里127不是int类型，
        short s = 127; //这里127是short类型数据,因为int类型不能自动转换为short，所以这里127不是int类型，
        char c = 127;//这里127是char类型数据，因为int类型不能自动转换为char，所以这里127不是int类型，
        int i = 127; //这里127是int类型数据
        long l = 127L;//这里127L是long类型是数据
        float f = 127.f;//这里127.f是float类型是数据
        double d = 127.0;//这里127.0是double类型是数据

        long l2 = 127;//这里127是int类型是数据，127会自动转换成long类型127L
        float f2 = 127;//这里127是int类型是数据，127会自动转换成float类型127.0f
        double d2 = 127;//这里127是int类型是数据，127会自动转换成double类型127.0


        byte b2 = 128 - 1;//128-1的结果在byte类型表示范围内， 所以(128-1)是byte类型
        short s2 = 1 + 1;//1+1的结果在short类型表示范围内，所以(1+1)是short类型
        char c2 = 'a' + 1;//'a'+1的结果在char类型表示范围内, 所以('a'+1)是char类型

        System.out.println(1); //不带任何后缀的整数默认是int,ctrl + 点击println验证
        System.out.println(1.0); //不带任何后缀的小数默认是double, ctrl + 点击println验证
    }


    /**
     * 表示long类型时候最好加上后缀，这样应该会省去自动转换的过程，提高性能
     */
    @Test
    public void testDataType2() {
        //byte b=128; 超出其byte取值范围, 128会被认为是int类型
        //long l=9999999999;//系统会把9999999999当作是int类型，但是又超出了int的取值范围，会报 Integer Number too large
        int i = 128;
        long l2 = 9999999999L;//9999999999L表示long类型数据
    }


    /**
     * 自动类型转换
     */
    @Test
    public void testTypeConversion() {
        //赋值场景：为了使变量和变量值类型匹配
        long l = 127;//这里127是int类型是数据，127会自动转换成long类型127L
        float f = 127;//这里127是int类型是数据，127会自动转换成float类型127.0f
        double d = 127;//这里127是int类型是数据，127会自动转换成double类型127.0

        //算数运算场景：运算符两侧的操作数的数据类型必须要相同,如果不一样,会发生隐式类型转化
        double totalPrice = 100.0;
        int quantity = 100;
        double unitPrice = totalPrice / quantity;//quantity变量值的副本进行类型转换，int--->double,保证参与计算的数值类型一致
    }


    /**
     * 强制类型转换:强制显式的把一个数据类型转换为另外一种数据类型,也称之为“narrow conversion”:缩小转换
     * 变量空间存储的变量值复制一份然后进行类型转换，变量本身(变量类型和变量值)并没有发生改变
     * 语法：（targetType）value
     */
    @Test
    public void testTypeConversion2() {
        //float f=2.1;  double类型数据不能直接赋值给float变量
        double d = -2.1;
        float f = (float) d;//double变量存储的变量值2.1复制一份，然后强转成float类型，再赋值
        int i = (int) f; //浮点数到整数的转换是通过舍弃小数得到
        System.out.println(d);   //ctrl + 点击println验证
        System.out.println(f);   //ctrl + 点击println验证
        System.out.println(i);   //ctrl + 点击println验证
    }


    /**
     * 强转可能会引起溢出(Overflow)，造成数据丢失
     */
    @Test
    public void testTypeConversion3() {
        /*
         * 强转可能损失精度example1: ;
         * 强转过程：
         * int类型 22555底层以补码存储(正数原码反码补码一致)：00000000 00000000 01011000 00011011
         * 强转成byte,补码截取掉高位三个字节，保存低位一个字节进行保存  00011011
         * 00011011 最高位是0，表示正数，补码=反码=原码：00011011，转化成十进制就是27
         * */
        int i = 22555;
        byte bt = (byte) i;
        byte bt2 = (byte) 22555;
        System.out.println();
        System.out.println("i: " + i);//i变量的类型和保存的变量值并不会改变
        System.out.println("bt:" + bt);
        System.out.println("bt2:" + bt2);
        System.out.println();


        /*
         * 强转可能损失精度example2;
         * 强转过程：
         * int类型 233底层存储形式(补码)：00000000 00000000 00000000 11101001
         * 强转成byte,补码截取掉高位三个字节，保存低位一个字节进行保存 11101001, 最高位是1，表示负数；
         * 补码的补码就是原码
         * 先求反码：首先符号位不变，其余取反：10010110
         * 再求补码：然后整体加1：10010111， 其中最高位是符号位，所以转成十进制是-23
         * 补充：原码、补码是有符号位的概念的
         * */
        int i2 = 233;
        byte bt3 = (byte) i2;
        byte bt4 = (byte) 233;
        System.out.println(i2);//i2变量的类型和保存的变量值并不会改变
        System.out.println("bt3:" + bt3);
        System.out.println("bt4:" + bt4);
    }


    /**
     * 数值型String--->数值型包装类： 当String类型数据 要转换成int、double、float、long等数据类型时，其数据必须由数字构成,也就是数值字符串
     * 数值基本类型----->数值包装类
     */
    @Test
    public void testTypeConversion4() {
        Integer i = Integer.valueOf("1");
        Integer i2 = Integer.valueOf(1);
        System.out.println(i + i2);//自动拆箱

        Double d = Double.valueOf(1.0);
        Double d2 = Double.valueOf("1.0");
        System.out.println(d + d2);//自动拆箱
    }


    /**
     * 数值型String--->数值基本类型
     */
    @Test
    public void testTypeConversion5() {
        //当String类型数据 要转换成int、double、float、long等基本数据类型时，其数据必须由数字构成
        System.setProperty("number", "20211231");
        String stringNumber = System.getProperty("number");

        int number = Integer.parseInt(stringNumber);
        if (number == 20211231) {
            System.out.println("Good luck");
        }
    }


    /**
     * 浮点数除以0会得到正无穷大或者负无穷大, 被除数或者除数有一个是浮点数，那么被除数除数都会自动转成浮点数
     *
     */
    @Test
    public void testDivision() {
        System.out.println(1.0 / 0);//Infinity
        System.out.println(-1.0 / 0);//-Infinity

        //正数除以0会产生异常：ArithmeticException: / by zero
        System.out.println(1 / 0);
    }
}
