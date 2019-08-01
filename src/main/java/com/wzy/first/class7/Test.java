package com.wzy.first.class7;

/**
 * @author wzy
 * @title: Test
 * @description: TODO
 * @date 2019/8/1 10:13
 */
public class Test {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    public static void main(String[] args) {
        System.out.println("COUNT_BITS:"+COUNT_BITS+":"+Integer.toBinaryString(COUNT_BITS));
        System.out.println("CAPACITY:"+CAPACITY+":"+Integer.toBinaryString(CAPACITY));
        System.out.println("RUNNING:"+RUNNING+":"+Integer.toBinaryString(RUNNING));
        System.out.println("SHUTDOWN:"+SHUTDOWN+":"+Integer.toBinaryString(SHUTDOWN));
        System.out.println("STOP:"+STOP+":"+Integer.toBinaryString(STOP));
        System.out.println("TIDYING:"+TIDYING+":"+Integer.toBinaryString(TIDYING));
        System.out.println("TERMINATED:"+TERMINATED+":"+Integer.toBinaryString(TERMINATED));
    }

}
