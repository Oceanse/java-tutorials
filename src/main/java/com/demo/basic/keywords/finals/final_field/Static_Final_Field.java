package com.demo.basic.keywords.finals.final_field;


public class Static_Final_Field {

    private static final Static_Final_Field instance=new Static_Final_Field();


    private Static_Final_Field() {
    }


    public static Static_Final_Field getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        //Math类中，圆周率被定义为静态常量,public static final double PI = 3.14159265358979323846;
        //System类中，out被定义为静态常量,public static final PrintStream out = null
        System.out.println("Area is: "+Math.PI*1*1);
        Static_Final_Field instance = Static_Final_Field.getInstance();

    }


}


interface Car{
    //这里实际上是public static final String brand=null;
    String BRAND=null; //成员常量必须初始化
}

class BMW implements Car{

    /**
     * 父接口成员常量已经初始化，这里不能再次初始化
     * @param brand
     */
    public BMW(String brand) {
        //BMW.BRAND=brand;//cannot assign a value to final variable brand
    }
}
