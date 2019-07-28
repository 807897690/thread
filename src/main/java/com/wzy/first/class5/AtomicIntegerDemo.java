package com.wzy.first.class5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wzy
 * @title: AtomicIntegerDemo
 * @description: TODO
 * @date 2019/7/28 12:19
 */
public class AtomicIntegerDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger(100);

    public static void main(String[] args) {
        new Thread(() ->{
            System.out.println(atomicInteger.compareAndSet(100, 110));
        }).start();
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicInteger.compareAndSet(110, 100));
        }).start();
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicInteger.compareAndSet(100, 110));
        }).start();
    }

}
