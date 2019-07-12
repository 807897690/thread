package com.wzy.class2;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal线程局部变量只对当前线程有效
 */
public class ThreadLocalDemo {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadLocal.set("aaaa");
                System.out.println(threadLocal.get());
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadLocal.get());
            }
        }.start();
    }

}
