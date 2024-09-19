package com.yc.web.servlets;

import com.yc.model.Account;
import com.yc.service.BankBiz;
import com.yc.web.servlets.model.JsonModel;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account.action")
public class AccountServlet extends BaseServlet{

    // 存款
    protected void deposit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int accountId = Integer.parseInt(req.getParameter("accountId"));
        double money = Double.parseDouble(req.getParameter("money"));
        //要从spring容器中获取业务逻辑对象
        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
//        String[] beanNames = ac.getBeanDefinitionNames();
//        for (String beanName : beanNames){
//            System.out.println(  "spring:" +  beanName);
//        }

        BankBiz biz = (BankBiz) ac.getBean("bankBizImpl");

        Account a = biz.deposit(accountId, money);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj( a );
        super.writeJson(jm, resp);

    }
    // 取款
    protected void withdraw(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int accountId = Integer.parseInt(req.getParameter("accountId"));
        double money = Double.parseDouble(req.getParameter("money"));
        //要从spring容器中获取业务逻辑对象
        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);

        BankBiz biz = (BankBiz) ac.getBean("bankBizImpl");
        Account a = biz.withdraw(accountId, money);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj( a );
        super.writeJson(jm, resp);

    }
    // 转账
    protected void transfer(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int fromId = Integer.parseInt(req.getParameter("fromId"));
        int toId = Integer.parseInt(req.getParameter("toId"));
        double money = Double.parseDouble(req.getParameter("money"));
        //要从spring容器中获取业务逻辑对象
        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);

        BankBiz biz = (BankBiz) ac.getBean("bankBizImpl");
        Account a = biz.transfer(fromId, money, toId);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj( a );
        super.writeJson(jm, resp);
    }
    // 查询
    protected void findAccount(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int accountId = Integer.parseInt(req.getParameter("accountId"));
        //要从spring容器中获取业务逻辑对象
        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);

        BankBiz biz = (BankBiz) ac.getBean("bankBizImpl");
        Account a = biz.findAccount(accountId);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj( a );
        super.writeJson(jm, resp);
    }
    // 开户
    protected void openAccount(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        double money = Double.parseDouble(req.getParameter("money"));
        //要从spring容器中获取业务逻辑对象
        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);

        BankBiz biz = (BankBiz) ac.getBean("bankBizImpl");
        Account a = biz.openAccount(money);
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj( a );
        super.writeJson(jm, resp);
    }

    //判断是否有邮箱
    protected void checkEmail(HttpServletRequest req , HttpServletResponse resp ,int accountId) throws IOException {
        //要从spring容器中获取业务逻辑对象
        ServletContext application = req.getServletContext();
        WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);

        BankBiz biz = (BankBiz) ac.getBean("bankBizImpl");
        Account a = biz.findAccount(accountId);
        if ( a.getEmail()==null || a.getEmail().equals("")){
            JsonModel jm = new JsonModel();
            jm.setCode(0);
            jm.setError( "请先添加邮箱。。。" );
            super.writeJson(jm, resp);
        }
    }

}
