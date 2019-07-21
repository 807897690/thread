package com.wzy.class6;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wzy
 * @title: Demo_ParalleComputing
 * @description: TODO
 * @date 2019/7/21 20:56
 */
public class Demo1_ParalleComputing {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> list = getPrime(1, 200000);
        System.out.println("list.size:" + list.size() + "   " + (System.currentTimeMillis() - start));
        final Integer cpu = 4;

        ExecutorService service = Executors.newFixedThreadPool(cpu);
        MyTask task1 = new MyTask(1, 50000);
        MyTask task2 = new MyTask(50001, 100000);
        MyTask task3 = new MyTask(100001, 150000);
        MyTask task4 = new MyTask(150001, 200000);

        Future<List<Integer>> f1 = service.submit(task1);
        Future<List<Integer>> f2 = service.submit(task2);
        Future<List<Integer>> f3 = service.submit(task3);
        Future<List<Integer>> f4 = service.submit(task4);

        long start1 = System.currentTimeMillis();
        List<Integer> newList = new LinkedList<>();
        newList.addAll(f1.get());
        newList.addAll(f2.get());
        newList.addAll(f3.get());
        newList.addAll(f4.get());
        System.out.println("list.size:" + newList.size() + "   " + (System.currentTimeMillis() - start1));
        service.shutdown();
    }

    static class MyTask implements Callable {

        private Integer start;

        private Integer end;

        public MyTask(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Object call() throws Exception {
            return getPrime(start, end);
        }
    }

    public static List<Integer> getPrime(Integer start, Integer end) {
        List<Integer> list = new LinkedList<>();
        for(int i = start; i <= end; i++) {
            if(isPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }

    public static boolean isPrime(Integer num) {
        for(int i=2; i<= Math.sqrt(num); i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }


}
