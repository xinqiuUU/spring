package com.yc.ioc.bean1;


import org.apache.log4j.Logger;

public class Address {

    private Logger logger =Logger.getLogger( Address.class.getName()  );
    private String province;
    private String city;
    private String district;

    public Address() {
        System.out.println("spring调用了Address的无参构造方法");
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                '}';
    }

    public Address(String province, String city, String district) {
        this.province = province;
        this.city = city;
        this.district = district;
    }


    public void setProvince(String province) {
        logger.info("spring调用了Address的setProvince方法");
        this.province = province;
    }

    public void setCity(String city) {
        logger.info("spring调用了Address的setCity方法");
        this.city = city;
    }

    public void setDistrict(String district) {
        logger.info("spring调用了Address的setDistrict方法");
        this.district = district;
    }
}
