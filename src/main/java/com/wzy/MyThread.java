package com.wzy;

/**
 * @author wzy
 * @title: MyThread
 * @description: TODO
 * @date 2019/7/4 21:20
 */
public class MyThread {
    //mutex
    /**
     * 怎么调用run方法？
     * 重入锁：
     * interrupt : 1、给当前线程更改一个值，解除循环  2、抛出一个Interrupt异常，解阻塞
     * synchronized 实现一把锁
     * 偏向锁：不会调用OS的实现函数
     * 轻量级锁：
     * 重量级锁：借助OS函数来实现的锁
     */
    public synchronized static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("I am a thread!");
            }
        };
        thread.start();
    }
}
