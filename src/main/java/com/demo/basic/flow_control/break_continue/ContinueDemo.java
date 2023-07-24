package com.demo.basic.flow_control.break_continue;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * continue是默认跳过本次循环continue后面剩下的语句，接着进行下一次循环，并不会终止循环
 * continue后也可以紧跟一个标签，可以跳过标签所标识循环的的剩下语句，重新开始下一次循环，或者简单理解为直接跳转到指定标签后面的循环起点
 * java中使用比起前的唯一理由就是在嵌套循环的场景下，向从多层嵌套中进行break或者continue
 * break和continue可以作为单独的java 语句，和标签结合使用起到类似goto的跳转作用
 * @author epanhai
 */
public class ContinueDemo {

    @Test
    public void testContinue() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue;//i是偶数时候跳过本次循坏的剩余代码(continue后面剩下的语句)，然后进行下一次循环,也就是跳转到当前循环的开始
            }
            System.out.println(i);
        }
    }


    /**
     * continue是跳过本次循环continue后面剩下的语句，接着进行下一次循环
     * 所以continue放在最后是没有意义的，因为这相当于没有忽略任何语句
     */
    @Test
    public void testContinue2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if (i % 2 == 0) {
                //i是偶数时候跳过本次循坏的剩余代码(continue后面剩下的语句)，然后进行下一次循环
                //continue放在最后是没有意义的，因为这相当于没有忽略任何语句
                continue;
            }
        }
    }

    @Test
    public void testContinue3() {
        List<String> all = Arrays.asList("A.java", "B.java", "A.class", "B.class", "a.c");
        List<String> javaSourceFile = new ArrayList<>();
        List<String> javaClassFile = new ArrayList<>();
        List<String> cSourceFile = new ArrayList<>();
        //if语句块中只包含一条语句，可以省略大括号,从编程规范角度不要省略大括号，省略大括号会使程序的可读性变差。
        for (String item : all) {
            if (item.endsWith("java")) {
                javaSourceFile.add(item);
                continue;
            }
            if (item.endsWith("class")) {
                javaClassFile.add(item);
                continue;
            }
            if (item.endsWith("c")) {
                cSourceFile.add(item);
                continue;//unnecessary
            }
        }
        System.out.println("javaSourceFile=" + javaSourceFile);
        System.out.println("javaClassFile=" + javaClassFile);
        System.out.println("cSourceFile=" + cSourceFile);
    }


    /**
     * 一般的continue会跳过剩余语句，直接跳转到当前循环的开始
     * 带标签的continue会跳过剩余语句，直接跳转到标签的位置，然后进入到标签后面的循环
     */
    @Test
    public void testContinue4(){
       outer: for (int i = 0; i < 3; i++) {
          inner:  for (int j = 0; j < 10; j++) {
                if(j%2==0){
                    continue inner;//这里相当于continue, continue默认跳过当前循环的剩余语句，跳转到当前循环的起点
                }
              System.out.print(i +" + "+j+" = "+(i+j)+"   ");//只输出加数是奇数的运算场景
            }
           System.out.println();//内循环结束后换行
        }
    }


    /**
     * 带标签的continue会跳过剩余语句，直接跳转到标签的位置，然后进入到标签后面的循环
     */
    @Test
    public void testContinue5(){
        outer: for (int i = 0; i < 3; i++) {
            inner:  for (int j = 0; j < 10; j++) {
                if(j==2){
                    continue outer;//这里continue跳过内循环和外循环的剩余语句，跳转到外循环的起点
                }
                System.out.print(i +" + "+j+" = "+(i+j)+"   ");
            }
            System.out.println();//这条语句没有执行的机会
        }
    }

    @Test
    public void testContinueAndBreak5(){
        int i=0;
        outer:
        while(true){
            System.out.println("outer while loop");
            while(true){
                i++;
                System.out.println("i = " + i);
                if(i==1){
                    System.out.println("continue");
                    continue;//回到内循环起点
                }
                if(i==3){
                    System.out.println("continue outer");
                    continue  outer;//回到外循环起点
                }
                if(i==5){
                    System.out.println("break");
                    break;//回到外循环起点
                }
                if(i==7){
                    System.out.println("break outer");
                    break outer;//跳出外循环
                }
            }
        }
    }

}