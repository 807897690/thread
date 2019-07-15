package com.wzy.class3;

/**
 * @author wzy
 * @title: JolExample5
 * @description: TODO
 * @date 2019/7/15 17:09
 */
public class JolExample5 {

    public static void main(String[] args) throws Exception {
        /**
         * 25591ms
         * 3295ms
         */
        Thread.sleep(5000);
        A a = new A();
        long start = System.currentTimeMillis();
        //调用同步方法1000000000L 来计算1000000000L的++，对比偏向锁和轻量级锁的性能
        //如果不出意外，结果灰常明显
        for(int i=0;i<1000000000L;i++){
            a.parse();
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("%sms", end - start));

    }

}
