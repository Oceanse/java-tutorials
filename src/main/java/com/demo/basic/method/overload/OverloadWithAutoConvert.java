package com.demo.basic.method.overload;

import org.testng.annotations.Test;

public class OverloadWithAutoConvert {

    void sum(int a, long b) {
        System.out.println("a method invoked");
    }

    void sum(long a, int b) {
        System.out.println("b method invoked");
    }

    /**
     * 如果在方法中没有匹配的类型参数，并且每个方法都会提升相同数量的参数，那么会出现歧义。如下示例代码，将会产生编译时错误。
     * @param args
     */
    public static void main(String args[]) {
        OverloadWithConstructor o = new OverloadWithConstructor();
        //o.sum(20, 20);// 这里可以将两个int类型的实参中的任意一个自动转换成long类型去匹配相应的函数，因此会给编译器带来困扰
    }




    public void showFood(String food){
        System.out.println("showFood(String food)");
        System.out.println(food);
    }

    public void showFood(String... foods){
        System.out.println("showFood(String... food)");
        for (String food : foods) {
            System.out.println(food);
        }
    }


    /**
     * 重载方法同时包含可变形参showFood(String... food)和showFood(String food)，
     * 那么showFood("noodles")只会调用单参方法showFood(String food)，也就是调用"更精确"的那个；
     * 如果想让showFood("noodles")调用变形参showFood(String... food)，可以传入单元素的数组showFood(new String[]{"noodles"})'
     * 另外不推荐重载可变形参的方法，没有太大意义，还会使得程序可读性变差
     */
    @Test
    public void test(){
        //showFood(String... food)
        showFood();

        //showFood(String food)
        showFood("noodles");

        //showFood(String... food)
        showFood("noodles","dumplings");

        //showFood(String... food)
        showFood(new String[]{"tomato"});
    }
}
