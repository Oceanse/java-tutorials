package com.demo.basic.method.valuepass;

import org.testng.annotations.Test;

public class ReferenceValuePass2 {


    /**
     * 引用变量保存的值是堆内存地址，所以参数是引用变量时传递的就是地址
     *
     * 类比：
     * User user=new User();
     * User user2=user;
     */
    @Test
    public void test(){
        User2 user=new User2();
        user.setI(10);
        user.setJ(20);
        System.out.println("test before: "+user.i+" "+user.j);
        //把user保存的地址复制一份传递给swap方法，之后test方法栈中的局部变量user2和swap方法栈中的局部变量指向同一个堆对象；
        //user变量值永远不会变，但是其指向对象的内容可能会发送变化
        swap(user);
        System.out.println("test after: "+user.i+" "+user.j);
    }

    public static void swap(User2 user){
        System.out.println("swap before: "+user.i+" "+user.j);
        int temp=user.i;
        user.i=user.j;
        user.j=temp;
        System.out.println("swap after: "+user.i+" "+user.j);
    }
}

class User2 {
    public int i;

    public int j;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
