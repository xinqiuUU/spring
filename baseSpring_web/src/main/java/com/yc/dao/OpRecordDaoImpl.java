package com.yc.dao;


import com.yc.model.OpRecord;
import com.yc.model.OpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ManagedResource( objectName = "com.yc.dao:name=OpRecordDaoImpl" )
public class OpRecordDaoImpl implements OpRecordDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void init(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    @Override
    public void insertOpRecord(OpRecord opRecord) {
        String sql = "insert into oprecord(accountid,opmoney,optime,optype,transferid) values(?,?,now(),?,?)";
        jdbcTemplate.update(sql, opRecord.getAccountid(), opRecord.getOpmoney(),
                opRecord.getOptype().getKey(), opRecord.getTransferid());
    }

    @Override
    public List<OpRecord> findOpRecord(int accountid) {
        String sql = "select * from oprecord where accountid=? order by optime desc";
        List<OpRecord> list = jdbcTemplate.query(sql, (rs,rowNum)->{
            OpRecord opRecord = new OpRecord();
            opRecord.setId( rs.getInt(1));
            opRecord.setAccountid( rs.getInt(2));
            opRecord.setOpmoney( rs.getDouble(3));
            opRecord.setOptime( rs.getString(4) );
            String optype =  rs.getString(5);
            if ( optype.equalsIgnoreCase("withdraw")){
                opRecord.setOptype(OpType.WITHDRAW);
            }else if ( optype.equalsIgnoreCase("deposit")){
                opRecord.setOptype(OpType.DEPOSIT);
            }else{
                opRecord.setOptype(OpType.TRANSFER);
            }
            opRecord.setTransferid(  rs.getInt(6)  );
            return opRecord;
        } , accountid);
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(int accountid, String opType) {
        String sql = "select * from oprecord where accountid=? and optype=? order by optime desc";
        List<OpRecord> list = jdbcTemplate.query(sql, (rs,rowNum)->{
            OpRecord opRecord = new OpRecord();
            opRecord.setId( rs.getInt(1));
            opRecord.setAccountid( rs.getInt(2));
            opRecord.setOpmoney( rs.getDouble(3));
            opRecord.setOptime( rs.getString(4) );
            String optype =  rs.getString(5); //从数据库中取出的是String，但OpRecord对象中是 枚举对象
            if ( optype.equalsIgnoreCase("withdraw")){
                opRecord.setOptype( OpType.WITHDRAW );
            }else if ( optype.equalsIgnoreCase("deposit")){
                opRecord.setOptype( OpType.DEPOSIT  );
            }else{
                opRecord.setOptype( OpType.TRANSFER );
            }
            opRecord.setTransferid(  rs.getInt(6)  );
            return opRecord;
        } , accountid);
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(OpRecord opType) {
        return null;
    }
}
