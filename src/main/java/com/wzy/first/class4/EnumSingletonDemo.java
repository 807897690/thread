package com.wzy.first.class4;

/**
 * @author wzy
 * @title: EnumSingletonDemo
 * @description: 枚举单例
 * 1、懒加载
 * @date 2019/7/27 11:00
 */
public class EnumSingletonDemo {

    private EnumSingletonDemo() {

    }

    private enum EnumSingleton {

        INSTANCE;

        private EnumSingletonDemo instance;

        EnumSingleton() {
            instance = new EnumSingletonDemo();
        }

        public EnumSingletonDemo getInstance() {
            return instance;
        }
    }

    public EnumSingletonDemo getInstance() {
        return EnumSingleton.INSTANCE.getInstance();
    }

}
