package com.yc.project1.service;

import com.yc.project1.bean.BankAccount;

public interface BankBiz {

    public void showAll();

    public BankAccount findById(int id);

    public void update(BankAccount ba);

}
