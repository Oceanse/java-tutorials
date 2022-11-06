package com.demo.basic.variable.variable_datatype;

import org.testng.annotations.Test;

public class BooleanDemo {

    boolean isContinue;
    Boolean flag;

    /**
     * 布尔类型成员变量默认值是false
     */
    @Test
    public void test() {
        System.out.println(isContinue);
        System.out.println(flag);
    }

    /**
     * 布尔String--->Boolean
     * "True","true","TrUe"等等通过Boolean.valueOf都会转成true
     * 其它字符串(包括False,false, null)都会转为false
     */
    @Test
    public void test2() {
        String s = "true";
        String s2 = "True";
        String s3 = "false";
        String s4 = "False";
        Boolean b = Boolean.valueOf(s);
        Boolean b2 = Boolean.valueOf(s2);
        Boolean b3 = Boolean.valueOf(s3);
        Boolean b4 = Boolean.valueOf(s4);
        System.out.println(b);//true
        System.out.println(b2);//true
        System.out.println(b3);//false
        System.out.println(b4);//false
        System.out.println(Boolean.valueOf("xxx"));//false
    }


    /**
     * String转成基本类型boolean
     * "True","true","TrUe"等等通过parseBoolean都会转成true
     * 其它字符串(包括null)都会转为false
     */
    @Test
    public void test3() {
        boolean b1 = Boolean.parseBoolean("true"); // true
        boolean b2 = Boolean.parseBoolean("TRUE"); // true
        boolean b3 = Boolean.parseBoolean("false"); // false
        boolean b4 = Boolean.parseBoolean("FALSE"); // false
        boolean b5 = Boolean.parseBoolean("others"); // false
        boolean b6 = Boolean.parseBoolean(""); // false
        boolean b7 = Boolean.parseBoolean(null); // false
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);
        System.out.println(b5);
        System.out.println(b6);
        System.out.println(b7);
    }


}
