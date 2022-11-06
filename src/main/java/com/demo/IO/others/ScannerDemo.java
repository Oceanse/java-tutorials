package com.demo.IO.others;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) throws IOException {
        //test();
    }


    public static void test() {
        //public Scanner(InputStream source)
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input the data: ");
        String content = scanner.nextLine();
        System.out.println(content);
    }


/*    public static void test2() throws IOException {
       //BufferedInputStream(InputStream in)
        BufferedInputStream br=new BufferedInputStream(System.in);
        System.out.println("please input the data: ");
        byte[] b=new byte[10];
        int len;
        while((len=br.read(b))!=-1){
            System.out.print(new String(b,0,len));
        }
        br.close();
    }*/



    @Test
    public static void test3() throws IOException {
        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("pom.xml")));

            while (s.hasNext()) {
                System.out.println(s.next());
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }




}
