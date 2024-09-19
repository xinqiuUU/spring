package com.yc.project1.service;

import com.yc.project1.bean.BankAccount;
import com.yc.project1.dao.BankDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BankBizImpl implements BankBiz{

    @Autowired
    private BankDao bankDao;

    @Override
    public void showAll() {
        List<BankAccount> list = this.bankDao.findAll();
        for ( BankAccount account : list){
            System.out.println(account);
        }

    }
    @Override
    public BankAccount findById(int id) {
        return this.bankDao.findById(id);
    }

    public void update(BankAccount account) {
        int result = 0;
        System.out.println("执行更新方法。。。。。。。");
        if ( result == 0){
            throw new ArithmeticException("算数异常,计算除0了");
        }
        this.bankDao.update( account );
    }
}
