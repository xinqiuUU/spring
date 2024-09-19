package com.yc.web.listeners;

import javax.servlet.ServletContextListener;

public class ApplicationContextListeners implements ServletContextListener {

    @Override
    public void contextInitialized(javax.servlet.ServletContextEvent servletContextEvent) {
        System.out.println("contextInitialized");


    }

    @Override
    public void contextDestroyed(javax.servlet.ServletContextEvent servletContextEvent) {
        System.out.println("contextDestroyed");
    }
}
