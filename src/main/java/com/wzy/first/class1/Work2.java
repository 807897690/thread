package com.wzy.first.class1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wzy
 * @title: Workers
 * @description: 模拟医院叫号系统
 * @date 2019/7/24 10:19
 */
public class Work2 {

    static List<String> orders = new ArrayList<>();

    /**
     * 初始化一个线程池，默认有3个窗口叫号
     */
    static ExecutorService executorService = new ThreadPoolExecutor(
            3, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));

    public static void main(String[] args) {
        for(int i=0; i<3; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        getOrder();
                    }
                }
            });
        }

        /**
         * 创建一个线程去生成号码
         */
        new Thread(()->{
            AtomicInteger i = new AtomicInteger(0);
            while(true) {
                try {
                    orders.add("号码" + i);
                    System.out.println("添加号码："+i.intValue());
                    i.getAndAdd(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public synchronized static void getOrder() {
        if (orders.size() > 0) {
            String order = orders.get(0);
            orders.remove(0);
            System.out.println(Thread.currentThread().getName() + "-----------" + order);
        } else {
            System.out.println(Thread.currentThread().getName() + "-----------" + "还没订单号生成");
        }
    }

}
