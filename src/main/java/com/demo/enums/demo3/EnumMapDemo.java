package com.demo.enums.demo3;

import com.demo.enums.demo2.Color;
import org.testng.annotations.Test;

import java.util.EnumMap;

public class EnumMapDemo {
    @Test
    public static void test(){
        EnumMap<Color,String> enumMap = new EnumMap(Color.class);
        System.out.println(enumMap);
        enumMap.put(Color.RED,"红色");
        enumMap.put(Color.GREEN,"绿色");
        enumMap.put(Color.BLUE,"蓝色");
        System.out.println(enumMap);
    }
}
