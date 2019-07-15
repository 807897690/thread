package com.wzy.class3;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: JolExample4
 * @description: TODO
 * @date 2019/7/15 17:05
 */
public class JolExample4 {

    static A a;
    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);
        a= new A();
        out.println("befor lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());//偏向锁
        synchronized (a){
            out.println("lock ing");
            out.println(ClassLayout.parseInstance(a).toPrintable());//偏向锁
        }
        out.println("after lock");
        out.println(ClassLayout.parseInstance(a).toPrintable());//偏向锁
    }

}
