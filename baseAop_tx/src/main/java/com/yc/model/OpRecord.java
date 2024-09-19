package com.yc.model;

import lombok.Data;

//流水记录
@Data
public class OpRecord {
    private int id;
    private int accountid;
    private double opmoney;
    private String optime;
    private OpType optype; //枚举类型
    private Integer transferid;
}
