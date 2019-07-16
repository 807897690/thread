package com.wzy.class4;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: Example
 * @description: TODO
 * @date 2019/7/16 21:07
 */
public class Example {
    static A a;
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        a = new A();
        out.println("befre lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());
        Thread t1 = new Thread(){
            @Override
            public void run() {
            synchronized (a) {
                out.println("t1 lock");
                out.println(ClassLayout.parseInstance(a).toPrintable());
            }
            }
        };
        t1.start();
        Thread.sleep(10000);
        synchronized (a) {
            out.println("after lock");
            out.println(ClassLayout.parseInstance(a).toPrintable());
        }
    }

}
