package com.yc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller
public class HelloWorldController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloWorld(Map<String, Object> model) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(new Date());
        model.put("currentTime", formattedDate);
        return "hello"; // 逻辑视图名称
    }
}
