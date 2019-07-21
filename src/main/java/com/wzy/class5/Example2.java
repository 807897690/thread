package com.wzy.class5;

import com.wzy.class4.A;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: Example2
 * @description:
 * 对一个类实例化60次，每次都进行同步，一开始60次全部都是批量锁（默认开启偏向锁）,然后循环这60个对象，
 * 每次又进行同步，第20个及以后全部变为偏向锁，之前的19个还是轻量级锁,最后又循环这60个对象，依次同步
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
                for (int i=0; i<list.size(); i++) {
                    A a = list.get(i);
                    synchronized (a) {
                        if (i == 19) {
                            out.println("t3");
                            out.println(ClassLayout.parseInstance(a).toPrintable());
                        }
                    }
                }
                A a = list.get(0);
                out.println("a0 after");
                out.println(ClassLayout.parseInstance(a).toPrintable());

                a = list.get(20);
                out.println("a20 after");
                out.println(ClassLayout.parseInstance(a).toPrintable());

                a = list.get(51);
                out.println("a51 after");
                out.println(ClassLayout.parseInstance(a).toPrintable());
            }
        };
        t3.start();
        t3.join();

        Thread thread2 = new Thread(){
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
        thread2.start();
        thread2.join();

        A a = new A();
        synchronized (a) {
            out.println("new a init");
            out.println(ClassLayout.parseInstance(a).toPrintable());
        }

    }

}
