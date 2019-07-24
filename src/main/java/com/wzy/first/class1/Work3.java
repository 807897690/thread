package com.wzy.first.class1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wzy
 * @title: Work3
 * @description: TODO
 * @date 2019/7/24 11:34
 */
public class Work3 {

    static List<String> orders = new ArrayList<>();

    /**
     * 初始化一个线程池，默认有3个窗口叫号
     */
    static ExecutorService executorService = new ThreadPoolExecutor(
            3, 5, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));

    public static void main(String[] args) {
        int i=0;
        while(true) {
            try {
                orders.add("号码" + i);
                executorService.submit(new AddTask());
                Thread.sleep(300);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class AddTask implements Runnable{
        @Override
        public void run() {
            if(orders.size() > 0) {
                String order = orders.get(0);
                orders.remove(0);
                System.out.println(Thread.currentThread().getName() + "------------"+ order);
            }else {
                System.out.println(Thread.currentThread().getName() + "------------还未生成订单号");
            }
            System.out.println("订单号列表长度为："+orders.size());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
