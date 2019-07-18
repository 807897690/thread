package com.wzy.class5;

import com.wzy.class4.A;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: Example2
 * @description: TODO
 * @date 2019/7/18 21:55
 */
public class Example2 {

    static List<A> list;
    public static void main(String[] args) throws InterruptedException {
//        Thread.sleep(5000);
        list = new ArrayList<>();
        Thread t1 = new Thread(){
            @Override
            public void run() {

                for(int i = 0; i<60; i++) {
                    A a = new A();
                    synchronized (a) {
                        list.add(a);
                        if(i == 19) {
                            out.println("t1");
                            out.println(ClassLayout.parseInstance(a).toPrintable());
                        }
                    }
                }
            }
        };
        t1.start();
        t1.join();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        thread.start();;
        Thread t2 = new Thread(){
            @Override
            public void run() {
                int k=0;
                for (A a : list) {
                    synchronized (a) {
                        if (k == 19) {
                            out.println("t2");
                            out.println(ClassLayout.parseInstance(a).toPrintable());
                        }
                    }
                    k++;
                }
            }
        };
        t2.start();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        thread1.start();
        thread1.join();

        Thread t3 = new Thread(){
            @Override
            public void run() {
                for (int i=30; i<list.size(); i++) {
                    A a = list.get(i);
                    synchronized (a) {
                        if (i == 54) {
                            out.println("t3");
                            out.println(ClassLayout.parseInstance(a).toPrintable());
                        }
                    }
                }
                A a = list.get(20);
                out.println("t3 after");
                out.println(ClassLayout.parseInstance(a).toPrintable());
            }
        };
        t3.start();
    }

}
