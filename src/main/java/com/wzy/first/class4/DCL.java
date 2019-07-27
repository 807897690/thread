package com.wzy.first.class4;

import java.util.concurrent.CountDownLatch;

/**
 * @author wzy
 * @title: HoonSingleton
 * @description: Double+Check
 * 1、是线程安全
 * 2、是懒加载模式
 * @date 2019/7/27 10:23
 */
public class DCL {

    private static DCL instance = null;

    private DCL() {

    }

    public static DCL getInstance() {
        if(instance == null) {
            synchronized (DCL.class) {
                if(instance == null) {
                    instance = new DCL();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        for(int i=0; i<2000; i++) {
            new Thread(()->{
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(DCL.getInstance());
            }).start();
        }
        latch.countDown();
    }

}
