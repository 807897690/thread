package com.wzy.class3;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: JolExample1
 * @description:
 * 1、java项目启动，延迟5秒左右执行才会给对象加上偏向锁，否则一开始对象都是无锁
 * 2、偏向锁与hashcode互斥，如果一个对象的状态是偏向锁，那么该对象肯定没有hashcode值，否则该对象的状态绝不会是偏向锁
 * @date 2019/7/14 20:37
 */
public class JolExample1 {

    static A a;

    public static void main(String[] args) {
        a = new A();
        out.println("before lock");
//        out.println(VM.current().details());
        out.println(ClassLayout.parseInstance(a).toPrintable());//无锁
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (a) {
                        Thread.sleep(5000);
                        out.println("thread release");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        out.println("after lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());//轻量级锁
        sync();
        out.println("after ing lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());//重量级锁
        System.gc();
        out.println("after gc");
        out.println(ClassLayout.parseInstance(a).toPrintable());//无锁
    }

    private static void sync() {
        synchronized (a) {
            out.println("ing lock");
            out.println(ClassLayout.parseInstance(a).toPrintable());//重量级锁
        }
    }


}
