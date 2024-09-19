package com.yc.dao;

import com.yc.model.OpRecord;

import java.util.List;

public interface OpRecordDao {
    //设计日志的添加接口方法
    public void insertOpRecord(OpRecord opRecord);

    //根据账号查询日志
    public List<OpRecord> findOpRecord(int accountid);

    public List<OpRecord> findOpRecord( int accountid , String opType);

    //待扩展的查询方法
    public List<OpRecord> findOpRecord( OpRecord opType);

}
