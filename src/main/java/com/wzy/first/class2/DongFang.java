package com.wzy.first.class2;

/**
 * @author wzy
 * @title: DongFang
 * @description: TODO
 * @date 2019/7/25 16:39
 */
public class DongFang {

    private static int tickets = 400;

    public synchronized static void getTicket(String name) {
        if(tickets == 0) {
            System.out.println("亲爱的用户"+name+",对不起，09：00由上海飞往北京的东方航空座机已全部售罄!");
            return;
        }
        System.out.println(name+"访问东方航空，剩余"+tickets+"张票,购买成功，票价1000元，出发时间09：00");
        tickets -- ;
    }

}
