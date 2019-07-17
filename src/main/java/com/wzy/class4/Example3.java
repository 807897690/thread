package com.wzy.class4;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.LinkedTransferQueue;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: Example3
 * @description: TODO
 * @date 2019/7/17 9:15
 */
public class Example3 {

    static LinkedTransferQueue<A> list;

    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list = new LinkedTransferQueue<>();

        Thread t2 = new Thread(){
            @Override
            public void run() {
                int i = 0;
                while(true) {
                    try {
                        A a = list.take();
                        synchronized (a) {
                            out.println( i+ " last  lock");
                            out.println(ClassLayout.parseInstance(a).toPrintable());

                        }
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();


        Thread t1 = new Thread(){
            @Override
            public void run() {
                int i=30;
                while( i > 0) {
                    A a = new A();
                    synchronized (a) {
                        try {
                            list.transfer(a);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        out.println(i + " first  lock");
                        out.println(ClassLayout.parseInstance(a).toPrintable());
                    }
                    i--;
                }
            }
        };
        t1.start();

    }

}
