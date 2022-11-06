package com.demo.basic.operator;

import org.testng.annotations.Test;


/**
 * 关系运算符(Relational Operator)用于用来比较判断两个数值型变量/常量/表达式的大小或者是否相等
 * 当运算符对应的关系成立时，运算结果是 true，否则是 false
 * 关于相等比较： == 和 != 可以应用于基本数据类型和引用类型。两者本质上上比较的都是变量存储的变量值，只是这个值可能是字面值，可能是对象的引用(对象的地址)
 *            当用于引用类型比较时，比较的是对象地址(两个引用是否指向同一个对象);
 *            当用于基本数据类型比较，比较的是他们的字面值
 * 关于大小比较：>,>=,<= 只能用于基本类型变量的比较，数值引用类型(数值包装类)会先自动拆箱成数值基本类型，然后再进行大小比较
 *
 *
 * */
public class RelationalOperatorDemo {


    /**
     * 基本类型变量值大小相等比较
     */
    @Test
    public void test(){
        int a = 10;
        int b = 20;
        System.out.println("a == b = " + (a == b) );
        System.out.println("a != b = " + (a != b) );
        System.out.println("a > b = " + (a > b) );
        System.out.println("a < b = " + (a < b) );
        System.out.println("b >= a = " + (b >= a) );//大于或者等于
        System.out.println("b <= a = " + (b <= a) );//小于或者等于
    }



    /**
     * 如果两个操作数都是引用类型，只有当两个引用变量是相同类型或者具有父子关系时才可以比较，并且比较的是两个变量存储的地址
     */
    @Test
    public void test2(){
        Integer i1=new Integer(20);
        Integer i2=new Integer(10);
        System.out.println(i1==i2);
        System.out.println(i1!=i2);
    }


    /**
     * 如果两个操作数都是引用类型，只有当两个引用变量是相同类型或者具有父子关系时才可以比较，并且比较的是两个变量存储的地址,只要两个引用指向的不是同一个对象就会返回 true。
     */
    @Test
    public void test2_2(){
        Integer i1=new Integer(20);
        Long i2=new Long(10);
        //System.out.println(i1==i2);  //编译不通过
    }



    /**
     * 如果进行比较的两个操作数都是数值类型和字符类型，无论它们的数据类型是否相同，只要它们的值相等，也都将返回 true。
     */
    @Test
    public void test2_3(){
        int i=new Integer(97);
        long l=new Long(97);
        System.out.println(i==l);

    }


    /**
     * int和char比较，char类型参与大小比较或者算数运算时候会以编号身份参与
     * 这里会先自动类型转换：char--->int, 然后再比较
     */
    @Test
    public void test2_4(){
        char c='a';
        int i=97;
        System.out.println(c==i);
    }


    /**
     * java也支持两个 boolean 类型的值进行比较
     */
    @Test
    public void test2_5(){
        //布尔直接量或者布尔字面量比较是否相同
        System.out.println("true==false: "+(true==false));
        System.out.println("true==false: "+(true!=false));
    }


    @Test
    public void test3() {
        //注意==和=
        boolean b = false;
        if (b = true){//赋值表达式的值是左边变量存储的值
            System.out.println("zhen.....");
        }
        else{
            System.out.println("jia...");
        }
    }

}
