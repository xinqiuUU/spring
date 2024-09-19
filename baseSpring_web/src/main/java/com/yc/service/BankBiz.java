package com.yc.service;

import com.yc.model.Account;

public interface BankBiz {

    // 开户
    public Account openAccount(double money);

    // 存款
    public Account deposit(int accountid , double money);

    // 取款
    public Account withdraw(int accountid, double money);

    // 转账
    public Account transfer(int fromid,  double money ,int toid);

    // 查询
    public Account findAccount(int accountid);
}
