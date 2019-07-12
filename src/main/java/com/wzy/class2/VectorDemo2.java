package com.wzy.class2;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @author wzy
 * @title: VectorDemo
 * @description: 有10000张火车票,同时有10个窗口对外售票
 * @date 2019/7/12 15:17
 */
public class VectorDemo2 {

    private static Vector<Integer> tickets = new Vector<Integer>();
    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add(i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    while(true){
                        synchronized (tickets) {
                            if(tickets.size() <= 0) {
                                break;
                            }
                            System.out.println("销售票编号:" + tickets.remove(0));
                        }
                    }
                }
            }.start();
        }
    }

}
