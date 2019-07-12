package com.wzy.class2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wzy
 * @title: LinkedBlockingQueueDemo
 * @description: 阻塞式的容器LinkedBlockingQueue,可无界也可有界
 * @date 2019/7/12 15:51
 */
public class LinkedBlockingQueueDemo {

    private static BlockingQueue<String> strings = new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    // 在阻塞式容器里面加了一个方法，put，也就是如果满了就会等待，对应的方法叫take，如果空了就会等待。
                    // 这种容器我们去用的时候自动就实现了阻塞式的生产者消费者。
                    strings.put("商品" + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "producer").start();

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for(;;){
                    try {
                        // take，拿，如果空了也会阻塞
                        System.out.println(Thread.currentThread().getName() + " take " + strings.take()); //如果空了，就会等待
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            },"consumer" + i).start();
        }

    }

}
