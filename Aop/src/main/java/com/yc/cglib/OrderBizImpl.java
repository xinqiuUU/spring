package com.yc.cglib;

//目标类: 待增强的类
//需求: 对这个类中的  showOrder, saveOrder, updateOrder 方法进行增强：
//        1.在上面三个方法运行前做权限验证
//        2.在上面三个方法运行后做日志记录。。。

import com.yc.jdkproxy.OrderBiz;

public class OrderBizImpl {
    public void showOrder() {
        System.out.println("show order");
    }

    public void saveOrder(int orderId) {
        System.out.println("save order");
    }

    public int updateOrder(int orderId, int money) {
        System.out.println("update order,更新订单"+orderId+"的金额为"+money);
        return 1;
    }

    public void findAllOrder() {
        System.out.println("find all order");
    }
}
