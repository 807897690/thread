package com.wzy.first.class6;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author wzy
 * @title: SemaphorDemoe
 * @description: TODO
 * @date 2019/7/30 11:38
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore sp = new Semaphore(5);
        for(int i=0; i<10; i++) {
            new Thread(() ->{
                String name = Thread.currentThread().getName();
                System.out.println(name+"开始请求");
                try {
                    sp.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name+"获取请求");
                System.out.println(name+"开始处理业务");
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name+"结束处理业务");
                sp.release();
                System.out.println(name+"离开");
            }, "thread"+ (i+1)).start();
        }
    }

}
