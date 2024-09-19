package com.yc.service;

import com.yc.model.Account;
import com.yc.model.OpRecord;
import com.yc.model.OpType;
import com.yc.dao.AccountDao;
import com.yc.dao.OpRecordDao;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
@Transactional // 事务管理  Spring AOP 的代理模式  事务传播
@ManagedResource( objectName = "com.yc.service:name=BankBizImpl" )
public class BankBizImpl implements BankBiz{

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private OpRecordDao opRecordDao;

    // 开户
    @Override
    public Account openAccount(double money) {
        // 开户
        int accountid = accountDao.insert(money);
        // 流水记录
        OpRecord record = new OpRecord();
        record.setAccountid(accountid);
        record.setOpmoney(money);
        record.setOptype(OpType.DEPOSIT);
        opRecordDao.insertOpRecord( record );
        //构建返回值
        Account a = new Account();
        a.setAccountid(accountid);
        a.setBalance(money);
        log.debug( "新开户:"+accountid+" 余额:"+money );
        return a;
    }

    // 存款
    @CachePut(cacheNames = "users",key = "#accountid")
    @Override
    public Account deposit(int accountid, double money) {
        Account a = null;
        try {
            a = findAccount( accountid );// 账户是否存在
        }catch (Exception e){
            log.error( "账户不存在:",e );
            throw new RuntimeException( "查无此账户:"+accountid +",无法进行操作" );
        }
        a.setBalance( a.getBalance()+money );
        accountDao.update( accountid, a.getBalance() );
        //流水记录
        OpRecord record = new OpRecord();
        record.setAccountid(accountid);
        record.setOpmoney(money);
        record.setOptype(OpType.DEPOSIT);
        opRecordDao.insertOpRecord( record );
        log.debug( "存款:"+money+" 账户余额:"+a.getBalance() );
        return a;
    }

    // 取款
    @CachePut(cacheNames = "users",key = "#accountid")
    @Override
    public Account withdraw(int accountid, double money) {

        //0.取消调隐式事务提交

        // 1.账户是否存在
        Account a = null;
        try {
            a = findAccount( accountid );// 账户是否存在
        }catch (Exception e){
            log.error( "账户不存在:",e );
            throw new RuntimeException( "查无此账户:"+accountid +",无法进行操作" );
        }
        //2.流水记录
        OpRecord record = new OpRecord();
        record.setAccountid(accountid);
        record.setOpmoney(money);
        record.setOptype(OpType.WITHDRAW);
        opRecordDao.insertOpRecord( record );
        //3.取款
        a.setBalance( a.getBalance()-money );
        accountDao.update( accountid, a.getBalance() );
        log.debug( "取款:"+money+" 账户余额:"+a.getBalance() );

        // 4. 成功提交事务
        // 5. 异常回滚事务
        // 6. 设置事务自动提交

        return a;
    }

    // 转账
    @Override
    public Account transfer(int fromid,  double money , int toid) {
        deposit( toid, money); // 转入
        Account a = withdraw( fromid, money); // 转出
        return a;
    }

    // 查询
    @Cacheable(cacheNames = "users",key = "#accountid")  // cacheNames缓存的名称    key  缓存的键
    @Override
    public Account findAccount(int accountid) {
        Account a = accountDao.findById( accountid );
        return a;
    }
}
