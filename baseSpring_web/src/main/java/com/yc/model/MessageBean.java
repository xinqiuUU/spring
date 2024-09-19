package com.yc.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

//消息实体类
@Data
@NoArgsConstructor
@AllArgsConstructor
@Log4j
@ToString
public class MessageBean {
    private Account account;
    private Double money;
    private Integer toaccountid;
    private String email;
    private String opType;
}
