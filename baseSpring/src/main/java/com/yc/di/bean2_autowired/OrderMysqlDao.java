package com.yc.di.bean2_autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

//@Primary
@Repository
public class OrderMysqlDao implements OrderDao{
    @Override
    public void addOrder() {
        System.out.println("OrderMysqlDao添加订单");
    }
}
