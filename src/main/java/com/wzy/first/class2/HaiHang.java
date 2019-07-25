package com.wzy.first.class2;

/**
 * @author wzy
 * @title: HaiHang
 * @description: TODO
 * @date 2019/7/25 16:39
 */
public class HaiHang {

    private static int tickets = 300;

    public synchronized static void getTicket(String name) {
        if(tickets == 0) {
            System.out.println("亲爱的用户"+name+",对不起，08：00由上海飞往北京的海航航空座机已全部售罄!");
            return;
        }
        System.out.println(name+"访问海航航空，剩余"+tickets+"张票,购买成功，票价1024元，出发时间08：00");
        tickets -- ;
    }

}
