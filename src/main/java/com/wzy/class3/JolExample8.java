package com.wzy.class3;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: JolExample8
 * @description: TODO
 * @date 2019/7/15 17:49
 */
public class JolExample8 {

    static A a;
    public static void main(String[] args) throws Exception {
        //Thread.sleep(5000);
        a = new A();
        out.println("befre lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());//无锁

        Thread t1= new Thread(){
            public void run() {
                synchronized (a){
                    try {
                        Thread.sleep(5000);
                        out.println("t1 release");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        Thread.sleep(1000);
        out.println("t1 lock ing");
        out.println(ClassLayout.parseInstance(a).toPrintable());//轻量锁
        sync();
        out.println("after lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());//重量锁

        System.gc();
        out.println("after gc()");
        out.println(ClassLayout.parseInstance(a).toPrintable());//无锁---gc
    }

    public  static  void sync() throws InterruptedException {
        synchronized (a){
            out.println("t1 main lock");
            out.println(ClassLayout.parseInstance(a).toPrintable());//重量锁
        }
    }

}
