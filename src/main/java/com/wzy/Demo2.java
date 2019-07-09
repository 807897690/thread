package com.wzy;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wzy
 * @title: Demo2
 * @description: TODO
 * @date 2019/7/9 21:41
 */
public class Demo2 {

    private final LinkedList<Object> list = new LinkedList<Object>();
    private final int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    public void put(Object object) {
        try {
            lock.lock();
            while(count == MAX) {
                producer.await();
            }
            System.out.println("线程" + Thread.currentThread().getName() + "存入对象");
            list.add(object);
            count++;
            consumer.signalAll();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public Object get(int c) {
        try {
            lock.lock();
            while(count == 0) {
                consumer.await();
            }
            Object object = list.add(c);
            System.out.println("线程" + Thread.currentThread().getName() + "取出对象");
            count--;
            producer.signalAll();
            return object;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        final Demo2 demo2 = new Demo2();
        for(int i=0; i<2; i++) {
            Thread thread = new Thread(){
                @Override
                public void run() {
                    while(true) {
                        demo2.put(new Object());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();
        }
        for(int i=0; i<10; i++) {
            final int finalI = i;
            Thread thread = new Thread(){
                @Override
                public void run() {
                    while(true) {
                        demo2.get(finalI);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();
        }
    }

}
