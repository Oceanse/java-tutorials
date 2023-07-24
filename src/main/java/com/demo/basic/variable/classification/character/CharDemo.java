package com.demo.basic.variable.classification.character;

import org.testng.annotations.Test;

/**
 * char类型变量表示形式：
 * 1 符号形式
 * A: 普通字符(单个符号): 'A', '1' '@'
 * B: 转义字符: 转义字符=转义符号\+被转义符号，比如：  '\t', '\n', '\\'， '\"', '\''  这里的每个转移字符\t，\n等对java编译器来说是单个符号或者字符，''内部只能存放单个字符
 * <p>
 * 2 数值形式(编号)
 * A 十进制    char a=0; char b=65535
 * B 十六进制  char c=0X0000, char d=0XFFFF
 * C 二进制    char e=0B0
 * <p>
 * 3 Unicode码表示一个char字符(类似于十六进制表示)：'\uffff', 其中f代表十六进制整数，取值范围： '\u0000'~'\uffff'
 * 对java编译器来说'\uffff'是单个符号或者字符
 * 这里四位十六进制整数相当于16位二进制整数，一共支持16x16x16x16=65536种字符,
 * <p>
 * <p>
 * <p>
 * java字符编码：UTF-16，所有char字符的编码统一使用unicode码点(0~65535)的16位二进制作为存储数值(不足用0补齐)，
 * 编号在0~255范围内的字符实际上可用单字节表示，在utf-16规则下，高位子节用0补齐
 * 简单来说就是字符编号就是其存储编码，因此char类型变量可以理解成字符或者字符对应的编号
 * <p>
 * <p>
 * java字符数值范围：双字节无符号十进制表示范围：0 ~65535;   十六进制--->0x0000~0xFFFF； 二进制--->16个0~16个1
 * char类型变量本质上也是数值类型变量，严格说是无符号整数变量，因此char变量参与加减乘除以及大小比较
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
    public void testCharDefinition() {
        //字符形式：普通字符和转移字符
        char c = '*';//普通字符：单个符号作为字符值
        char c2 = '\t'; //转移字符：转义字符作为字符值; 转义符号\+被转义符号=转移字符，转移字符对java编译器来说单个符号;

        //unicode码形式
        char c3 = '\u12ab'; //unicode码字符：'\uaaaa'作为字符值，其中a代表十六进制整数

        //数值形式：将一个在byte/short/char范围内的整数赋值给byte/short/char变量，那么系统会自动把这个数当成byte/short/char类型;
        char c4 = 65534;

        //字符a的不同表示方式
        char c5_0 = 'a';
        char c5_1 = 97;
        char c5_2 = 0B01100001; //十进制97，也就是字符'a'
        char c5_3 = 0X0061;//十进制97，也就是字符'a'
        char c5_4 = '\u0061';//这里必须是4位，不足高位用0补齐；十进制97，也就是字符'a'
        System.out.println("c5_0 = " + c5_0);
        System.out.println("c5_0 = " + c5_1);
        System.out.println("c5_0 = " + c5_2);
        System.out.println("c5_0 = " + c5_3);
        System.out.println("c5_0 = " + c5_4);
        System.out.println(c5_0 == c5_1 && c5_1 == c5_2 && c5_2 == c5_3 && c5_3 == c5_4);//编码或者编号相同

        //字符空格的不同表示方式
        char space = ' ';//英文半角空格，
        char space2 = 32;
        char space3 = '\u0020';//对应英文半角空格' '
        System.out.println("b" + space + "d");
        System.out.println("b" + space2 + "d");
        System.out.println("b" + space3 + "d");
        System.out.println("b\u0020d");
        System.out.println(space == space2 && space2 == space3);//编码或者编号相同
    }


    /**
     * The number of bits used to represent a char value in unsigned binary form(二进制形式)
     */
    @Test
    public void testCharSize(){
        int size = Character.SIZE;
        System.out.println(size);
    }


    /**
     * 双字节无符号十进制表示范围：0~65535
     * 最小值和最大值不是打印字符，所以强转成int类型打印
     */
    @Test
    public void testCharMinAndMax() {
        char min1 = 0;
        char min2 = 0B0000000000000000;//可以简写为0B0, 高位的0可以省略
        char min3 = 0X0000;//可以简写为0X0, 高位的0可以省略
        char min4 = '\u0000';//unicode码的大小可以关联十六进制进行推导
        char min5 = Character.MIN_VALUE;
        System.out.println("min1 = " + (int) min1);
        System.out.println("min2 = " + (int) min2);
        System.out.println("min3 = " + (int) min3);
        System.out.println("min4 = " + (int) min4);
        System.out.println("min5 = " + (int) min5);

        char max1 = 65535;
        char max2 = 0B1111111111111111;
        char max3 = 0Xffff;
        char max4 = '\uffff';//unicode码的大小可以关联十六进制进行推导
        char max5 = Character.MAX_VALUE;//自动类型转换
        System.out.println("max1 = " + (int) max1);
        System.out.println("max2 = " + (int) max2);
        System.out.println("max3 = " + (int) max3);
        System.out.println("max4 = " + (int) max4);
        System.out.println("max5 = " + (int) max5);
    }


    /**
     * 打印字符形式和数值形式
     */
    @Test
    public void testOutput() {
        //注意：数值也可以是字符类型，把一个字符类型范围内的整数赋值给一个字符变量，那么这个整数就是字符类型
        char letter = 65, letter2 = 97;
        //默认打印字符形式
        System.out.println("letter = " + letter);
        System.out.println("letter2 = " + letter2);

        //打印数值形式
        System.out.println("letter = " + (int) letter);
        System.out.println("letter2 = " + (int) letter2);
    }


    /**
     * 反斜杠\单独存在时是具有转义功能,可以把特殊字符转移成普通字符或者把普通字符转义成特殊字符
     * <p>
     * 情况1：特殊字符--->普通字符
     * 反斜杠出现在特殊字符之前，会将特殊字符转成普通字符(或者理解为和后面的特殊字符组成一个新意义的字符); eg: \', \", \\;    ' " \  三个本来是具有特殊意义的字符，转义后 \' \" \\编程了三个普通字符
     * 两个反斜杠\\一起出现，那么第一个\是转义字符，会把第二个\转义成不具备转义功能的普通反斜杠字符，两个字符组成一个新意义的普通字符：普通反斜杠
     * <p>
     * 情况2：普通字符--->特殊字符
     * 反斜杠出现在普通字符之前，会将普通字符转成具有特殊意义的字符； eg: \n, \t    n和t本来是不具备特殊意义的普通字符， 转移后\n \t编程了换行符和制表符
     */
    @Test
    public void testEscapeCharacter() {
        System.out.println('\'');//字符类型单引号
        System.out.println("\"");//字符串类型双引号，字符串就是由一个个字符构成，\"就是一个字符
        System.out.println("\'hello world\'");
        System.out.println("\"hello world\"");

        //char c='\';   这里编译器会把\和后面的'一起配对，把后面的'转义成没有任何特殊含义的的普通点字符，导致整个字符好像少了一个结束单引号
        System.out.println('\\'); //这里第一个\是转义字符，会把第二个\转义成不具备转义功能的普通反斜杠字符,也就是\单独存在时是具有转移功能的反斜杠；java中两个\\代表一个普通反斜杠字符
        System.out.println("C:\\ocean\\myDir"); //windows文件系统路径
    }


    /**
     * \为转义字符，转变了后面字符'n'的原义，把普通字符n转义成特殊字符'\n'，代表换行，在java编译器看来\n是一个字符
     * "\n"和'\n'都表示一个回车符.不同的是"\n"的引号中还可以继续加别的内容,比如"123\n456"， 而'\n'单引号内不能再加别的内容,原因是单引号内只能有一个字符;
     * 另外 + 碰上字符串时候表示连接符，而不是运算符
     */

    @Test
    public void testEscapeCharacter2() {

        System.out.println("1==========================");
        //"\t和'\t'都表示一个制表符，制表符一般显示为四个空格
        System.out.println("aa" + "\t" + "aa");//编译器优化后，应该是"aa\taa"
        System.out.println("bb" + '\t' + "bb");//编译器优化后，应该是"bb\tbb"
        System.out.println("cc\tcc");//cc-->空格-->cc，把\t看成一个字符
        //"\n"和'\n'都是换行符
        System.out.println("dd" + '\n' + "dd");
        System.out.println("ee" + "\n" + "ee");
        System.out.println("123\n456");

        System.out.println("2==========================");
        System.out.println('d' + '\t' + 'd');//三个字符用+连接，这里的+是运算符,三个字符会自动转化成int类型数据进行运算
        System.out.println('e' + "\t" + 'e');//"\t"是字符串，所以这里的+是连接符
        System.out.println(12 + '\n');//int+char,首先转char换成int,再相加，这里+是运算符
        System.out.println(12 + "\n" + 34);//int+字符串，这里+是连接符
    }


    /**
     * int char类型转换
     */
    @Test
    public void testTypeConversion() {
        int i = 1; //这里1是int类型变量
        char c = 1; //这里1是char类型变量，0~65535范围内的数值被赋值给char类型变量时会被编译器当做char类型变量值

        i = c; //char类型变量自动转换成int类型变量
        // c=i;   //int类型变量不能自动转换为char
        c = (char) i;// int类型变量强制转换为char,然后赋值给char类型变量c6, 注意这里墙砖的是变量i存储的变量值的副本

        //c=65536;  //char 只能存储0~65535， 当大于65535会将其视为int类型，而int类型变量不能自动转换为char
    }


    /**
     * char参与算数运算时候会自动转换成int类型或者跨越转换成其他类型
     * char大小比较本质比较的是字符的编号
     */
    @Test
    public void testTypeConversion2() {
        char c = 'a';
        char c2 = 'b';
        System.out.println(c + 1);
        System.out.println(c + 1.1);
        System.out.println(c%2);
        System.out.println(c > c2);//通过底层编号参与运算
        System.out.println(c2 - c);//通过底层编号参与运算
    }


    /**
     * 赋值运算时候会自动类型转换成int类型或者跨越转换成其他类型
     */
    @Test
    public void testTypeConversion3() {
        int i = '中';//把字符'中'的十进制编号自动转换成int类型后赋值给整型变量
        double i2 = '中';//把字符'中'的十进制编号自动转换成int类型,然后再转成double类型或者理解为跨越转换
        System.out.println(i);
        System.out.println(i2);
    }


    /**
     * 参数值传递时候会自动类型转换成int类型或者跨越转换成其他类型
     */
    @Test
    public void testTypeConversion4() {
        int result = addOne('a');
        System.out.println(result);
    }

     int addOne(int i){
        return i+1;
    }


    /**
     * 返回值自动类型转换
     */
    @Test
    public void testTypeConversion5() {
        int result = getCodePoint('a');
        System.out.println(result);
    }

    int getCodePoint(char c){
        return c;
    }

    /**
     * cha类型变量强制转换成int类型，也就是获取其十进制编号
     */
    @Test
    public void testTypeConversion6() {
        int i = (int) '中';//等价于int i = '中';
        int i2 = (int) '\n';//等价于int i2 = '\n';
        int i3 = (int) '\t';//等价于int i3 = '\t';
        int i4 = (int) '\u0020';// int i4 = '\u0020';
        int i5 = (int) Character.MIN_VALUE;//等价于 int i5=Character.MIN_VALUE;
        int i6 = (int) Character.MAX_VALUE;//等价于 int i6=Character.MAX_VALUE;
        System.out.println(i);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(i4);
        System.out.println(i5);
        System.out.println(i6);
    }

    /**
     * int类型变量强制转换成char类型
     * 0~65535范围内的整数强转成char
     * 生成6位随机字符串
     */
    @Test
    public void testTypeConversion7() {
        int tmpInt;
        char tmpChar;
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            tmpInt = (int) (Math.random() * 26 + 97);
            tmpChar = (char) tmpInt;
            randomString = randomString.append(tmpChar);
        }
        System.out.println(randomString);
    }

}
