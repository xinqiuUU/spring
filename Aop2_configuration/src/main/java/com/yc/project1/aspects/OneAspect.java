package com.yc.project1.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
//@Order(1000) //1.设置优先级
public class OneAspect implements Ordered {

    @Pointcut("execution(* com.yc.project1.service.*.update*(..))") // 所有以update开头的方法
    public void a() {
    }

    @Around("a()")
    public Object doAround( ProceedingJoinPoint pjp ) {
        System.out.println("OneAspect 开始执行");
        //目标方法运行
        Object result = null;
        try {
            result = pjp.proceed();
        } catch ( Throwable e ) {
            throw new RuntimeException(e);
        }
        System.out.println("OneAspect 结束执行");
        return result;
    }

    //2.设置优先级
    @Override
    public int getOrder() {
        return 10000;
    }
}
