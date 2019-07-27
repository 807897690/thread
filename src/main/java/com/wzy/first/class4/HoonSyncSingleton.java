package com.wzy.first.class4;

import java.util.concurrent.CountDownLatch;

/**
 * @author wzy
 * @title: HoonSingleton
 * @description: 懒汉式+同步方法单例模式
 * 1、线程安全
 * 2、是懒加载模式
 * @date 2019/7/27 10:23
 */
public class HoonSyncSingleton {

    private static HoonSyncSingleton hoonSingleton = null;

    private HoonSyncSingleton() {

    }

    public synchronized static HoonSyncSingleton getInstance() {
        if(hoonSingleton == null) {
            hoonSingleton = new HoonSyncSingleton();
        }
        return hoonSingleton;
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
                System.out.println(HoonSyncSingleton.getInstance());
            }).start();
        }
        latch.countDown();
    }

}
