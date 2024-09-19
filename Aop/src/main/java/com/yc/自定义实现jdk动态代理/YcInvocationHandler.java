package com.yc.自定义实现jdk动态代理;

import java.lang.reflect.Method;

/*
 * 回调方法 实现类具体实现方法
 */
public interface YcInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
