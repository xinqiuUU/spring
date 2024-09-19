package com.yc.di.bean2_autowired;

import org.springframework.stereotype.Repository;

@Repository
public class OrderOceanDao implements OrderDao{
    @Override
    public void addOrder() {
        System.out.println("OrderOceanDao添加订单");
    }
}
