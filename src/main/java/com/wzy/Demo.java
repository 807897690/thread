package com.wzy;

/**
 * @author wzy
 * @title: Demo
 * @description: TODO
 * @date 2019/7/9 21:11
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 两个线程，一个线程一直加，另一个线程监控，当前一个线程加到5的时候，线程二给出提示
 */
public class Demo {

    private List<Object> list = new ArrayList<Object>();

    public void add(Object object) {
        list.add(object);
    }

    public int getSize() {
        return list.size();
    }

    public static void main(String[] args) {
        final Demo demo = new Demo();
        final CountDownLatch latch = new CountDownLatch(1);
        Thread t2 = new Thread(){
            @Override
            public void run() {
               if(demo.getSize() != 5) {
                   try {
                       latch.await();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println("list大小为5");
               }
            }
        };
        t2.start();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    Object o = new Object();
                    demo.add(o);
                    if(demo.getSize() == 5) {
                        latch.countDown();
                    }
                    System.out.println(demo.getSize());
                }
            }
        };
        t1.start();
    }

}
