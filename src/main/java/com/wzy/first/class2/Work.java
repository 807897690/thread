package com.wzy.first.class2;

import java.util.concurrent.CountDownLatch;

/**
 * @author wzy
 * @title: Work
 * @description: 模拟购买飞机票的程序（北京到上海）
 * 三家航空公司：东方、南方、海航，每家公司的票数、票价、信息都不一样
 * @date 2019/7/25 16:05
 */
public class Work {


    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        /**
         * 模拟一百个用户来访问，解析每个用户的请求，判断用户访问那个航空公司，返回信息，并且购买成功
         */
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            Thread t1 = new Thread(){
                @Override
                public void run() {
                    int num = finalI % 3;
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    switch (num) {
                        case 0:
                            DongFang.getTicket(Thread.currentThread().getName());
                            break;
                        case 1:
                            HaiHang.getTicket(Thread.currentThread().getName());
                            break;
                        case 2:
                            Nanfang.getTicket(Thread.currentThread().getName());
                            break;
                    }
                }
            };
            t1.setName("用户"+(i+1));
            t1.start();
        }
        latch.countDown();
    }

}
