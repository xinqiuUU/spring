package com.yc.project1.dao;

import com.yc.project1.bean.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BankDaoImpl implements BankDao{
    @Autowired
    private DataSource dataSource;
    @Override
    public List<BankAccount> findAll() {
        List<BankAccount> list = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from bank");
        ){
            ResultSet rs = ps.executeQuery();
            while ( rs.next() ){
                BankAccount ba = new BankAccount();
                ba.setId(rs.getInt(1));
                ba.setBalance(rs.getDouble(2));
                list.add(ba);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public BankAccount findById(int id) {
        BankAccount ba = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from bank where id =?");
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ba = new BankAccount();
                ba.setId(rs.getInt(1));
                ba.setBalance(rs.getDouble(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ba;
    }
    @Override
    public void update(BankAccount account) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("update bank set balance =? where id =?");
        ) {
            ps.setDouble(1, account.getBalance());
            ps.setInt(2, account.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
