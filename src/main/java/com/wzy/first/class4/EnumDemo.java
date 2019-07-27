package com.wzy.first.class4;

/**
 * @author wzy
 * @title: EnumDemo
 * @description: 枚举只会被实例一次
 * 枚举相当于常量，只有再加载的时候实例化一次
 * @date 2019/7/27 11:01
 */
public enum EnumDemo {

    A,
    B,
    C,
    D;

    private static void m1() {
        System.out.println("method");
    }

    public static void main(String[] args) {
        A.m1();
        B.m1();
        C.m1();
        D.m1();
    }

}
