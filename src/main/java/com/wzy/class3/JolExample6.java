package com.wzy.class3;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: JolExample6
 * @description: TODO
 * @date 2019/7/15 17:18
 */
public class JolExample6 {

    static A a;
    public static void main(String[] args) throws Exception {
        a = new A();
        out.println("befre lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());//无锁
        sync();
        out.println("after lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());//无锁
    }

    public  static  void sync() throws InterruptedException {
        synchronized (a){
            out.println("lock ing");
            out.println(ClassLayout.parseInstance(a).toPrintable());//轻量级锁
        }
    }

}
