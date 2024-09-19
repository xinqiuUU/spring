package com.yc.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

public class TestMain {
    public static void main(String[] args) {
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C://cglib");
        OrderBizImpl target = new OrderBizImpl();

        MyInterceptor myInterceptor = new MyInterceptor(target);
        OrderBizImpl proxy = (OrderBizImpl) myInterceptor.createProxy();
        proxy.showOrder();
        proxy.findAllOrder();

    }
}
