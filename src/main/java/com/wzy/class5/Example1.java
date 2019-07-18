package com.wzy.class5;

import com.wzy.class3.A;
import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: Example1
 * @description: TODO
 * @date 2019/7/18 20:41
 */
public class Example1 {

    static A a;
    public static void main(String[] args) throws Exception {
        a = new A();
        out.println("befre lock");
        out.println("无锁"+ClassLayout.parseInstance(a).toPrintable());//无锁
        Thread t1= new Thread(){
            public void run() {
                synchronized (a){
                    out.println("t1 release");
                    out.println("t1 "+ ClassLayout.parseInstance(a).toPrintable());//偏向锁
                }
                out.println("after t1 release");
            }
        };
        t1.start();
        Thread.sleep(5000);
        Thread t2= new Thread(){
            public void run() {
                synchronized (a){
                    out.println("t2 release");
                    out.println("t2 "+ClassLayout.parseInstance(a).toPrintable());//轻量锁
                }

            }
        };
        t2.start();
        Thread.sleep(5000);
        Thread t3= new Thread(){
            public void run() {
                synchronized (a){
                    out.println("t3 release");
                    out.println("t3 "+ClassLayout.parseInstance(a).toPrintable());//轻量锁
                }

            }
        };
        t3.start();
        Thread.sleep(5000);
        System.gc();
        out.println("after gc()");
        out.println(ClassLayout.parseInstance(a).toPrintable());//无锁---gc
    }

}
