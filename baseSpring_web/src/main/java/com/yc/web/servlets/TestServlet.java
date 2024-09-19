package com.yc.web.servlets;

import com.yc.service.BankBiz;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test") // 注解的方式配置servlet
public class TestServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//        System.out.println("context:"+applicationContext);
        BankBiz bankBiz = context.getBean(BankBiz.class);
//        // 进行相关操作
        bankBiz.deposit(1, 9999);

    }

}
