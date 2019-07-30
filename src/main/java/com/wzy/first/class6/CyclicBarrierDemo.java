package com.wzy.first.class6;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wzy
 * @title: CyclicBarrierDemo
 * @description: TODO
 * @date 2019/7/30 9:07
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(10);
        for(int i=0; i<10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+ "准备好了");
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"冲出跑道");
            },"thread"+ i).start();
        }
    }

}
