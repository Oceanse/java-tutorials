package com.demo.basic.variable.classification.enums.demo3;

import com.demo.basic.variable.classification.enums.demo2.ColorEnum;
import org.testng.annotations.Test;

import java.util.EnumMap;

/**
 * 使用场景：当key为枚举的时候使用，建议使用 EnumMap 代替 HashMap。
 */
public class EnumMapDemo {
    @Test
    public static void testEnumMap(){
        EnumMap<ColorEnum,String> enumMap = new EnumMap(ColorEnum.class);
        System.out.println(enumMap);
        enumMap.put(ColorEnum.RED,"红色");
        enumMap.put(ColorEnum.GREEN,"绿色");
        enumMap.put(ColorEnum.BLUE,"蓝色");
        System.out.println(enumMap);
        System.out.println(enumMap.get(ColorEnum.GREEN));
    }


    @Test
    public static void testEnumMap2(){
        EnumMap<ColorEnum,String> enumMap = new EnumMap(ColorEnum.class);
        ColorEnum[] values = ColorEnum.values();
        for (ColorEnum value : values) {
            enumMap.put(value, value.name());
        }
        System.out.println(enumMap);
        System.out.println(enumMap.get(ColorEnum.GREEN));
    }
}
