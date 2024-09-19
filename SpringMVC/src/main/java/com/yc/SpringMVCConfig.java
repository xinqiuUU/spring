package com.yc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class SpringMVCConfig {

    /*
        视图解析器有很多种 ， 用来对各种资源的进行处理
        用于解析  JSP 或 HTML 等资源
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    /*
        springmvc 配置文件支持两种: xml
                                 java 注解快速配置
     */
    @Bean
    public WebMvcConfigurer createWebMvcConfigurer() {

        return new WebMvcConfigurer() {

            // 配置了静态资源的访问路径
            /*
                访问 /static/ 下的资源，都由 /static/ 下的资源处理
             */
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                //配置访问路径为 /static/ 下的资源 对应的 静态资源的路径为 /static/
                registry.addResourceHandler("/statics/**")
                        .addResourceLocations("classpath:/statics/");
            }

            // 配置了默认的 Servlet 处理，允许处理静态资源请求
            @Override
            public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
                //启用了默认的 Servlet 处理，允许处理静态资源请求
                configurer.enable();
            }
        };
    }
    // 配置文件上传解析器
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        multipartResolver.setMaxUploadSize(1024 * 1024 * 10); // 单个文件大小: 10M
        multipartResolver.setMaxInMemorySize(1024 * 1024 * 30);//一次请求上传的最大大小: 30M
        return multipartResolver;
    }
}
