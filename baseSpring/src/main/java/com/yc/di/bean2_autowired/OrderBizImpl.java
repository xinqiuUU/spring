package com.yc.di.bean2_autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

@Service
public class OrderBizImpl implements OrderBiz{

//    @Autowired  // 自动装配 按照类型装配
////    @Qualifier("orderOceanDao")
    @Inject
//    @Resource(name = "orderOceanDao")
    @Named("orderOceanDao")
    private OrderDao dao; // dao层的beanid  orderOceanDao  orderMysqlDao

    public void setOrderDao(OrderDao dao) {
        this.dao = dao;
    }

    @Override
    public void makeOrder() {
        System.out.println("业务层orderBiz调用dao层");
        dao.addOrder();
    }
}
