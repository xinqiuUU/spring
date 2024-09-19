package com.yc.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsonModel implements Serializable {
    private Integer code;
    private Object obj;
    private String error;
}
