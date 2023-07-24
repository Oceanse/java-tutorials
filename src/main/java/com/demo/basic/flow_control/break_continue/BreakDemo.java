package com.demo.basic.flow_control.break_continue;

import org.testng.annotations.Test;


/**
 * 使用break的场景有两种：switch语句和循环语句
 * 循环中：可以直接强行退出当前的循环，某些时候需要在某种条件出现时强行终止循环，而不是等到循环条件为 false 时才退出循环
 * 一般的break会跳出当前循环
 * 带标签的break会跳出指定标签所在的循环
 * switch:每个 case 子句块的最后添加语句break来退出switch
 * break和continue可以作为单独的java 语句，和标签结合使用起到类似goto的跳转作用
 *
 * @author epanhai
 */
public class BreakDemo {

    @Test
    public void testBreak() {
        int[] scores = new int[]{60, 80, 100, 70, 90};

        boolean isFullMarks = false;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == 100) {
                isFullMarks = true;
                break;//break只是退出循环，并不是退出方法(找到获得满分的人就停止遍历，退出循环)
            }
        }

        if (isFullMarks) {
            System.out.println("There are someone who get full marks");
        } else {
            System.out.println("Nobody get full marks");
        }
    }


    @Test
    public void testBreak2() {
        int[] scores = new int[]{60, 80, 70, 90};

        boolean isFullMarks = false;
        for (int score : scores) {
            if (score == 100) {
                isFullMarks = true;
                break;//break只是退出循环，并不是退出方法(找到获得满分的人就停止遍历，退出循环)
            }
        }

        if (isFullMarks) {
            System.out.println("There are someone who get full marks");
        } else {
            System.out.println("Nobody get full marks");
        }
    }


    @Test
    public void testBreak3() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 5) {
                    break;//j==5时跳出当前循坏,效果就是加数不能为5和之后的数
                }
                System.out.print(i + " + " + j + " = " + (i + j) + "\t");
            }
            System.out.println();
        }

    }


    /**
     * 一般的break会跳出当前循环
     * 带标签的break会跳出指定标签所在的循环
     */
    @Test
    public void testBreak4() {
        outer:
        for (int i = 0; i < 4; i++) {
            inner:
            for (int j = 0; j < 5; j++) {
                if (j == 2) {
                    //break inner;  //j==2时跳出内层循环inner, 效果就是加数不能为2和之后的数
                    break outer;//j==2时跳出外层循环outer
                }
                System.out.print(i + " + " + j + " = " + (i + j) + "\t");
            }
            System.out.println();
        }
    }


}
