package com.wzy.class6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wzy
 * @title: Demo3_CachePool
 * @description: TODO
 * @date 2019/7/21 22:10
 */
public class Demo3_CachePool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for (int i = 0; i < 2; i++) {
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);

        TimeUnit.SECONDS.sleep(60);
        System.out.println(service);

        TimeUnit.SECONDS.sleep(1);
        System.out.println(service);
    }

}
