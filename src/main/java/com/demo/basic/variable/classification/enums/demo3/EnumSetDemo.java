package com.demo.basic.variable.classification.enums.demo3;

import com.demo.basic.variable.classification.enums.demo2.ColorEnum;
import com.demo.basic.variable.classification.enums.demo2.WeekDayEnum;
import org.testng.annotations.Test;

import java.util.EnumSet;
import java.util.Iterator;

public class EnumSetDemo {

    /**
     *allOf: 创建一个包含枚举类的所有枚举值的集合
     */
    @Test
    public static void testAllOf(){
        //创建一个包含Color枚举类的所有枚举值的集合
        EnumSet<ColorEnum> enumSet2=EnumSet.allOf(ColorEnum.class);
        System.out.println(enumSet2);

        //创建一个包含WeekDay枚举类的所有枚举值的集合
        EnumSet<WeekDayEnum> weekDays=EnumSet.allOf(WeekDayEnum.class);
        for (WeekDayEnum weekDay : weekDays) {
            System.out.println(weekDay);
        }
        System.out.println();
        Iterator<WeekDayEnum> iterator = weekDays.iterator();
        while (iterator.hasNext()){
            WeekDayEnum weekDay = iterator.next();
            System.out.println(weekDay);
        }
    }


    /**
     *noneOf创建一个空的EnumSet
     */
    @Test
    public static void testNoneOf(){
        EnumSet<WeekDayEnum> enumSet=EnumSet.noneOf(WeekDayEnum.class);
        System.out.println(enumSet);
        enumSet.add(WeekDayEnum.MONDAY);
        enumSet.add(WeekDayEnum.WEDNESDAY);
        enumSet.add(WeekDayEnum.FRIDAY);
        System.out.println(enumSet);
    }


    /**
     *of创建一个包含指定枚举值的EnumSet
     */
    @Test
    public static void testOf(){
        EnumSet<WeekDayEnum> enumSet=EnumSet.of(WeekDayEnum.TUESDAY, WeekDayEnum.THURSDAY, WeekDayEnum.MONDAY);
        System.out.println(enumSet);
        enumSet.add(WeekDayEnum.WEDNESDAY);
        enumSet.add(WeekDayEnum.FRIDAY);
        System.out.println(enumSet);
    }

    /**
     * EnumSet range(E from,E to): 创建一个枚举集,其中包含由参数中指定范围定义的元素
     */
    @Test
    public static void tesRange(){
        EnumSet<WeekDayEnum> enumSet=EnumSet.range(WeekDayEnum.TUESDAY, WeekDayEnum.THURSDAY);
        System.out.println(enumSet);

        //产生异常
        EnumSet<WeekDayEnum> enumSet2=EnumSet.range(WeekDayEnum.FRIDAY, WeekDayEnum.THURSDAY);
        System.out.println(enumSet2);
    }

    @Test
    public static void testCopyOf(){
        //创建一个包含WeekDay枚举类的所有枚举值的集合
        EnumSet<WeekDayEnum> weekDays=EnumSet.allOf(WeekDayEnum.class);
        //复制
        EnumSet<WeekDayEnum> weekDays2 = EnumSet.copyOf(weekDays);
        System.out.println("weekDays = " + weekDays);
        System.out.println("weekDays2 = " + weekDays2);
        System.out.println(weekDays.equals(weekDays2));
        System.out.println(weekDays==weekDays2);//false
    }
}
