package com.yc.project1.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
    记录日志的切面
 */

@Aspect
@Component
public class LogAspect {

    // 定义切入点
    @Pointcut("execution(* com.yc.project1.service.*.update*(..))") // 所有以update开头的方法
    public void a() {
    }
    // 定义切入点
    @Pointcut("execution(* com.yc.project1.service.*.find*(..)) || execution(* com.yc.project1.service.*.show*(..))")
    public void b() {
    }
    // 定义切入点
    @Pointcut("a() || b()")  //匹配业务层中所有的操作
    public void c() {
    }

    //前置增强
    @Before("b()")  //或者 @Before("execution(* com.yc.project1.service.*.find*(..))")
    public void doAccessCheck() {
        System.out.println("权限验证");
    }

    //后置增强
    @AfterReturning( pointcut = "a()", returning = "returnValue")
    public void doLog( Object returnValue ) {
        System.out.println("记录日志");
        System.out.println(new Date());
        System.out.println( "切面拦截到的执行结果:" + returnValue);
    }

    //异常通知
    @AfterThrowing( pointcut = "a()", throwing = "ex")
    public void doExceptionLog( ArithmeticException ex ) {
        System.out.println("异常。。。");
        System.out.println(new Date());
        System.out.println( "切面拦截到的异常:" + ex);
    }

    //环绕通知
    @Around("b()")
    public Object doAround( ProceedingJoinPoint pjp ) {
        System.out.println("环绕通知");
        System.out.println( "环绕开始时间:"+ new Date());
        long  start = System.currentTimeMillis();
        //目标方法运行
        Object result = null;
        try {
            result = pjp.proceed();//目标方法运行
        } catch ( Throwable e ) {
            throw new RuntimeException(e);
        }
        System.out.println( "环绕结束时间:"+ new Date());
        System.out.println( "方法耗时:"+ (System.currentTimeMillis()-start));
        return result;
    }


}
