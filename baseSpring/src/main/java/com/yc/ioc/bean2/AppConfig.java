package com.yc.ioc.bean2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration //表示这是一个配置类
@ComponentScan(basePackages = "com.yc.bean2") //表示扫描com.yc.bean2包下的所有类 包括bean2下的包
public class AppConfig {


}
