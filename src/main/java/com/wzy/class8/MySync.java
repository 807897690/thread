package com.wzy.class8;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

/**
 * 双向链表
 */
public class MySync{
	private Node head;
	private Node tail;
	private int size;

    private static Unsafe unsafe = null;

    private static long stateOffset;

	static {
        try {
            unsafe = getUnsafe();
            stateOffset = unsafe.objectFieldOffset
                    (MySync.class.getDeclaredField("size"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

	private class Node {
		private Thread thread;
		private Node next;
		private Node pre;

		public Node (Thread thread) {
			this.thread = thread;
		}

		public Thread getThread() {
			return thread;
		}
	}

	public MySync() {
		size = 0;
		head = null;
		tail = null;
	}

	//添加尾部节点
	public void addWaitLock(Thread thread) {
		Node node = new Node(thread);
		if(size == 0) {
		    if(compareAndSet( 0 ,1)) {
                head = node;
                tail = node;
                size++;
                LockSupport.park(this);
            }
		}else {
            if(compareAndSet( size ,size+1)) {
                node.pre = tail;
                tail.next = node;
                tail = node;
                size++;
                LockSupport.park(this);
            }
		}
	}

	//删除链表头
	public Thread deleteHead() {
		Node temp = head;
		if(size != 0) {
			head = head.next;
			head.pre = null;
			size--;
		}
		return temp.getThread();
	}

	public Thread getHead() {
		Node temp = head;
		if(size != 0) {
			return temp.getThread();
		}else {
			return null;
		}
	}

	//获得链表的节点个数
	 public int getSize(){
		 return size;
	 }

	 //判断链表是否为空
	 public boolean isEmpty(){
		 return (size == 0);
	 }

    private static Unsafe getUnsafe() throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        return unsafe;
    }

    private boolean compareAndSet(int expect,int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

}
