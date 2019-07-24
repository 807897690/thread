package com.wzy.class7;

import com.wzy.class4.A;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: Example
 * @description: TODO
 * @date 2019/7/23 20:58
 */
public class Example {

    public static void main(String[] args) throws Exception {
        Thread.sleep(6000);
//        a = new A();
        List<A> list = new ArrayList<>();
//        List<B> list2 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new A());
//            list2.add(new B());
        }

        Thread t1 = new Thread() {
            String name = "1";

            public void run() {
                out.printf(name);
                for (A a : list) {
                    synchronized (a) {
                        if (a == list.get(10))
                            out.println("t1 预期是偏向锁"+10 + ClassLayout.parseInstance(a).toPrintable());//偏向锁

                    }

                }
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        t1.start();
        Thread.sleep(5000);
        out.println("main 预期是偏向锁 同步结束后不撤销偏向锁，在下次使用的时候进行撤销偏向并膨胀为轻量级锁或者重锁 或重偏向"+10 + ClassLayout.parseInstance(list.get(10)).toPrintable());//偏向锁

        Thread t2 = new Thread() {
            String name = "2";

            public void run() {
                out.printf(name);

                for(int i = 0;i<100;i++){
                    A a = list.get(i);
                    if(i==20){
                        a= list.get(9);
                    }
                    synchronized (a) {
                        if ( a == list.get(10)) {
                            out.println("t2 i=10 get(1)预期是无锁" +  ClassLayout.parseInstance(list.get(1)).toPrintable());//偏向锁
                            out.println("t2 i=10 get(10) 预期轻量级锁 " + i + ClassLayout.parseInstance(a).toPrintable());//偏向锁
                        }
                        if ( a == list.get(19)) {
                            out.println("t2  i=19  get(10)预期是无锁" + 10 + ClassLayout.parseInstance(list.get(10)).toPrintable());//无锁 不可偏向
                            out.println("t2  i=19  get(19) 满足重偏向条件20 预期偏向锁 " + i + ClassLayout.parseInstance(a).toPrintable());//偏向锁

                            out.println("t2  i=19  get(40) 未同步到的对象，依然偏向t1 " + i + ClassLayout.parseInstance(list.get(40)).toPrintable());//偏向锁
                        }
                        if (i == 20) {
                            out.println("t2  i=20  get(9)预期是轻量级锁，因为无锁 不可偏向的标识不可重新更改为可偏向状态，所以再次锁定之前的对象依然使用了轻量级锁" + 10 + ClassLayout.parseInstance(a).toPrintable());//轻量级锁


                        }

                    }



                }



                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        };
        t2.start();
    }

}
