package com.wzy.first.class2;

/**
 * @author wzy
 * @title: Nanfang
 * @description: TODO
 * @date 2019/7/25 16:39
 */
public class Nanfang {

    private static int tickets = 200;

    public synchronized static void getTicket(String name) {
        if(tickets == 0) {
            System.out.println("亲爱的用户"+name+",对不起，07：15由上海飞往北京的南方航空座机已全部售罄!");
            return;
        }
        System.out.println(name+"访问南方航空，剩余"+tickets+"张票,购买成功，票价1100元，出发时间07：15");
        tickets -- ;
    }

}
