package com.wzy.class1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wzy
 * @title: Demo3
 * @description: TODO
 * @date 2019/7/10 15:54
 */
public class Demo3 implements Runnable{

    /**
     * 公平锁,谁等的时间长就让哪个线程获得锁
     */
    private Lock lock = new ReentrantLock(true);

    public void run() {
        for(int i=0; i<100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "正在执行");
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Demo3 demo3 = new Demo3();
        Thread t1 = new Thread(demo3);
        Thread t2 = new Thread(demo3);
        t1.start();
        t2.start();

    }
}
