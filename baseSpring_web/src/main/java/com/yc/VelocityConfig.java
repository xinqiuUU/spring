package com.yc;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*
    模板引擎配置
 */
@Configuration
public class VelocityConfig {
    @Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        return velocityEngine;
    }
    @Bean
    public VelocityContext velocityContext() {
        VelocityContext context = new VelocityContext();
        return context;
    }
    @Bean
    public Template depositeTemplate( @Autowired VelocityEngine velocityEngine) {
        Template template = velocityEngine.getTemplate("vms/deposite.vm", "utf-8");
        return template;
    }
    @Bean
    public Template withdrawTemplate( @Autowired VelocityEngine velocityEngine) {
        Template template = velocityEngine.getTemplate("vms/withdraw.vm", "utf-8");
        return template;
    }
    @Bean
    public Template transferTemplate( @Autowired VelocityEngine velocityEngine) {
        Template template = velocityEngine.getTemplate("vms/transfer.vm", "utf-8");
        return template;
    }
    @Bean
    public DateFormat fullDf(){
        DateFormat fullDf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return fullDf;
    }
    @Bean
    public DateFormat partDf(){
        DateFormat partDf = new SimpleDateFormat("yyyy年MM月dd日 北京时间hh时");
        return partDf;
    }


}
