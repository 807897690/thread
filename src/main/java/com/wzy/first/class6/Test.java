package com.wzy.first.class6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author wzy
 * @title: Test
 * @description: TODO
 * @date 2019/7/25 22:49
 */
public class Test {

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        MyLock myLock = new MyLock();
        CountDownLatch latch = new CountDownLatch(1000);
        for(int i=0; i<1000; i++) {
            Thread t1 = new Thread(){
                @Override
                public void run() {
                    myLock.lock();
                    add();
                    myLock.unlock();
                    latch.countDown();
                }
            };
            t1.start();
        }
        latch.await();
        System.out.println(i);
    }

    public static void add() {
        i++;
    }

}
