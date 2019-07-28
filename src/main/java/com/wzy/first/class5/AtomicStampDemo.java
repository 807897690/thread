package com.wzy.first.class5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author wzy
 * @title: AtomicStampDemo
 * @description: TODO
 * @date 2019/7/28 12:47
 */
public class AtomicStampDemo {

    private static AtomicStampedReference asr = new AtomicStampedReference(100, 1);

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(asr.compareAndSet(100, 110, asr.getStamp(), asr.getStamp()+1));
            System.out.println(asr.compareAndSet(110, 100, asr.getStamp(), asr.getStamp()+1));
        });
        Thread t2 = new Thread(()->{
            int stamp = asr.getStamp();
            System.out.println("t2:stamp:"+stamp);
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2："+asr.compareAndSet(100, 120, stamp, stamp+1));
        });
        t1.start();
        t2.start();
    }

}
