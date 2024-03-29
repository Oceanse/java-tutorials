package com.demo.basic.flow_control.loop.whiles;

import org.testng.annotations.Test;

/**
 * 四要素： 初值，条件，迭代，循环体
 * <p>
 * 初始化条件
 * while(条件表达式){
 * 循环体(包含迭代)
 * }
 * 先判断条件表达式-->循环体-->条件表达式--->.....
 * 如果条件表达式一开始就是false, 循环体一次都不会执行
 * <p>
 * while 、do while 循环的循环迭代语句紧跟着循环体，
 */
public class WhileDemo {

    /**
     * 死循环
     */
    @Test
    public void testDeadCycle() throws InterruptedException {
        while (true) {
            System.out.println(Math.random());
            Thread.sleep(1000);
        }
        //System.out.println("这里永远无法执行"); //这里放开会编译不通过，提示unreachable
    }


    /**
     * 死循环
     * ;也是一个合法的语句，是特殊的空语句, 由于空语句没有迭代条件，所以陷入死循环
     * 编译器不能判断出i > 0的结果，只有运行时JVM才能知道结果
     */
    @Test
    public void testDeadCycle2() {
        int i = 10;
        while (i > 0) {
            ;//空语句
        }
        //不属于while循环,这里编译的时候编译器不能判断出是否为死循环，因为编译器认为i>0可能为false, 所以没编译报错unreachable
        System.out.println("我不属于while循环，但是永远得不到执行机会");
    }

    @Test
    public void testDeadCycle3() {
        while (10 > 0) {
            ;//空语句
        }
        //这里打开注释后，编译器会报错，因为编译的时候编译器可以判断出为死循环，所以编译报错unreachable
        //System.out.println("我不属于while循环，但是永远得不到执行机会");

    }


    /**
     * 死循环
     * ;也是一个合法的语句，是特殊的空语句, 由于空语句没有迭代条件，所以陷入死循环
     * 编译器不能判断出i > 0的结果，只有运行时JVM才能知道结果
     */
    @Test
    public void testDeadCycle4() {
        final int i = 10;
        while (i > 0) {
            ;//空语句
        }
        //不属于while循环,这里编译的时候编译器会进行编译优化，变量i会被字面值10进行替换，所以编译器能判断出死循环，因此编译报错unreachable
        // System.out.println("我不属于while循环，但是永远得不到执行机会");
    }


    /**
     * 循环体只有一句时候可以省略{}，不建议这么做，因为这样会降低程序的可读性
     */
    @Test
    public void testBracket() {
        int i = 10;
        while (i > 0)
            System.out.println(i--);
        System.out.println("i=" + i);//不属于while循环体

    }


    /**
     * while 语句计算 4 的阶乘
     */
    @Test
    public void testWhileFactorial() {
        int i = 1;
        int res = 1;
        while (i <= 4) {
            res *= i;
            i++;
        }
        System.out.println("4的阶乘结果为：" + res);
    }


    /**
     * while 语句计算 4 的阶乘
     */
    @Test
    public void testWhileFactorial2() {
        int i = 4;
        int res = 1;
        while (i > 0) {
            res *= i;
            i--;
        }
        System.out.println("4的阶乘结果为：" + res);
    }


    /**
     * 图书列表中保存50条信息，现在需要让它分5行, 每行显示10条，
     */
    @Test
    public void testShowBooks() {
        int pageIndex = 1;
        while (pageIndex < 51) {
            System.out.print(pageIndex + "\t");
            if (pageIndex % 10 == 0) {
                System.out.println();
            }
            pageIndex++;
        }
    }
}
