package com.demo.basic.keywords.statics.static_variable;

import java.io.*;

/**
 * 类变是无法被序列化，因为序列化是针对实例变量的。
 * 在java中，序列化是将对象的状态转换为字节流的过程，以便在网络上传输或保存到持久存储中。
 * 当对象被序列化时，只会保存实例变量的状态，包括其值和类型等信息。
 * 类变量保持不变，保持其在类加载时的值

 */
class MyClass implements Serializable {
    private static int classVariable = 10;
    private int instanceVariable;

    public MyClass(int instanceVariable) {
        this.instanceVariable = instanceVariable;
        classVariable=30;
    }

    public static void setClassVariable(int classVariable) {
        MyClass.classVariable = classVariable;
    }

    public void setInstanceVariable(int instanceVariable) {
        this.instanceVariable = instanceVariable;
    }

    public void display() {
        System.out.println("Class variable:" + classVariable);
        System.out.println("Instance variable:" + instanceVariable);
    }
}

