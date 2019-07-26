package com.wzy.class8;

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
        CountDownLatch latch = new CountDownLatch(1);
        for(int i=0; i<1000; i++) {
            Thread t1 = new Thread(){
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    myLock.lock();
                    add();
                    myLock.unLock();
                }
            };
            t1.start();
        }
        latch.countDown();
        TimeUnit.SECONDS.sleep(60);
        System.out.println(i);
    }

    public static void add() {
        i++;
    }

}
