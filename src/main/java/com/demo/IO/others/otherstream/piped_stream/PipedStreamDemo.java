package com.demo.IO.others.otherstream.piped_stream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道流的主要作用是可以进行两个线程间的通信。
 * 如果要进行管道通信，则必须把 PipedOutputStream 连接在 PipedInputStream 上。
 * 为此，PipedOutputStream 中提供了 connect() 方法。
 * @author epanhai
 */
public class PipedStreamDemo {

    public static void main(String[] args) {
        Send s = new Send();
        Receive r = new Receive();
        try {
            // 管道输出流和管道输入流相连接
            s.getPos().connect(r.getPis());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(s).start();
        new Thread(r).start();
    }


    /**
     * 管道输出流
     */
    static class Send implements Runnable {

        private PipedOutputStream pos = null;

        Send() {
            // 实例化输出流
            pos = new PipedOutputStream();
        }

        @Override
        public void run() {
            String str = "Hello World!!!";
            try {
                pos.write(str.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                pos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 得到此线程的管道输出流
         */
        PipedOutputStream getPos() {
            return pos;
        }

    }



    /**
     * 管道输出入流
     */
    static class Receive implements Runnable {

        private PipedInputStream pis = null;

        Receive() {
            pis = new PipedInputStream();
        }

        @Override
        public void run() {
            byte[] b = new byte[1024];
            int len = 0;
            try {
                len = pis.read(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                pis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("接收的内容为：" + new String(b, 0, len));
        }

        /**
         * 得到此线程的管道输入流
         */
        PipedInputStream getPis() {
            return pis;
        }

    }

}
