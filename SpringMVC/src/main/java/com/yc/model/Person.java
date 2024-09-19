package com.yc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor //无参构造函数
@AllArgsConstructor //全参构造函数
public class Person {
    private int id;
    private String name;
    private int age;
}
