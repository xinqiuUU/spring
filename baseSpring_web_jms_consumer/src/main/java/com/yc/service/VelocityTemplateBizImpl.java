package com.yc.service;

import com.yc.model.Account;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.text.DateFormat;
import java.util.Date;

@Component
public class VelocityTemplateBizImpl {
    @Autowired
    private VelocityContext context;// 模板上下文
    @Autowired
    private VelocityEngine velocityEngine; // 模板引擎
    @Autowired
    @Qualifier("depositeTemplate") // 模板名称depositeTemplate注入
    private Template depositeTemplate;
    @Autowired
    @Qualifier("withdrawTemplate") // 模板名称withdrawTemplate注入
    private Template withdrawTemplate;
    @Autowired
    @Qualifier("transferTemplate") // 模板名称transferTemplate注入
    private Template transferTemplate;

    @Autowired
    @Qualifier("fullDf") // 模板名称withdrawTemplate注入
    private DateFormat fullDf;
    @Autowired
    @Qualifier("partDf") // 模板名称withdrawTemplate注入
    private DateFormat partDf;
    public String genEmailContent(String opType, Account account, double money, int toaccountid) {
        String info;
        if ( opType.equals("deposit")){
            info = depositInfo( account, money );
        }else if (opType.equals("withdraw")){
            info = withdrawInfo( account, money );
        }else if (opType.equals("transfer")){
            info = transferInfo( account, money, toaccountid );
        }else {
            info = "";
        }
        return info;
    }
    // 生成存款信息
    private String depositInfo( Account account ,double money){
        Date d = new Date();

        //托管了
//        VelocityContext context = new VelocityContext();
        context.put("accountid", account.getAccountid());
        context.put("email", account.getEmail());
        context.put("subject", "存款操作");
        context.put("optime", fullDf.format(d));
        context.put("money",money );
        context.put("balance", account.getBalance());
        context.put("currentDate", partDf.format(d));

        try(StringWriter writer = new StringWriter()){
            depositeTemplate.merge(context,writer);  //合并内容。替换占位符
            return writer.toString();       //从流获取最终的字符后
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";

    }
    // 生成取款信息
    private String withdrawInfo( Account account ,double money){
        Date date = new Date();
        //托管了
//        VelocityContext context = new VelocityContext();
        context.put("accountid", account.getAccountid());
        context.put("email", account.getEmail());
        context.put("subject", "取款操作通知");
        context.put("optime", fullDf.format(date));
        context.put("money",money );
        context.put("balance", account.getBalance());
        context.put("currentDate", partDf.format(date));

        try(StringWriter writer = new StringWriter()){
            withdrawTemplate.merge(context,writer);  //合并内容。替换占位符
            return writer.toString();       //从流获取最终的字符后
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    // 生成存款信息
    private String transferInfo( Account account ,double money , int toAccountid){
        Date date = new Date();
        //托管了
//        VelocityContext context = new VelocityContext();
        context.put("accountid", account.getAccountid());
        context.put("email", account.getEmail());
        context.put("subject", "转账操作通知");
        context.put("optime", fullDf.format(date));
        context.put("money",money );
        context.put("balance", account.getBalance());
        context.put("currentDate", partDf.format(date));
        context.put("toaccountid", toAccountid);

        try(StringWriter writer = new StringWriter()){
            transferTemplate.merge(context,writer);  //合并内容。替换占位符
            return writer.toString();       //从流获取最终的字符后
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
