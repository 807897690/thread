package com.wzy.first.class1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wzy
 * @title: Work6
 * @description: TODO
 * @date 2019/7/24 16:12
 */
public class Work6 {

    static List<String> orders = new ArrayList<>();


    private static Lock lock = new ReentrantLock();

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
                while (true) {
                    try {
                        orders.add("号码" + i);
                        /**
                         * 为什么这里加了睡眠之后，程序就跑的正常，不加的话，打印就不正常了
                         */
//                    Thread.sleep(400);
                        i.getAndAdd(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }).start();
    }

    public static String getOrder() {
        try {
            lock.lock();
            if (orders.size() > 0) {
                String order = orders.get(0);
                String order1 = "";
                if (orders.size() > 1) {
                    order1 = orders.get(1);
                }
                orders.remove(0);
                System.out.println("--------------" + order + "---------------------" + order1);
                return order;
            } else {
                return "还没订单号生成";
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

}
