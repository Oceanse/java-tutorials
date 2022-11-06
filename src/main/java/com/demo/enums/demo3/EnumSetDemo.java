package com.demo.enums.demo3;

import com.demo.enums.demo2.Color;
import com.demo.enums.demo2.WeekDay;
import org.testng.annotations.Test;

import java.util.EnumSet;
import java.util.Iterator;

public class EnumSetDemo {

    /**
     *allOf: 创建一个包含枚举类的所有枚举值的集合
     */
    @Test
    public static void test(){
        //创建一个包含WeekDay枚举类的所有枚举值的集合
        EnumSet<WeekDay> enumSet=EnumSet.allOf(WeekDay.class);
        System.out.println(enumSet);

        //创建一个包含Color枚举类的所有枚举值的集合
        EnumSet<Color> enumSet2=EnumSet.allOf(Color.class);
        System.out.println(enumSet2);
    }


    /**
     *noneOf创建一个空的EnumSet
     */
    @Test
    public static void test2(){
        EnumSet<WeekDay> enumSet=EnumSet.noneOf(WeekDay.class);
        System.out.println(enumSet);

        enumSet.add(WeekDay.MONDAY);
        enumSet.add(WeekDay.WEDNESDAY);
        enumSet.add(WeekDay.FRIDAY);
        System.out.println(enumSet);
    }


    /**
     *of创建一个包含指定枚举值的EnumSet
     */
    @Test
    public static void test3(){
        EnumSet<WeekDay> enumSet=EnumSet.of(WeekDay.TUESDAY,WeekDay.THURSDAY,WeekDay.MONDAY);
        System.out.println(enumSet);
        enumSet.add(WeekDay.WEDNESDAY);
        enumSet.add(WeekDay.FRIDAY);
        System.out.println(enumSet);
    }

    /**
     * EnumSet range(E from,E to): 创建一个枚举集,其中包含由参数中指定范围定义的元素
     */
    @Test
    public static void test4(){
        EnumSet<WeekDay> enumSet=EnumSet.range(WeekDay.TUESDAY,WeekDay.THURSDAY);
        System.out.println(enumSet);

        //产生异常
        EnumSet<WeekDay> enumSet2=EnumSet.range(WeekDay.FRIDAY,WeekDay.THURSDAY);
        System.out.println(enumSet2);
    }

    /**
     * 便利
     */
    @Test
    public static void test5(){
        //创建一个包含WeekDay枚举类的所有枚举值的集合
        EnumSet<WeekDay> weekDays=EnumSet.allOf(WeekDay.class);

        for (WeekDay weekDay : weekDays) {
            System.out.println(weekDay);
        }
        System.out.println();

        Iterator<WeekDay> iterator = weekDays.iterator();
        while (iterator.hasNext()){
            WeekDay weekDay = iterator.next();
            System.out.println(weekDay);
        }
    }


    @Test
    public static void test6(){
        //创建一个包含WeekDay枚举类的所有枚举值的集合
        EnumSet<WeekDay> weekDays=EnumSet.allOf(WeekDay.class);
        //复制
        EnumSet<WeekDay> weekDays2 = EnumSet.copyOf(weekDays);
        System.out.println("weekDays = " + weekDays);
        System.out.println("weekDays2 = " + weekDays2);
        System.out.println(weekDays.equals(weekDays2));
        System.out.println(weekDays==weekDays2);
    }
}
