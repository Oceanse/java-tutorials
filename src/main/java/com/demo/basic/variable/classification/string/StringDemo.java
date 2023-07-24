package com.demo.basic.variable.classification.string;

import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 对象性：String是一个引用类型，它本身也是一个class；String类型变量通常保存的是某个字符串的地址(堆内存地址或者常量池地址)，由于是引用类型变量，所以也可以保存null这个特殊的引用类型的变量值
 *
 * 不可变(immutable)：字符串对象一旦被创建，其内容是不可变的
 *
 * 底层存储：
 * jdk8及以前：final char[] value用于存储字符串数据，一个char采用utf-16需要占用两个字节的存储单位
 * The current implementation of the String class stores characters in a char array, using two bytes (sixteen bits) for each character.
 *
 * JDK9：byte[]用于存储字符串数据。
 * JDK9 是基于 ISO/latin1/Utf-16  ,latin1 和 ISO 占用一个 byte 标识，UTF-16 占用两个 byte ，JDK9 会自动识别用哪个编码
 * 据说是JDK的开发人员经过调研了成千上万的应用程序的heap dump信息，然后得出了一个结论：大部分的String都是以Latin-1字符编码来表示的，只需要一个字节存储就够了，两个字节完全是浪费。
 * 于是在JDK9之后，字符串的底层存储变成了byte[]，节省了很多内存空间。
 * We propose to change the internal representation of the String class from a UTF-16 char array to a byte array plus an encoding-flag field.
 * The new String class will store characters encoded either as ISO-8859-1/Latin-1 (one byte per character), or as UTF-16 (two bytes per character),
 * based upon the contents of the string.
 */
public class StringDemo {

    /**
     * 字符串四种构造方式
     */
    @Test
    public void testStringConstruct() throws UnsupportedEncodingException {
        String names = "Tom";//这里在常量池创建了一个字符串对象， 栈中的局部变量保存着常量池对象的地址
        String color = new String("red");//创建了两个对象：先创建常量池字符串对象，再创建堆内存对象，堆内存对象中保存着常量池字符串对象的地址
        String letter = new String(new char[]{'x', 'y', 'z'});//String(char value[])， 字符串底层就是通过一个char[]数组进行存储
        String number = new String(new byte[]{65,97}, StandardCharsets.US_ASCII);//解码
        System.out.println(names);
        System.out.println(color);
        System.out.println(letter);
        System.out.println(number);
    }


    /**
     * 字符串不可变性immutable
     */
    @Test
    public void testStringImmutable() {
        String s = "Hello";
        s.toUpperCase();//这里不是直接改变"Hello"的内容，而是常量池中生成一个新的字符串,只是没有引用指向这个常量池中的字符串对象
        System.out.println(s);
    }

    /**
     * 字符串不可变性immutable
     */
    @Test
    public void testStringImmutable2() {
        //这里fruits中的数组元素变量保存的实际上是常量池中字符串的地址
        String[] fruits = new String[]{"apple", "orange", "banana"};
        //相当于把每个数组元素的值传递给给新的变量fruit，这里数组元素的值个人理解是常量池中字符串的地址，
        //也就是把常量池中字符串的地址赋值给了新的变量fruit，临时变量fruit和数组元素变量两个句柄指向常量池中的同一个字符串对象
        for (String fruit : fruits) {
            //虽然新的变量fruit和数组元素变量两个句柄指向常量池中的同一个字符串，但是String是不可变类，也就是常量池中的字符串内容不可改变
            //所以当其中一个句柄试图修改字符串内容时候，本质是会创建一个新的字符串，但是两个句柄会仍然指向修改之前的字符串
            //所以这里fruit.toUpperCase()之后，fruit将会指向新的字符串对象，数组元素还是指向原先的常量池中的字符串对象，因此原数组元素的值不会变
            fruit.toUpperCase();
        }
        //fruits数组元素本身没有改变
        for (String fruit : fruits) {
            System.out.print(fruit+" ");
        }
    }
    /**
     * 详见内存分配图
     */
    @Test
    public void testStringEquals() {
        //== 是operator,比较的是两个变量中存储的值
        //常量池中相同的字符串直接量只有一个， 不会存在多个副本，自然str1和str2的引用就是相同的。
        String str1 = "cat";//str1存放"cat"在常量池中的地址
        String str2 = "cat";//str2存放"cat"在常量池中的地址
        System.out.println("str1.equals(str2): " + str1.equals(str2));
        System.out.println("str1 == str2: " + (str1 == str2));
        System.out.println();

        //equals()是一个方法
        //Object中equals用于判断两个变量是否是对同一个对象的引用,String类中对equals()进行了override,比较的是两个字符串的content
        String str3 = "cats";//str3存放"cats"在常量池中的地址
        String str4 = new String("cats");//str4存放堆内存变量的地址，这个堆内存变量保存着"cats"在常量池中的地址
        System.out.println("str3.equals(str4): " + str3.equals(str4));
        System.out.println("str3 == str4: " + (str3 == str4));
        System.out.println("str3 == str4.intern(): " + (str3 == str4.intern()));//执行intern操作时，如果常量池已经存在该字符串，则直接返回常量池中的字符串对象
        System.out.println();

        String str5 = "abc" + "def"; //编译时合并成String str5 = "abcdef";
        String str6 = "abcdef";
        System.out.println("str5.equals(str6): " + str5.equals(str6));
        System.out.println("str5 == str6: " + (str5 == str6));
        System.out.println();


        //待理解
//        String s7 = "hello";
//        String s8 = "HELLO".toLowerCase();//
//        System.out.println("s7.equals(s8): "+s7.equals(s8));
//        System.out.println("s7==s8: "+(s7 == s8));

       /* //待理解
        System.out.println("4===============");
        String str11="abc";
        String str22="def";
        String str33=str11+str22;
        String str44="abc"+"def";
        System.out.println(str33.equals(str44));
        System.out.println(str33==str44);*/
    }



    /**
     * 对象性： 有自己的方法
     * 常用方法
     */
    @Test
    public void testStringAPI() {
        String str = "nbacba_$!@-123nbaCBA";
        System.out.println("str.length(): " + str.length());
        System.out.println("first character: " + str.charAt(0));
        System.out.println("last character: " + str.charAt(str.length() - 1));
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i)+" ");
        }
        System.out.println();
        System.out.println();

        System.out.println("str.indexOf('a'): " + str.indexOf('a'));//字符a第一次出现的index
        System.out.println("str.lastIndexOf('a'): " + str.lastIndexOf('a'));//字符a最后一次出现的index
        System.out.println("str.indexOf(\"nba\"): " + str.indexOf("nba"));//字符串"ab"第一次出现的index
        System.out.println("str.lastIndexOf(\"nba\"): " + str.lastIndexOf("nba"));//字符串"ab"最后一次出现的index
        System.out.println("str.IndexOf('('): " + str.indexOf('('));//字符串不包含此字符则返回-1
        System.out.println("str.IndexOf(\"()\"): " + str.lastIndexOf("()"));//字符串不包含此子字符串则返回-1
        System.out.println();

        String str2 = "ab\nCDab";
        System.out.println("str2.startsWith(\"ab\"): " + str2.startsWith("ab"));
        System.out.println("str2.endsWith(\"ab\"): " + str2.endsWith("ab"));
        System.out.println("str2.contains(\"\\n\"): " + str2.contains("\n"));
        System.out.println("str2.contains(\"ab\")： " + str2.contains("ab"));
        System.out.println();

        //字符串内容不可改变，任何改变字符串内容的行为本质上都是生成新的字符串
        //大小写转换
        System.out.println("str.toUpperCase(): " + str.toUpperCase());
        System.out.println("str.toUpperCase(): " + str.toLowerCase());
        System.out.println();

        //trim()去除首尾空白字符,空白字符包括" "，\t，\n, \r, 本质是生成新的字符串
        String str3 = "\tabc\t";
        System.out.println("head" + str3 + "tail");
        System.out.println("head" + str3.trim() + "tail");
        String str4 = "abc\r";
        System.out.println(str4.trim() + "tail");
    }



    /**
     * replace的参数是char和CharSequence，即可以支持字符的替换，也支持字符串的替换并且是全部替换
     * 关于字符串替换replaceAll与replace()替换字符串的效果是一样，都是全部替换，但是replaceAll()支持正则表达式的替换
     */
    @Test
    public void testReplace() {
        String str = "11aaabbCCDDaa22";

        //新字符替换全部的旧字符
        System.out.println("str.replace('a', 'A'): " + str.replace('a', 'A'));

        //新字符串替换全部旧字符串
        System.out.println("str.replace(\"aa\", \"AA\"): " + str.replace("aa", "AA"));//String replace(CharSequence target, CharSequence replacement)，其中String implemwnt CharSequence

        //新字符串替换全部旧字符串
        System.out.println("str.replaceAll(\"aa\", \"AA\"): " + str.replaceAll("aa", "AA"));//String replaceAll(String regex, String replacement)

        System.out.println("str.replaceAll(\"\\\\d\", \"*\"): " + str.replaceAll("\\d", "*")); //把一个字符串所有的数字字符都换成星号; 正则表达式中\d代表的意思是匹配数字
        System.out.println("str.replaceAll(\"\\\\w\", \"*\"): " + str.replaceAll("\\w", "#")); //把一个字符串所有的字母及数字字符都换成星号; 正则表达式中\w代表的意思是匹配大小写字母数字和下划线 相当于([0-9a-zA-Z])
    }


    @Test
    public void testSplit() {
        String firstName = new String[]{"haiyang,pan"}[0];
        String lastName = new String[]{"haiyang,pan"}[1];

        String str = "Z-a-bcde ";
        String[] split = str.split("-");
        System.out.println(str.split("-")[0]);
        System.out.println(str.split("-")[1]);
        System.out.println(str.split("-")[2]);
        System.out.println(Arrays.toString(split));
        System.out.println();

        //split with dot
        String str2 = "pom.xml";
        String[] split1 = str2.split(".");//It doesn't work
        System.out.println("split1:"+Arrays.toString(split1));
        System.out.println();

        //"." is a special character in java regex engine, so you have to use "\\." to escape this character:
        String[] split2 = str2.split("\\.");//这里暂时理解为正则表达式使用普通反斜杠来转义特殊字符
        System.out.println("split2:"+Arrays.toString(split2));
        System.out.println();

        //use "\\|" to escape this character:
        String str3 = "a|b|c";
        String[] split3 = str3.split("\\|");
        System.out.println("split3:"+Arrays.toString(split3));
    }


    @Test
    public void testSubstring() {
        System.out.println("abcdefg".substring(1));
        System.out.println("asdgmklrt".substring(1, 3));//substring(int beginIndex, int endIndex)     [beginIndex,endIndex)
        String filePath="/a/b/file.txt";
        String dirPath=filePath.substring(0,filePath.lastIndexOf('/')+1);//父路径
        String fileFullName=filePath.substring(filePath.lastIndexOf('/')+1);//文件全名
        String extensionName=filePath.substring(filePath.lastIndexOf('.')+1);//扩展名
        String fileName=filePath.substring(filePath.lastIndexOf('/')+1, filePath.lastIndexOf('.'));//文件名
        System.out.println("dirPath = " + dirPath);
        System.out.println("fileFullName = " + fileFullName);
        System.out.println("extensionName = " + extensionName);
        System.out.println("fileName = " + fileName);
    }


    /**
     * isEmpty()  字符串长度等于0，返回true; 否则返回false
     *
     */
    @Test
    public void testEmptyAndNull() {
        //String str="" 是一个空串(str(存在栈内存)指向堆内存空间，但是堆空间值为空)，空串不是null
        //String str2=null 会对引用变量str(存在栈内存)赋值为null,但是不会开辟堆空间
        String str = "";
        String str2 = null;

        System.out.println("str.length(): " + str.length());
        System.out.println("str.isEmpty(): " + str.isEmpty());
        System.out.println("str == null: " + (str == null));

        System.out.println("str2 == null: " + (str2 == null));
        System.out.println("str2.isEmpty(): " + str2.isEmpty());//NPE
        System.out.println("str2.length(): " + str2.length());//NPE
    }



    /**
     * string.format(String format, Object... args)函数来生成具有特定格式的字符串, 函数的第一个参数是格式,
     * 之后是对应格式中每个代号的各种数据,使得产生的长字符串可读性大大提高了
     * <p>
     * If there are more arguments than format specifiers, the extra arguments are ignored
     * 参数多余格式化控制符，多余的参数将会被忽略
     * <p>
     * %s - 接受一个字符串
     * %d - 接受整数类型（十进制）
     * %c - 接受字符类型（十进制）
     */
    @Test
    public void testFormat() {

        //两个%s可以将后面的两个参数格式化(%s和后面的参数一一对应)
        String key="name";
        String value="ocean";
        String out = String.format("-D%s = %s good luck", key, value);
        System.out.println(out);

        //多余的参数将会被忽略
        String out2 = String.format("name=%s", "ocean", "qq");
        System.out.println(out2);

        String out3 = String.format("字母c的大写=%c, ascii码值=%d", 'C',99);
        System.out.printf(out3);
    }


    /**
     * String<--->数组相互转换
     * java只支持字符串和字符/字节数组的相互转换
     */
    @Test
    public void testStringAndArray() throws UnsupportedEncodingException {
        String str = "goodluck海阳";

        //String转成char数组,字符串内部是通过一个char[]数组表示
        char[] chars = str.toCharArray();
        System.out.println(Arrays.toString(chars));
        //char数组转成String
        System.out.println(new String(chars));
        System.out.println();

        //String转成byte数组,本质是对字符串进行编码，就是把每个字符转成对应的编号(二进制/十进制。。。)
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);//utf-8,一个中文字符占用空间要超过3个字节
        System.out.println(Arrays.toString(bytes));
        //byte数组转成String， 本质是解码还原字符串
        System.out.println(new String(bytes,StandardCharsets.UTF_8));
    }



    /**
     * 其他类型====>String类型:
     *
     * 1 String.valueOf()
     * 2 追加空串"",此时+是连接符
     * 3 引用类型 toString()
     */
    @Test
    public void testValueOf() {
        //String valueOf(Object obj){ return (obj == null) ? "null" : obj.toString();}
        //int、double、boolean、char、float、long、Object类型数据转换成String
        System.out.println(String.valueOf(12.0f));
        System.out.println(String.valueOf('c'));
        System.out.println(String.valueOf(true));
        System.out.println(String.valueOf(new Object()));
        System.out.println(123+"");//ctrl+点击println会发现println接受的字符串类型变量
    }


    /**
     * 数值字符串转换为基本类型/包装类
     */
    @Test
    public void testStringToPrimitive() {
        //String转成int,参数必须是整数字符串，否则会产生NumberFormatException
        int n1 = Integer.parseInt("123"); // 123

        //String转成Integer，参数必须是整数字符串，否则会产生NumberFormatException
        Integer n2 = Integer.valueOf("123"); // 123
    }


    /**
     * A thread-safe, mutable sequence of characters
     */
    @Test
    public void testStringBuffer() {
        StringBuffer sb = new StringBuffer();
        sb.append("123").append("abc");
        System.out.println(sb);
        sb.reverse();
        System.out.println(sb);
    }

    /**
     *  A mutable sequence of characters
     *  可变 高效
     */
    @Test
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append("abc").append("123");
        System.out.println(sb);
        sb.reverse();
        System.out.println(sb);
    }


    @Test
    public void testStringCompareTo() {
        int r1 = "abc".compareTo("abe");//前小后大
        int r2 = "abc".compareTo("abc");//前后相等
        int r3 = "abd".compareTo("abc");//前大后小
        System.out.println("r1 = " + r1);//-2
        System.out.println("r2 = " + r2);//0
        System.out.println("r3 = " + r3);//1

        int r4 = Integer.valueOf(1).compareTo(Integer.valueOf(3));//结果只能是-1,0,1
        System.out.println("r4 = " + r4);

        int r5 = Integer.compare(1, 3);//结果只能是-1,0,1
        System.out.println("r5 = " + r5);
    }

}
