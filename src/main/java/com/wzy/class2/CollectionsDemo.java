package com.wzy.class2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wzy
 * @title: CollectionsDemo
 * @description: collections是java里面一个集合处理类，里面有给容器加锁的方法，通过调用api可以返回一个加了锁的容器。
 * @date 2019/7/12 15:37
 */
public class CollectionsDemo {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        List<String> synchronizedList = Collections.synchronizedList(arrayList);
    }

}
