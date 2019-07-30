package com.wzy.first.class6;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wzy
 * @title: ReentrantReadWriteLockDemo
 * @description:
 *  读写锁：实现读写分离，读读共享，读写互斥，写写互斥
 *  读写锁适用于读多写少的情况，性能远高于重入锁ReentrantLock
 * @date 2019/7/30 9:16
 */
public class ReentrantReadWriteLockDemo {

    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private void method1() {
        try {
            readLock.lock();
            System.out.println("method1 start");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("method1 edn");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    private void method2() {
        try {
            readLock.lock();
            System.out.println("metho2 start");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("metho2 end");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    private void method3() {
        try {
            writeLock.lock();
            System.out.println("metho3 start");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("metho3 end");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockDemo demo = new ReentrantReadWriteLockDemo();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                demo.method1();
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                demo.method2();
            }
        };
        Thread t3 = new Thread(){
            @Override
            public void run() {
                demo.method3();
            }
        };
        t1.start();
        t2.start();
        t3.start();
    }

}
