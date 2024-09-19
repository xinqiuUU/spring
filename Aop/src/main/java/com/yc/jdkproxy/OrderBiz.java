package com.yc.jdkproxy;

public interface OrderBiz {
    public void showOrder();
    public void saveOrder( int orderId );
    public int updateOrder(int orderId , int money);

    public void findAllOrder();


}
