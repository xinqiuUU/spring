package com.yc.ioc.bean1;

import org.apache.log4j.Logger;

public class Student {
    private Logger logger =Logger.getLogger( Address.class.getName()  );

    private int id;
    private String name;

    Address address;

    public Student() {
    }

    public Student(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }


    public void setId(int id) {
        logger.info("spring调用了Student的setId方法");
        this.id = id;
    }

    public void setName(String name) {
        logger.info("spring调用了Student的setName方法");
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
