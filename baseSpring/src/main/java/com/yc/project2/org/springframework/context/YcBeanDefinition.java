package com.yc.project2.org.springframework.context;

import lombok.Data;

/*
    bean的元信息类
 */
@Data
public class YcBeanDefinition {
    private boolean isLazy;
    private String scope="singleton";
    private boolean isPrimary;
    private String classInfo; //***这个是此类的全路径 通过它能创建这个类的实例

}
