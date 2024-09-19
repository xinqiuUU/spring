package com.yc.project1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankBiz {

    @Autowired
    private BankDao bankDao;

    public void showAll(){
        List<BankAccount> list = bankDao.findAll();
        for (BankAccount bankAccount : list) {
            System.out.println(bankAccount);
        }
    }

}
