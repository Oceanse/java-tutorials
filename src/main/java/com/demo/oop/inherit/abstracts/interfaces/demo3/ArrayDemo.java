package com.demo.oop.inherit.abstracts.interfaces.demo3;

/**
 * 面向接口设计实现和调用，用接口和实现类封装某种行为
 * 生成工程生产实现类
 * 面向接口调用
 * @author epanhai
 */
public class ArrayDemo {

    /**
     * 面向接口调用
     * 接口类型作为参数
     * @param numbers
     * @param command
     */
    public static void processArray(int[] numbers, Command command) {
        for (int number : numbers) {
            command.process(number);
        }
    }

    public static void main(String[] args) {
        Command doubleCommand = CommandFactory.getCommand("double");
        Command squareCommand = CommandFactory.getCommand("square");
        processArray(new int[]{1, 2, 3}, doubleCommand);
        System.out.println();
        processArray(new int[]{1, 2, 3}, squareCommand);
    }

}


/**
 * 处理行为接口规范
 */
interface Command {
    void process(int element);
}


/**
 * 实现1：翻倍
 */
class DoubleCommand implements Command {
    @Override
    public void process(int element) {
        System.out.print(2 * element + "\t");
    }
}

/**
 * 实现2：平方
 */
class SquareCommand implements Command {
    @Override
    public void process(int element) {
        System.out.print(element * element + "\t");
    }
}

/**
 * 实现类生成工厂
 */
class CommandFactory {

    /**
     * 接口类型作为返回值
     * @param action
     * @return
     */
    public static Command getCommand(String action){
        if(action=="double"){
            return new DoubleCommand();
        }else if(action=="square"){
            return new SquareCommand();
        }else{
            return null;
        }
    }
}