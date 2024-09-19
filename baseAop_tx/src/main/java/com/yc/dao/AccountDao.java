package com.yc.dao;

import com.yc.model.Account;

import java.util.List;

public interface AccountDao {

    //添加Account账号
    public int insert(double money);

    //根据账号将money更新
    public int update( int accountid , double money);

    //根据账号查询money
    public int findCount();

    //查询所有账户
    public List<Account> findAll();

    //根据id查询账户
    public Account findById(int accountid);


}
