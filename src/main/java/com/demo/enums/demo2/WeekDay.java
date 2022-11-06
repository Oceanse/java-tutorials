package com.demo.enums.demo2;


import org.testng.annotations.Test;

public enum WeekDay {
    //如果枚举类定义了成员变量，则需要提供一个带参构造方法为该成员变量赋值，此时需要在常量名后使用“( )”括起参数,这里相当于public static final MONDAY=new MONDAY("星期一", 1)
    //常量的定义需要放在前头，之后才能是成员变量、构造方法、方法等的定义; 也就是必须先定义实例，不能将字段或方法定义在实例前面
    MONDAY("星期一", 1), //public static final MONDAY=new WeekDay("星期一", 1)
    TUESDAY("星期二", 2),
    WEDNESDAY("星期三", 3),
    THURSDAY("星期四", 4),
    //如果要为 enum 定义方法，那么必须在 enum 的最后一个实例尾部添加一个分号。
    FRIDAY("星期五", 5);


    //成员变量：枚举类成员变量的定义与类成员变量的定义相同。由于枚举类这一结构的初衷是设置常量，建议把枚举类成员变量都定义为私有变量，且只提供对应的getter方法，不提供对应的setter方法。
    private String desc;//文字描述
    private Integer code; //对应的代码

    /**
     * 构造方法：枚举类不支持通过程序来创建新实例，其构造方法的访问限制符只能为private，或者不加访问限制符
     * @param desc
     */
    private WeekDay(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    /**
     * 定义方法,返回描述,跟常规类的定义没区别
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 定义方法,返回代码,跟常规类的定义没区别
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "WeekDay{" +
                "desc='" + desc + '\'' +
                ", code=" + code +
                '}';
    }



    @Test
    public static void test(){
        WeekDay[] days = WeekDay.values();
        for (WeekDay day : days) {
            System.out.println(day.ordinal()+"："+ day.name());
        }
    }



    @Test
    public static void test2(){
        WeekDay[] days = WeekDay.values();
        for (WeekDay day : days) {
            System.out.println(day.getCode()+"："+ day.getDesc());
        }
    }


    @Test
    public static void test3(){
        WeekDay[] days = WeekDay.values();
        for (WeekDay day : days) {
            System.out.println(day.toString());
        }
    }






    public static void main(String[] args) {
        test3();
    }
}