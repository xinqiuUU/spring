package com.yc.dao;

import com.yc.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository //表明这是一个Dao持久层的类  spring提供了自动化异常类型类型转换的功能 将SQLException转换为RuntimeException
public class AccountDaoimpl implements AccountDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void init(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    @Override
    public int insert(double money) {
        String sql = "insert into accounts(balance) values(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result=jdbcTemplate.update(connection->{
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{   "accountid" });
            ps.setString(1,money+"");
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int update(int accountid, double balance) {
        String sql = "update accounts set balance =? where accountid =?";
        int update = jdbcTemplate.update(sql, balance, accountid);
        return update;
    }

    @Override
    public int findCount() {
        String sql = "select count(*) from accounts";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public List<Account> findAll() {
        String sql = "select * from accounts";
        List<Account> list = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Account account = new Account();
            account.setAccountid(rs.getInt("accountid"));
            account.setBalance(rs.getDouble("balance"));
            return account;
        });
        return list;
    }

    //根据id查询账户
    @Override
    public Account findById(int accountid) {
        String sql = "select * from accounts where accountid =?";
        Account a = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Account account = new Account();
            account.setAccountid(rs.getInt(1));
            account.setBalance(rs.getDouble(2));
            return account;
        }, accountid );
        if ( a == null){
            throw new RuntimeException("没有找到该账户"); //dao层的异常会被spring转换成DataAccessException
        }
        return a;
    }
}
