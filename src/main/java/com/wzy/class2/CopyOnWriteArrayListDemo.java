package com.wzy.class2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wzy
 * @title: CopyOnWriteArrayListDemo
 * @description: CopyOnWriteArrayList
 * 在往集合中添加数据的时候，先拷贝存储的数组，然后添加元素到拷贝好的数组中，
 * 然后用现在的数组去替换成员变量的数组（就是get等读取操作读取的数组）。
 * 这个机制和读写锁是一样的，但是比读写锁有改进的地方，那就是读取的时候可以写入的 ，
 * 这样省去了读写之间的竞争，看了这个过程，你也发现了问题，同时写入的时候怎么办呢，当然果断还是加锁。
 *
 * 读多写少可以用copyonwritelist
 * @date 2019/7/12 15:31
 */
public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        List<String> lists = new CopyOnWriteArrayList<String>();
        Random random = new Random();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("A" + random.nextInt(10000));
                    }
                }
            };
            threads[i] = new Thread(task);
        }

        run(threads);

        System.out.println(lists.size());
    }

    private static void run(Thread[] threads) {
        long start = System.currentTimeMillis();
        Arrays.asList(threads).forEach(t -> t.start());
        Arrays.asList(threads).forEach(t->{
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
