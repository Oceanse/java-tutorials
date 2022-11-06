package com.demo.basic.variable.variable_datatype.char_variable;

import org.testng.annotations.Test;


/**
 * Java虚拟机规范中明确说明了java的char类型使用的编码方案是UTF-16
 * UTF-16 的编码长度要么是 2 个字节（U+0000 到 U+FFFF），要么是 4 个字节（U+010000 到 U+10FFFF）;
 * <p>
 * Java中常见的char基本位于unicode表的BMP平面，码点(编号)范围或者数值范围： 十进制--->0 ~65535;   十六进制--->0x0000~0xFFFF； 二进制--->16个0~16个1
 * 所以char字符的编码统一使用unicode码点(编号)的16位二进制(不足用0补齐)作为存储数值
 * 简单来说就是字符编号就是其存储编码.
 * <p>
 * 对于java字符来说，计算机底层保存的是字符的编号(unicode码)，(每个编号都有对应的字符)，双字节无符号十进制表示范围：0~65535，因此它可以作为
 * 整型值参与加减乘除以及大小比较；所以char类型变量本质上也是数值类型变量，严格说是无符号数值型变量
 * <p>
 * 总结：
 * 1 unicode字符集中每个符号都有对应的编号(码点)；
 * 2 utf-16, 编号范围在0~65535内的符号，编号的16位二进制就是其底层编码
 * 3 char类型变量可以理解成存储字符或者字符对应的编号
 * <p>
 * 字符型值三种表示形式：
 * 1 符号形式
 * A: 普通字符(单个符号): 'A', '1' '@'
 * B: 转义字符: 转义字符=转义符号\+被转义符号，比如：  '\t', '\n', '\\'， '\"', '\''  这里的每个转移字符\t，\n等对java编译器来说是单个符号
 * <p>
 * 2 数值形式(编号)
 * A 十进制    char a=0; char b=65535
 * B 十六进制  char c=0X0000, char d=0XFFFF
 * C 二进制    char e=0B0
 * char变量值强转成int就是字符对应的十进制
 * <p>
 * 3 Unicode码表示一个char字符(类似于十六进制表示)：'\uaaaa', 其中a代表十六进制整数，取值范围： '\u0000'~'\uffff'
 * 这里四位十六进制整数相当于16位二进制整数，以一共支持65536种字符,其中前256个字符('\u0000'~'\u00ff')和ASCII中的字符完全相同
 * <p>
 * <p>
 * <p>
 * 中英翻译：
 * 转义字符：escape character
 * \u1234: Unicode escape sequence
 * 即使在源代码注释中使用了非法unicode转义字符序列，编译器也会报错
 * if you have an illegal Unicode escape sequence in your source, it will be flagged as an error even though it's inside a comment
 * @author epanhai
 */
public class CharDemo {


    /**
     * 字符表示形式：普通字符  转移字符   unicode码字符   数值
     */
    @Test
    public void test() {

        //普通字符：单个符号作为字符值
        char c = 'a';

        //转移字符：转义字符作为字符值; 转义符号\+被转义符号=转移字符，转移字符对java编译器来说单个符号;
        char c2 = '\t';

        //unicode码字符：'\uaaaa'作为字符值，其中a代表十六进制整数
        char c3 = '\u12ab';

        //将一个在byte/short/char范围内的整数赋值给byte/short/char变量，那么系统会自动把这个数当成byte/short/char类型;
        //0~65535范围内的十进制或者十六进制整数作为字符值；char变量本质是无符号整数，表示范围从0~65535，只不过每个整数都对应一个字符，比如'a'<--->97
        //0~65535范围内的数值被赋值给char类型变量时会被编译器当做char类型变量值
        char c4_1 = 0;
        char c4_2 = 65535;
        char c4_3 = 0x0000;
        char c4_4 = 0xffff;
        char c4_5 = 0B0;
        char c4_6 = 0B1111111111111111;

        char c4_7 = 97;
        char c4_8 = 0B01100001; //十进制97，也就是字符'a'
        char c4_9 = 0x61;//十进制97，也就是字符'a'
        char c4_10 = '\u0061';//这里必须是4位，不足高位用0补齐；十进制97，也就是字符'a'
        System.out.println(c == c4_7);
        System.out.println(c4_7 == c4_8);
        System.out.println(c4_8 == c4_9);
        System.out.println(c4_9 == c4_10);


        int i = 1;
        // char c5=i;   //这里变量i是int类型变量，int类型变量不能自动转换为char
        char c5=1;    //0~65535范围内的数值被赋值给char类型变量时会被编译器当做char类型变量值

        char c6 = (char) i;// int类型变量强制转换为char,然后赋值给char类型变量c6, 注意这里墙砖的是变量i存储的变量值的副本

        //char c5=65536;  //char 只能存储0~65535， 当大于65535会将其视为int类型，而int类型变量不能自动转换为char
    }


    /**
     * 字符表示形式：普通字符  转移字符   unicode码字符   数值
     */
    @Test
    public void test1_2() {
        char space = ' ';//英文半角空格，
        char space2 = 32;
        char space3 = '\u0020';//对应英文半角空格' '
        System.out.println(space == space2);
        System.out.println(space2 == space3);
        System.out.println("b" + space + "d");
        System.out.println("b" + space2 + "d");
        System.out.println("b" + space3 + "d");
    }


    /**
     * 字符表示形式：普通字符  转移字符   unicode码字符   数值
     * 注意：数值也可以是字符类型，把一个字符类型范围内的整数赋值给一个字符变量，那么这个整数就是字符类型
     */
    @Test
    public void test1_3() {
        char letter = 65, letter2 = 97;
        System.out.println(letter);//打印字符形式
        System.out.println(letter2);//打印字符形式
    }


    /**
     * 算数运算时候会自动类型转换
     * 字符参与四则运算 大小比较，本质是字符的编号参与运算
     */
    @Test
    public void test2() {
        char c = 'a';
        char c2 = 'b';
        System.out.println(c + 1);//把变量c保存的变量值复制一份，转成int再参与计算
        System.out.println(c + 1.1);//把变量c保存的变量值复制一份，转成int再转成double,再参与计算
        System.out.println(c > c2);//比较的是底层编号
    }


    /**
     * 赋值运算时候会自动类型转换
     */
    @Test
    public void test2_2() {
        int i = '中';//把字符'中'的十进制编号自动转换成int类型后赋值给整型变量
        double i2 = '中';//把字符'中'的十进制编号自动转换成int类型,然后再转成double类型
        System.out.println(i);
        System.out.println(i2);

        System.out.println();
        char min = '\u0000';
        char max = '\uffff';
        int minNum = min;//自动类型转换
        int maxNum = max;//自动类型转换
        System.out.println(minNum);//0
        System.out.println(maxNum);//65535

        System.out.println();
        int minValue = Character.MIN_VALUE;//自动类型转换
        int maxValue = Character.MAX_VALUE;//自动类型转换
        System.out.println(minValue);
        System.out.println(maxValue);
    }



    /**
     *0~65535范围内的整数强转成char
     */
    @Test
    public void test2_3() {
        System.out.println((char)97);
        System.out.println((char)97L);
    }

    /**
     * cha类型变量强制转换成int类型
     */
    @Test
    public void test2_4() {
        int i = (int) '中';//等价于int i = '中';
        int i2 = (int) '\n';//等价于int i2 = '\n';
        int i3 = (int) '\t';//等价于int i3 = '\t';
        int i4 = (int) '\u0020';
        int i5=(int)Character.MIN_VALUE;//等价于 int i5=Character.MIN_VALUE;
        int i6=(int)Character.MAX_VALUE;//等价于 int i6=Character.MAX_VALUE;
        System.out.println(i);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        System.out.println(i5);
        System.out.println(i6);
    }


    /**
     * 生成6位随机字符串
     */
    @Test
    public void test2_8() {
        int tmpInt;
        char tmpChar;
        StringBuilder randomString=new StringBuilder();
        for (int i = 0; i < 6; i++) {
            tmpInt=(int)(Math.random()*26+97);
            tmpChar=(char)tmpInt;
            randomString=randomString.append(tmpChar);
        }
        System.out.println(randomString);
    }


    /**
     * 反斜杠\单独存在时是具有转义功能的转移
     * <p>
     * 情况1：
     * 反斜杠出现在特殊字符之前，会将特殊字符转成普通字符(或者理解为和后面的特殊字符组成一个新意义的字符); eg: \', \", \\;    ' " \  三个本来是具有特殊意义的字符，转义后 \' \" \\编程了三个普通字符
     * 两个反斜杠\\一起出现，那么第一个\是转义字符，会把第二个\转义成不具备转义功能的普通反斜杠字符，两个字符组成一个新意义的普通字符：普通反斜杠
     * <p>
     * 情况2：
     * 反斜杠出现在普通字符之前，会将普通字符转成具有特殊意义的字符； eg: \n, \t    n和t本来是不具备特殊意义的普通字符， 转移后\n \t编程了换行符和制表符
     */
    @Test
    public void test3() {
        System.out.println('\'');//字符类型单引号
        System.out.println("'");//字符串类型单引号
        System.out.println('"');//字符类型双引号
        System.out.println("\"");//字符串类型双引号
        System.out.println("\'hello world\'");
        System.out.println("\"hello world\"");

        //char c='\';   这里编译器会把\和后面的'一起配对，把后面的'转义成没有任何特殊含义的的普通点字符，导致整个字符好像少了一个结束单引号
        System.out.println('\\'); //这里第一个\是转义字符，会把第二个\转义成不具备转义功能的普通反斜杠字符,也就是\单独存在时是具有转移功能的反斜杠；java中两个\\代表一个普通反斜杠字符


    }


    /**
     * "\n"和'\n'是一样的,都表示一个回车符.不同得是"\n"的引号中还可以继续加别的内容,比如"123\n456"
     * 而'\n'单引号内不能再加别的内容,原因是单引号内只能有一个字符;而'\n'单引号内不能再加别的内容,原因是单引号内只能有一个字符
     * \为转义字符，转变了后面字符n的原义，两个符号组成了一个新的字符，代表换行
     * 另外 + 碰上字符串时候表示连接符，而不是运算符
     */

    @Test
    public void test4() {

        System.out.println("1==========================");
        //"\t和'\t'都表示一个制表符
        System.out.println("aa" + "\t" + "aa");
        System.out.println("bb" + '\t' + "bb");//println后面会输出一个换行
        System.out.println("cc\tcc");//cc-->空格-->cc，把\t看成一个字符
        //"\n"和'\n'都是换行符
        System.out.println("dd" + '\n' + "dd");
        System.out.println("ee" + "\n" + "ee");
        System.out.println("123\n456");//123-->换行-->456


        System.out.println("2==========================");
        System.out.println('d' + '\t' + 'd');//三个字符用+连接，这里的+是运算符,三个字符会自动转化成int类型数据进行运算
        System.out.println('e' + "\t" + 'e');//"\t"是字符串，所以这里的+是连接符
        System.out.println(12 + '\n');//int+char,首先转char换成int,再相加，这里+是运算符
        System.out.println(12 + "\n");//int+字符串，这里+是连接符
    }


}
