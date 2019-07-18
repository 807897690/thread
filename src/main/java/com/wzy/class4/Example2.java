package com.wzy.class4;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: Example2
 * @description:
 * 批量重偏向：t1线程实例化多个对象（同一个类），并且同步了这些对象，t2也同步了这些对象，因为需要锁升级，
 * 需要多次撤销偏向锁升级为轻量级锁，因为多次撤销，jvm认为该类有问题，所以后续的该类对象的锁全部重偏向，默认阀值在20
 * @date 2019/7/16 22:07
 */
public class Example2 {
    static List<A> list;
    public static void main(String[] args) throws InterruptedException {
//        Thread.sleep(5000);
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
        Thread thread = new Thread(){
            @Override
            public void run() {
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
