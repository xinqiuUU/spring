package com.yc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    控制器：接收请求 ， 处理请求 ， 返回视图名
    springmvc 接到视图名  -》 InterpreterResolverViewResolver(内部资源视图解析器) 解析视图名  -》 视图对象
    "WEB_INF/views/"+"xxx"+".html"
 */
@Controller
public class HideController {

    //请求: /a
    @RequestMapping("/a")
    public String hide(){
        return "hide";
    }

}
