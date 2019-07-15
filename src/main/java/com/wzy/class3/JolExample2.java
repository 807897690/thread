package com.wzy.class3;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: JolExample2
 * @description: TODO
 * @date 2019/7/15 17:02
 */
public class JolExample2 {

    static  A a = new A();
    public static void main(String[] args) {
        //jvm的信息
        out.println(VM.current().details());
        out.println(ClassLayout.parseInstance(a).toPrintable());
    }

}
