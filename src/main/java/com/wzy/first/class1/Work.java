package com.wzy.first.class1;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wzy
 * @title: Work
 * @description: 编写一个类似银行、医院的叫号程序（要求：多个窗口叫号，不重号、不跳号）
 * @date 2019/7/23 17:31
 */
public class Work {


    public static void main(String[] args) {

        AtomicInteger i= new AtomicInteger();

        LinkedTransferQueue<String> strings = new LinkedTransferQueue<>();

        new Thread(()->{
            while(true) {
                try {
                    System.out.println(Thread.currentThread().getName()+"取出号码："+strings.take());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while(true) {
                try {
                    System.out.println(Thread.currentThread().getName()+"取出号码："+strings.take());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while(true) {
                try {
                    System.out.println(Thread.currentThread().getName()+"取出号码："+strings.take());
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
           while(true) {
                try {
                    strings.transfer("号码"+i);
                    i.getAndIncrement();
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
