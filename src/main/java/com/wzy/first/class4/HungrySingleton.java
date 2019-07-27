package com.wzy.first.class4;

/**
 * @author wzy
 * @title: HungrySingleton
 * @description: 饿汉式单例模式
 * 1、线程安全，加载的时候就已经生成了实例，类的静态属性存放于方法区，多个线程访问的都是同一个对象
 * 2、不是懒加载，
 * @date 2019/7/27 10:08
 */
public class HungrySingleton {

    private static HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {

    }

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }

    public static void main(String[] args) {
        HungrySingleton hungrySingleton = HungrySingleton.getInstance();
        System.out.println(hungrySingleton);
        HungrySingleton hungrySingleton1 = HungrySingleton.getInstance();
        System.out.println(hungrySingleton1);
    }

}
