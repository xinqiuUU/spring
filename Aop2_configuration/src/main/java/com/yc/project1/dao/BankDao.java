package com.yc.project1.dao;

import com.yc.project1.bean.BankAccount;

import java.util.List;

public interface BankDao {
    public List<BankAccount> findAll();
    public BankAccount findById(int id);
    public void update(BankAccount account);
}
