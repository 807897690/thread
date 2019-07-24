package com.wzy.first.class1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wzy
 * @title: Workers
 * @description: TODO
 * @date 2019/7/24 10:19
 */
public class Worker2 {

    static List<String> orders = new ArrayList<>();

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                3, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        for(int i=0; i<3; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + "-----------" + getOrder());
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        new Thread(()->{
            int i =0;
            while(true) {
                try {
                    orders.add("号码"+i);
                    Thread.sleep(400);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public synchronized  static String getOrder() {
        if(orders.size() > 0) {
            String order = orders.get(0);
            orders.remove(0);
            return order;
        }else {
            return "还没订单号生成";
        }
    }

}
