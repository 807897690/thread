package com.wzy.first.class6;

/**
 * @author wzy
 * @title: Test1
 * @description: TODO
 * @date 2019/7/29 21:10
 */
public class Test1 {

    private MyLock lock = new MyLock();

    private void A() {
        lock.lock();
        System.out.println("A");
        B();
        lock.unlock();
    }

    private void B() {
        lock.lock();
        System.out.println("B");
        lock.unlock();
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.A();
    }

}
