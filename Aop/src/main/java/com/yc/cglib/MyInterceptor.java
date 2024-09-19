package com.yc.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyInterceptor implements MethodInterceptor {

    private Object target;

    public MyInterceptor(Object target) {
        this.target = target;
    }

    //对外提供一个方法用于创建一个代理类的对象
    public Object createProxy() {
        //利用cglib来创建代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass()); //设置父类
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //1.什么时候调用增强
        if (method.getName().startsWith("show") || method.getName().startsWith("save")
                || method.getName().startsWith("update")) {
            checkRights();
        }
        //2.调用目标类的目标方法
        Object obj =  method.invoke( target,  args ); //调用目标类的方法 target.method(args);

        if (method.getName().startsWith("show") || method.getName().startsWith("save")
                || method.getName().startsWith("update")) {
            doLog();
        }
        return obj;
    }



    //增强的功能
    public void checkRights() {
        System.out.println("检查权限  20000代码");
    }
    private void doLog() {
        System.out.println("记录日志  20000代码");
    }
}
