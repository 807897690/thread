package com.wzy.class2;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wzy
 * @title: TransferQueueDemo
 * @description: TransferQueue
 * 和普通的queue的方法差不多，多了一个transfer方法。
 * 如果你用这种队列的话，往往是消费者先启动，生产者生产一个东西的时候，他先是去找消费者，
 * 如果有消费者就直接丢给消费者
 * @date 2019/7/12 16:00
 */
public class TransferQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        LinkedTransferQueue<String> strings = new LinkedTransferQueue<>();

        new Thread(()->{
            while(true) {
                try {
                    System.out.println("t1" + strings.take());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            while(true) {
                try {
                    System.out.println("t2" + strings.take());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);

//        strings.transfer("aaa");
//		strings.put("aaa");
		new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    strings.transfer("aaa" + i);
                    System.out.println(strings.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
		}).start();
    }

}
