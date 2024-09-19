package com.yc.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 日志拦截器
 */
@Component // 注册到Spring容器
public class RequestLoggingInterceptor implements HandlerInterceptor {

    // 在请求处理之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String clientIp = request.getRemoteAddr();
        System.out.println("Incoming request - URI: " + uri + ", Method: " + method + ", Client IP: " + clientIp);
        return true; // 返回true继续执行后续的拦截器或处理器
    }

    // 在请求处理之后，视图渲染之前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Request processing completed - URI: " + request.getRequestURI());
    }

    // 在整个请求结束之后，即视图渲染完成之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Request completed - URI: " + request.getRequestURI());
    }
}
