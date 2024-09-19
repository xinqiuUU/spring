package com.yc.自定义实现jdk动态代理;

public interface OrderBiz {
    public void showOrder();
    public void saveOrder( String orderId );
    public int updateOrder(String orderId , String money);

    public void findAllOrder();


}
