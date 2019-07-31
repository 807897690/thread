package com.wzy.class9;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wzy
 * @title: ReadWriteLockDemo
 * @description: 模拟读写锁升级死锁过程
 * @date 2019/7/31 16:18
 */
public class ReadWriteLockDemo {

    private int data = 0;

    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public void getAndSet(int data) {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"获取读锁");
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName()+"读出数据为："+ this.data);
            try {
                System.out.println(Thread.currentThread().getName() + "开始获取写锁，等待其他线程释放锁");
                writeLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取写锁");
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "写入数据为：" + data);
                this.data = data;
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                writeLock.unlock();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        for(int i=0; i<5; i++) {
            new Thread(()->demo.getAndSet(new Random().nextInt(10))).start();
        }
    }

}
