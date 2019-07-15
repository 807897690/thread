package com.wzy.class3;

/**
 * @author wzy
 * @title: A
 * @description: TODO
 * @date 2019/7/14 20:36
 */
public class A {

    int i=0;
    public synchronized void parse(){
        i++;
        JolExample7.countDownLatch.countDown();
    }


}
