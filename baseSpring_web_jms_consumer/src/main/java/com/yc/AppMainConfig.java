package com.yc;


import org.springframework.context.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan
@EnableMBeanExport  // 开启MBean服务  启动待监控的类的机制 导出这些类的信息
public class AppMainConfig {

    // 使用 CommonsMultipartResolver
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760); // 10MB
        multipartResolver.setMaxInMemorySize(4096);   // 4KB
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
}
