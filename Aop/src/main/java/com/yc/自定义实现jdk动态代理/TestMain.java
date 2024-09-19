package com.yc.自定义实现jdk动态代理;

public class TestMain {
    public static void main(String[] args) {

        //配置环境变量:将Proxy生成的代理类的字节码保存下来
//        System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");//保存到当前目录下
        //目标类的对象
        OrderBiz target = new OrderBizImpl();

        //通过 InvocationHandler 来创建一个代理类的对象
        CheckRightsInvocationHandler handler = new CheckRightsInvocationHandler(target);
        OrderBiz proxy = (OrderBiz) handler.createProxy(); //生成代理类的对象


        proxy.findAllOrder(); // -> 调用代理对象的被代理方法时 -> jvm自动调用 handler.invoke() -> 调用目标类的被代理方法
        proxy.saveOrder("1001");
        proxy.updateOrder("1001", "10000");
        proxy.showOrder();

    }


}
