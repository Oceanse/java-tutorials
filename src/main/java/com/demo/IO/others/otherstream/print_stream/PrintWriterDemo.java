package com.demo.IO.others.otherstream.print_stream;

import org.testng.annotations.Test;

import java.io.*;

public class PrintWriterDemo {

    @Test
    public void test() throws FileNotFoundException {
        //Data to write on Console using PrintWriter
        PrintWriter writer = new PrintWriter(System.out);
        writer.write("Javatpoint provides tutorials of all technology.");
        writer.flush();
        writer.close();

        //Data to write in File using PrintWriter
        PrintWriter writer1 = null;
        writer1 = new PrintWriter("testout.txt");
        writer1.write("Like Java, Spring, Hibernate, Android, PHP etc.");
        writer1.flush();
        writer1.close();
    }


    @Test
    public void test2() throws IOException {
    }

}
