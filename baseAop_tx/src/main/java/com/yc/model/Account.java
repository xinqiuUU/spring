package com.yc.model;

import lombok.Data;

import java.io.Serializable;

// 账户
@Data
public class Account implements Serializable {
    private int accountid;
    private double balance;

}
