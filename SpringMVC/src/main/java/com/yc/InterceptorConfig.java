package com.yc;

import com.yc.Interceptor.RequestLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
    配置拦截器
    拦截器的作用： 对请求进行拦截，对请求进行预处理，对请求进行后处理
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    // 定义拦截器
    @Bean
    public RequestLoggingInterceptor requestLoggingInterceptor() {
        return new RequestLoggingInterceptor();
    }

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLoggingInterceptor());
    }
}
