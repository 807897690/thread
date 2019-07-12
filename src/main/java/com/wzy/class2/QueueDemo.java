package com.wzy.class2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author wzy
 * @title: QueueDemo
 * @description: TODO
 * @date 2019/7/12 15:41
 */
public class QueueDemo {

    public static void main(String[] args) {
        Queue<String> strings = new ConcurrentLinkedQueue<String>();

        for (int i = 0; i < 10; i++) {
            //offer，类似于add方法，add会出一些问题，比如容量限制，
            //超出限制会抛异常，offer有返回值可以判断是否加成功了
            strings.offer("a" + i);
        }

        System.out.println(strings);

        System.out.println(strings.size());

        System.out.println(strings.poll());//拿了就没了
        System.out.println(strings.size());

        System.out.println(strings.peek());//用一下不删
        System.out.println(strings.size());
    }

}
