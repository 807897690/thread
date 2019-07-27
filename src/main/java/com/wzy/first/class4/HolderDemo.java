package com.wzy.first.class4;

import java.util.concurrent.CountDownLatch;

/**
 * @author wzy
 * @title: Holder
 * @description: Holder
 * 声明类的时候，成员变量中不声明实例变量，而放到内部静态类中
 * 1、实现懒加载
 * 2、线程安全，静态类只会被实例化一次,内部类只有再调用的时候才会被实例化
 * @date 2019/7/27 10:54
 */
public class HolderDemo {

    private HolderDemo() {

    }

    private static class Holder {
        private static HolderDemo instance = new HolderDemo();
    }

    public static HolderDemo getInstance() {
        return Holder.instance;
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        for(int i=0; i<2000; i++) {
            new Thread(()->{
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(HolderDemo.getInstance());
            }).start();
        }
        latch.countDown();
    }

}
