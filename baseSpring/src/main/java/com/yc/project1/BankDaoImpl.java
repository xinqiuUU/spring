package com.yc.project1;

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
                ba.setBalance(rs.getInt(2));
                list.add(ba);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
