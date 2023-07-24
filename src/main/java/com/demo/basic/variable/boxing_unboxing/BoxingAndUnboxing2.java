package com.demo.basic.variable.boxing_unboxing;

import org.testng.annotations.Test;


/**
 * Integer.valueOf() 方法出于减少对象创建次数和节省内存的考虑,会对数值为-128 ~127之间的整数对象进行缓存，不包含小数
 * Byte,Short,Integer,Long类型的缓存范围是[-128,127]
 */
public class BoxingAndUnboxing2 {

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

        Integer i3= Integer.valueOf(128);//超出-128-127区间，直接堆中new一个新的对象
        Integer i4=Integer.valueOf(128);//超出-128-127区间，直接堆中new一个新的对象
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
        Long i1= 10L;//等价于 Integer i1= Integer.valueOf(10);//使用缓存池中的对象
        Long i2=10L;//等价于 Integer i2= Integer.valueOf(10);//使用缓存池中的对象
        System.out.println(i1==i2);

        Long i3= 128L;//等价于 Integer i3= Integer.valueOf(128);直接堆中new一个新的对象
        Long i4=128L;//等价于 Integer i4= Integer.valueOf(128);直接堆中new一个新的对象
        System.out.println(i3==i4);
    }


    /**
     * Double没有缓存，Double.valueOf()都是直接返回new Double (num);
     */
    @Test
    public void testEqualsSign4(){
        Double d1=Double.valueOf(100.0);//直接堆中new一个新的对象
        Double d2=Double.valueOf(100.0);//直接堆中new一个新的对象
        System.out.println(d1==d2);//false
    }
}
