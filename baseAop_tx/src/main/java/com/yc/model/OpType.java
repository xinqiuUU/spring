package com.yc.model;

public enum OpType {

    WITHDRAW("withdraw",1),//取款
    DEPOSIT("deposite",2),//存款
    TRANSFER("transfer",3);//转账

    private String key;
    private int index;

    private OpType(String key,int index) {
        this.key = key;
        this.index = index;
    }

    public String getKey() {
        return key;
    }

    public int getIndex() {
        return index;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
