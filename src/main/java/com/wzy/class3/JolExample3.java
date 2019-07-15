package com.wzy.class3;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: JolExample3
 * @description: TODO
 * @date 2019/7/15 17:03
 */
public class JolExample3 {

    public static void main(String[] args) throws Exception {
        A a= new A();
        out.println("befor hash");
        //没有计算HASHCODE之前的对象头
        out.println(ClassLayout.parseInstance(a).toPrintable());
        //JVM 计算的hashcode
        out.println("jvm------------0x"+Integer.toHexString(a.hashCode()));
        HashUtil.countHash(a);
        //当计算完hashcode之后，我们可以查看对象头的信息变化
        out.println("after hash");
        out.println(ClassLayout.parseInstance(a).toPrintable());

    }

}
