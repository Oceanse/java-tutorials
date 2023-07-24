package com.demo.basic.variable.classification.array;

import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayReflect {
    /**
     * 通过反射创建数组:  public static Object newInstance(Class<?> componentType, int length)
     */
    @Test
    public void testArrayGeneratedByReflect() {
        Class<Integer> clazz = Integer.class;
        Integer[] array = (Integer[]) Array.newInstance(clazz, 10);
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }
        System.out.println(Arrays.toString(array));
    }


    private static Object resizeArray (Object oldArray, int newSize) {
        //获取数组oldArray的长度
        int oldSize = Array.getLength(oldArray);
        //获取数组oldArray的元素类型
        Class elementType = oldArray.getClass().getComponentType();
        //实例一个新的数组 类型和oldArray的一样 长度参数传入的newSize
        Object newArray = Array.newInstance(elementType,newSize);
        //得到新数组newArray 和oldArray两个中长度最短的，这里newSize可能小于原数组的长度
        int preserveLength = Math.min(oldSize,newSize);
        //数组内容复制
        if (preserveLength > 0) {
            System.arraycopy (oldArray, 0, newArray, 0, preserveLength);
        }
        return newArray;
    }
}
