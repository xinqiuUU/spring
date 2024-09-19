package com.yc.aspects;

import com.yc.model.Account;
import com.yc.model.MessageBean;
import com.yc.service.BankBiz;
import com.yc.service.JmsmessageProcessor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
@Order(10)
public class BankBizEmailAspect {

    @Pointcut("execution(* com.yc.service.BankBizImpl.deposit(..))") // 所有以update开头的方法
    public void deposit() {}

    @Pointcut("execution(* com.yc.service.BankBizImpl.withdraw(..))") // 所有以update开头的方法
    public void withdraw() {}

    @Pointcut("execution(* com.yc.service.BankBizImpl.transfer(..))") // 所有以update开头的方法
    public void transfer() {}

    @Pointcut("deposit() || withdraw() || transfer()")
    public void all() {}

    @Autowired
    private BankBiz bankBiz;

    @Autowired
    private JmsmessageProcessor jmsmessageProcessor;

    // 增强类型:后置( after afterReturning , afterThrowing, finally)
    @AfterReturning("all()")
    public void sendEmail(  JoinPoint jp ) {  //连接点 即目标方法的参数
        Object[] obj = jp.getArgs(); // 第一个参数  账户id
        int accountid = Integer.parseInt(obj[0].toString());
        double money = Double.parseDouble(obj[1].toString());
        int toAccountid;
        if ( jp.getSignature().getName().equals("transfer") ){
            toAccountid = Integer.parseInt(obj[2].toString());
        }else {
            toAccountid = 0;
        }
        Account account = bankBiz.findAccount(  accountid );
        String email = account.getEmail();

        String methodName = jp.getSignature().getName();
        String opType = methodName;

        new Thread( ()->{
//            mailBiz.sendMail(email,"账户变动通知",info);
            jmsmessageProcessor.sendMessage(  new MessageBean(  account , money , toAccountid ,email , opType )  );

        }).start();
    }

    // 生成存款信息
    private String depositInfo( Account account ,double money){
        Date d = new Date();
        DateFormat fullDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat partDf = new SimpleDateFormat("yyyy-MM-dd 北京时间hh时");

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader,class", ClasspathResourceLoader.class.getName());
        ve.init();

        VelocityContext context = new VelocityContext();
        context.put("accountid", account.getAccountid());
        context.put("email", account.getEmail());
        context.put("subject", "存款操作");
        context.put("optime", fullDf.format(d));
        context.put("money",money );
        context.put("balance", account.getBalance());
        context.put("currentDate", partDf.format(d));

        Template template = ve.getTemplate("vms/deposite.vm", "utf-8");

        try(StringWriter writer = new StringWriter()){
            template.merge(context,writer);  //合并内容。替换占位符
            return writer.toString();       //从流获取最终的字符后
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";

    }
    // 生成取款信息
    private String withdrawInfo( Account account ,double money){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(date);
        String s = "尊敬的%s先生:\n\t您的账户%s于%s取出了%s元，当前余额为%s元.\n\t\t\t\t中国银行衡阳分行";
        return String.format(s , account.getAccountid() , account.getAccountid(), time, money, account.getBalance());
    }
    // 生成存款信息
    private String transferInfo( Account account ,double money , int toAccountid){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(date);
        String s = "尊敬的%s先生:\n\t您的账户%s于%s向账户%s转入了%s元，当前余额为%s元.\n\t\t\t\t中国银行衡阳分行";
        return String.format(s , account.getAccountid() , account.getAccountid(), time,toAccountid,  money, account.getBalance());
    }
}
