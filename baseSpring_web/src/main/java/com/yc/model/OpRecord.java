package com.yc.model;

import lombok.Data;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

//流水记录
@Data
@ManagedResource( objectName = "com.yc.bean:name=OpRecord" )
@Component
public class OpRecord {
    private int id;
    private int accountid;
    private double opmoney;
    private String optime;
    private OpType optype; //枚举类型
    private Integer transferid;
}
