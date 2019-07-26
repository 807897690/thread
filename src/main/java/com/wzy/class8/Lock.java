package com.wzy.class8;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @author wzy
 * @title: Lock
 * @description: TODO
 * @date 2019/7/26 10:05
 */
public abstract class Lock implements Serializable {

    public abstract void lock();

    public abstract void unLock();

    protected volatile int lockStatus;

    private static Unsafe unsafe = null;

    private static long stateOffset;

    static {
        try {
            unsafe = getUnsafe();
            stateOffset = unsafe.objectFieldOffset
                    (Lock.class.getDeclaredField("lockStatus"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected boolean compareAndSet(int expect,int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    private static Unsafe getUnsafe() throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        return unsafe;
    }
}
