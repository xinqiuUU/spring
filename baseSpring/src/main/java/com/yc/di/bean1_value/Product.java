package com.yc.di.bean1_value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:product.properties")
@Component
public class Product {
//    private int id=1;
//    private String name="iphone";
//    private double price=10000;

    @Value("${id}")
    private int id;
    @Value("${pname}")
    private String pname;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                '}';
    }

}
