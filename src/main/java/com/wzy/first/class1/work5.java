package com.wzy.first.class1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wzy
 * @title: work5
 * @description: TODO
 * @date 2019/7/24 16:06
 */
public class work5 {

    static BlockingQueue<String> orders = new LinkedBlockingQueue<>();

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
                        System.out.println(Thread.currentThread().getName() + "-----------" + getOrder());
//                        try {
//                           TimeUnit.MILLISECONDS.sleep(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            });
        }

        /**
         * 创建一个线程去生成号码
         */
        new Thread(()->{
            AtomicInteger i = new AtomicInteger(0);
            synchronized (orders) {
                while (true) {
                    try {
                        orders.put("号码" + i);
                        /**
                         * 为什么这里加了睡眠之后，程序就跑的正常，不加的话，打印就不正常了
                         */
//                    Thread.sleep(400);
                        i.getAndAdd(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public synchronized static String getOrder() {
        if (orders.size() > 0) {
            String order = null;
            try {
                order = orders.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            orders.remove(0);
            return order;
        } else {
            return "还没订单号生成";
        }
    }

}
