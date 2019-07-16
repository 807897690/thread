package com.wzy.class4;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: Example2
 * @description: TODO
 * @date 2019/7/16 22:07
 */
public class Example2 {
    static List<A> list;
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        list = new ArrayList<>();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for(int i = 0; i<20; i++) {
                    A a = new A();
                    synchronized (a) {
                        System.out.println("1111111");
                        list.add(a);
                    }
                }
            }
        };
        t1.start();
        t1.join();
        Thread t2 = new Thread(){
            @Override
            public void run() {
                int k=0;
                for (A a : list) {
                    synchronized (a) {
                        if (k >= 17) {
                            out.println(k + "lock");
                            out.println(ClassLayout.parseInstance(a).toPrintable());
                        }
                    }
                    k++;
                }
            }
        };
        t2.start();
    }

}
