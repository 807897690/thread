package com.wzy.class9;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wzy
 * @title: ReentrantReadWriteLockDemo
 * @description:
 *  读写锁 : 读读并行，读写串行， 写写串行
 * @date 2019/7/30 9:16
 */
public class ReentrantReadWriteLockDemo {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Integer data = 0;

    public void get() {
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"开始准备读");
        try {
            Thread.sleep((long) (Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"读到数据" + data);
        readWriteLock.readLock().unlock();
    }

    public void put(Integer data) {
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"开始准备写");
        try {
            Thread.sleep((long) (Math.random()*1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = data;
        System.out.println(Thread.currentThread().getName()+"写入数据" + data);
        readWriteLock.writeLock().unlock();
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
        for(int i=0; i<6; i++) {
            new Thread(() -> demo.get()).start();
            new Thread(() -> demo.put(new Random().nextInt(10))).start();
        }
    }

}
