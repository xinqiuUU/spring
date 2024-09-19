package com.yc.自定义实现jdk动态代理;

import com.yc.自定义实现jdk动态代理.YcClassLoader;
import com.yc.自定义实现jdk动态代理.YcInvocationHandler;
import com.yc.自定义实现jdk动态代理.YcJdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//回调接口
public class CheckRightsInvocationHandler implements YcInvocationHandler {

    private Object target; //目标类的引用  *****

    //目标类的引用的初始化
    public CheckRightsInvocationHandler(Object target) {
        this.target = target;
    }

    //对外提供一个方法用于创建一个代理类的对象
    public Object createProxy() {
        //jdk中提供了一个Proxy类的newProxyInstance方法来创建代理类的对象
        Object proxy = YcJdkProxy.newProxyInstance( new YcClassLoader(),
                target.getClass().getInterfaces(),
                this );
        //this表明 : 一个代理对象被调用时 由jvm机自动回调this把指的 InvocationHandler 接口的invoke方法来处理
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
