package com.yc.project1.bean;

import lombok.Data;

@Data
public class BankAccount {
    private int id;
    private Double balance;

    public BankAccount() {
    }

    public BankAccount(int id, Double balance) {
        this.id = id;
        this.balance = balance;
    }
}
