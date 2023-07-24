package com.demo.basic.flow_control;

import org.testng.annotations.Test;

/**
 * 流程控制：
 * 从结构化程序设计角度出发，程序有3种结构：顺序结构、选择结构和循环结构。
 * 1996年， 计算机科学家Bohm和Jacopini证明了这样的事实：任何简单或复杂的算法都可以由顺序结构、选择结构和循环结构这三种基本结构组合而成。
 * 所以， 这三种结构就被称为程序设计的三种基本结构， 也是结构化程序设计必须采用的结构。
 * 若是在程序中没有给出特别的执行目标，系统则默认自上而下一行一行地执行该程序，这类程序的结构就称为顺序结构。
 * 但是事物的发展往往不会遵循早就设想好的轨迹进行，因此，所设计的程序还需要能够具有在不同的条件下处理不同问题，这类程序的结构就称为选择结构。
 * 另外还需要进行一些相同的重复操作时，设计的程序能省时省力地解决问题，这类程序的结构就称为循环结构。
 * <p>
 * 关于语句：
 * Java 中，语句是最小的组成单位，每个语句必须使用分号作为结束符
 * <p>
 * 分类：Java空语句、简单语句和复合语句
 * 空语句(Empty Statement)：其实就是一个分号,它在程序中什么都不做
 * 简单语句(Simple Statement )：是最基本的语句形式，表示一个单独的操作，包含：
 * 表达式语句：包含一个表达式，执行特定的操作， int x=10;
 * 赋值语句：用于将一个值赋值给一个变量，x=10;
 * 方法调用语句：调用一个方法执行特定的操作， System.out.println("死循环");
 * 复合语句(Compound Statement)：也称为块(block), 由花括号括起来的一组简单语句，可以用于选择、循环等流程控制或者方法或者代码块
 *
 * @author epanhai
 */
public class StatementDemo {


    /**
     * 空语句 Empty Statement其实就是一个分号,它在程序中什么都不做，也不包含具有实际性的语句。
     */
    @Test
    public void testEmptyStatement() throws InterruptedException {
        boolean flag = true;
        if (flag) {
            for (; ; ) {
                System.out.println("死循环");
                Thread.sleep(1000);
            }
        }
        while (true) {
            ;
        }
    }


    /**
     * 简单语句
     */
    @Test
    public void testSimpleStatement() {
        int age = 33;
        String name;
        name = "ocean";
        System.out.println(name.toUpperCase());
    }

    /**
     * 复合语句又称为语句块，是很多个简单语句的组合
     */
    @Test
    public void test3CompoundStatement() {
        double random = Math.random();
        if (random > 0.5) {
            System.out.println(random);
            System.out.println("当前数大于0.5");
        }

        while (true) {
            System.out.println("死循环应该被避免");
            break;
        }

        for (; ; ) {
            System.out.println("死循环应该被避免");
            break;
        }
    }


    /**
     * 一java个语句拆成多行
     */
    @Test
    public void test4() {
        //由于 Java 使用分号作为语句的结束符，所以下面的 3 行代码会被 Java 认为是一条语句，因为这 3 行中只有一个分号。
        // 但是我们不推荐使用这种方式来编写语句。
        String str = "Apple "
                + "Banner " + "Pear "
                + " Orange";
    }


}
