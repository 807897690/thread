package com.wzy.class8;

import java.util.concurrent.locks.LockSupport;

/**
 * @author wzy
 * @title: MyLock
 * @description: TODO
 * @date 2019/7/25 21:14
 */
public class MyLock extends Lock{

    private MySync mySync = new MySync();

    private static Thread nowThread;

    /**
     * 加锁
     */
    public void lock() {
        while(!tryAccquire(1)) {
            System.out.println("当前加入等待线程名称："+Thread.currentThread().getName()+"----等待队列大小："+mySync.getSize());
            mySync.addWaitLock(Thread.currentThread());
        }
    }

    /**
     * 解锁
     */
    public void unLock() {
        lockStatus = 0;
        System.out.println("释放线程:"+Thread.currentThread().getName());
        setNowThread(mySync.getHead());
    }

    /**
     * 尝试加锁
     * @param accquire
     * @return
     */
    private boolean tryAccquire(int accquire) {
        int c = getLockStatus();
        if(c == 0) {
            if(mySync.isEmpty()) {
                return compareAndSet(0, accquire);
            }else {
                if(getNowThread() == mySync.getHead()) {
                    mySync.deleteHead();
                    return true;
                }
            }
        }
        return false;
    }

    protected int getLockStatus() {
        return lockStatus;
    }

    protected static void setNowThread(Thread thread) {
        nowThread = thread;
        LockSupport.unpark(nowThread);
    }

    protected Thread getNowThread() {
        return nowThread;
    }

}
