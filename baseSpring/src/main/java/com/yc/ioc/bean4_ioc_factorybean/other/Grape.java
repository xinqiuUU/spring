package com.yc.ioc.bean4_ioc_factorybean.other;

public class Grape implements Fruit{
    private int id;


    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
