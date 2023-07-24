package com.demo.basic.keywords.statics.static_variable;

import org.junit.Test;

import java.io.*;

/**
 * ????
 */
public class StaticVariableSerializeTest {

    public static void main(String[] args) {
        MyClass obj = new MyClass(20);
        try {
            FileOutputStream fileOut = new FileOutputStream("object.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.println("Object.serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileInputStream fileIn = new FileInputStream("object.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            MyClass deSerializedObj = (MyClass) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Object deserialized successfully.");
            deSerializedObj.display();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStaticVariableSerialize() {
        try {
            FileInputStream fileIn = new FileInputStream("object.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            MyClass deSerializedObj = (MyClass) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Object deserialized successfully.");
            deSerializedObj.display();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}