package com.yc.ioc.bean4_ioc_factorybean.other;

/*
    此类是别的公式或组织提供的类  原来没有加注解
    现在
 */
public class Banana implements Fruit{
    private int id;

    public Banana() {
    }

    public Banana(int id) {
        this.id = id;
    }

    @Override
    public void setId(int id) {
        this.id = id;

    }

    @Override
    public int getId() {
        return id;
    }

}
