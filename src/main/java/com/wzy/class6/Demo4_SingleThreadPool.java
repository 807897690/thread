package com.wzy.class6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wzy
 * @title: Demo4_SingleThreadPool
 * @description: TODO
 * @date 2019/7/21 22:11
 */
public class Demo4_SingleThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(()->{
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }
    }

}
