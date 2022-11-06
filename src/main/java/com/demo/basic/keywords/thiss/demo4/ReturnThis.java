package com.demo.basic.keywords.thiss.demo4;


/**
 * @author epanhai
 */
public class ReturnThis {
    int count;

    /**
     * 如果在某个方法中把this 作为返同值，则可以多次连续调用同一个方法，
     * 从而使得代码更加简洁。但足，这种把this 作为返回值的方法可能造成实际意义的模糊，例如
     * grow方法用千表示count数量的增长，即count成员变量的值加1. 实际上不应该有返回值。
     * @return
     */
    public ReturnThis countAdd(){
        count++;
        return this;
    }

    public static void main(String[] args) {
        ReturnThis returnThis = new ReturnThis();
        returnThis.countAdd().countAdd().countAdd();
        System.out.println(returnThis.count);
    }
}
