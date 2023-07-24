package com.demo.basic.operator;

import org.testng.annotations.Test;


/**
 * 关系运算符(Relational Operator)用于用来比较判断两个数值型变量/常量/表达式的大小或者是否相等
 * 当运算符对应的关系成立时，运算结果是 true，否则是 false
 * 关于相等比较： == 和 != 可以应用于基本数据类型和引用类型。两者本质上上比较的都是变量存储的变量值，只是这个值可能是字面值，可能是对象的引用(对象的地址)
 *            当用于引用类型比较时，比较的是对象地址(两个引用是否指向同一个对象);
 *            当用于基本数据类型比较，比较的是他们的字面值
 * 关于大小比较：>,>e=,<= 只能用于基本类型变量的比较，数值引用类型(数值包装类)会先自动拆箱成数值基本类型，然后再进行大小比较
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
     * 使用“==”时:
     * 对基本数据类型（如int,double等），比较的是变量存储的数值的大小
     * 对引用数据类型（如Integer,Double等）比较的是变量指向的对象在内存中存储的地址
     * 通过new创建的对象具有不同的堆地址
     */
    @Test
    public void testEqualsSign(){
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        System.out.println(a==b);
    }



    /**
     * 对引用数据类型比较的是变量指向的对象在内存中存储的地址
     * Integer.valueOf() 方法出于减少对象创建次数和节省内存的考虑,会对数值为-128 ~127之间的整数对象进行缓存，不包含小数
     * 在运行的时候如果整数对象在 -128-127 之间则返回缓存中的该对象，而不会重新生成对象，如果不在-128-127之间则会在堆中新创建一个对象。
     * Byte,Short,Integer,Long类型的缓存范围是[-128,127]
     */
    @Test
    public void testEqualsSign2(){
        Integer i1= Integer.valueOf(10);//在-128-127区间，直接返回缓存对象的引用引用
        Integer i2=Integer.valueOf(10);//在-128-127区间，直接返回缓存对象的引用引用
        System.out.println(i1==i2);//i1和i2实际上指向同一个缓存对象，也就是保存着同一个对象的引用

        Integer i3= Integer.valueOf(128);//超出-128-127区间，直接new一个新的对象
        Integer i4=Integer.valueOf(128);//超出-128-127区间，直接new一个新的对象
        System.out.println(i3==i4);//i3和i4指向不同的对象
    }



    /**
     * 对引用数据类型（如Integer,Double等）比较的是变量指向的对象在内存中存储的地址
     * Integer.valueOf() 方法出于减少对象创建次数和节省内存的考虑,会对数值为-128 ~127之间的整数对象进行缓存，不包含小数
     * 在运行的时候如果整数对象在 -128-127 之间则返回缓存中的该对象，而不会重新生成对象，如果不在-128-127之间则会在堆中新创建一个对象。
     * Byte,Short,Integer,Long类型的缓存范围是[-128,127]
     * 编译器帮助我们完成了自动装箱
     */
    @Test
    public void testEqualsSign3(){
        Long i1= 10L;//等价于 Integer i1= Integer.valueOf(10);
        Long i2=10L;//等价于 Integer i2= Integer.valueOf(10);
        System.out.println(i1==i2);

        Long i3= 128L;//等价于 Integer i3= Integer.valueOf(128);
        Long i4=128L;//等价于 Integer i4= Integer.valueOf(128);
        System.out.println(i3==i4);
    }


    /**
     * Double没有缓存，Double.valueOf()都是直接返回new Double (num);
     */
    @Test
    public void testEqualsSign4(){
        Double d1=Double.valueOf(100.0);
        Double d2=Double.valueOf(100.0);
        System.out.println(d1==d2);//false
    }



    /**
     * 若是基本数据类型和包装类进行大小比较，则包装类会自动拆箱，比较数值的大小
     */
    @Test
    public void testEqualsSign5(){
        int a = 200;
        Integer b = 200;
        System.out.println(a == b);//b自动拆箱
        System.out.println(a > b);//b自动拆箱
        System.out.println(a < b);//b自动拆箱

    }



    /**
     * 如果进行比较的两个操作数都是数值类型和字符类型，无论它们的数据类型是否相同，只要它们的值相等，也都将返回 true。
     */
    @Test
    public void testEqualsSign6(){
        int i=new Integer(97);
        long l=new Long(97);
        double d=new Double(97.0);
        System.out.println((i==l)&&(l==d));
    }


    /**
     * int和char比较，char类型参与大小比较或者算数运算时候会以编号身份参与
     * 这里会先自动类型转换：char--->int, 然后再比较
     */
    @Test
    public void testEqualsSign7(){
        char c='a';
        int i2=97;
        System.out.println(c==i2);
    }

    @Test
    public void testEqualsSign8() {
        //注意==和=
        boolean b = false;
        if (b = true){//赋值表达式的值是左边变量存储的值
            System.out.println("zhen.....");
        }
        else{
            System.out.println("jia...");
        }
    }


    /**
     * java也支持两个 boolean 类型的值进行比较
     */
    @Test
    public void testEqualsSign9(){
        //布尔直接量或者布尔字面量比较是否相同
        System.out.println("true==false: "+(true==false));
        System.out.println("true！=false: "+(true!=false));
    }
}
