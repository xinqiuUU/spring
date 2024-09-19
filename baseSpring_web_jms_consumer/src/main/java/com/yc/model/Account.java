package com.yc.model;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

// 账户
@ManagedResource( objectName = "com.yc.bean:name=Account" )
@Component
public class Account implements Serializable {
    private int accountid;
    private double balance;
    private String email;

    @ManagedOperation
    @ManagedOperationParameter( name = "accountid", description = "账户编号")
    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    @ManagedOperationParameter( name = "balance", description = "账户余额")
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManagedAttribute
    public int getAccountid() {
        return accountid;
    }

    @ManagedAttribute
    public double getBalance() {
        return balance;
    }
    @ManagedAttribute
    public String getEmail() {
        return email;
    }
}
