package com.wzy.first.class3;

import java.util.concurrent.TimeUnit;

/**
 * @author wzy
 * @title: VolitileDemo
 * @description:
 * （1）保证可见性
 *      对共享变量的修改，其他的线程马上能感知到
 *      不能保证原子性  读、写、（i++）
 * （2）保证有序性
 *  重排序（重排序后对单线程没有影响，对多线程有影响）
 *      Happens-before
 *          对于volatile修饰的变量：
 *              （1）volatile之前的代码不能调整到他的后面
 *              （2）volatile之后的代码不能调整到他的前面（as if seria）
 *              （3）霸道（位置不变化）s
 * @date 2019/7/26 15:48
 */
public class VolitileDemo {

    private final static int MAX = 50;

    private static volatile int initValue = 0;


    public static void main(String[] args) {
        new Thread(() ->{
            int localValue = initValue;
            while(localValue < MAX) {
                if(localValue != initValue) {
                    System.out.println("data was change" + initValue);
                    localValue = initValue;
                }
            }
        }, "reader").start();

        new Thread(() ->{
            int localValue = initValue;
            while(localValue < MAX) {
                localValue ++;
                System.out.println("data was update" + localValue);
                initValue = localValue;
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "update").start();
    }

}
