package com.wzy.class3;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * @author wzy
 * @title: JolExample1
 * @description: TODO
 * @date 2019/7/14 20:37
 */
public class JolExample1 {

    static A a = new A();

    public static void main(String[] args) {
        out.println(VM.current().details());
        out.println(ClassLayout.parseInstance(a).toPrintable());
    }

}
