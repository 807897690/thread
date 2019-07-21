package com.wzy.class6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wzy
 * @title: Demo6_WorkStealingPool
 * @description: TODO
 * @date 2019/7/21 22:14
 */
public class Demo6_WorkStealingPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newWorkStealingPool();
        /**
         * 输出当前cpu核数
         */
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        TimeUnit.SECONDS.sleep(90);
    }

    static class R implements Runnable{

        int time;

        public R (int t){
            this.time = t;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + " " + Thread.currentThread().getName());
        }

    }

}
