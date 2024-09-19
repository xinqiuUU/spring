package com.yc.ioc.bean2;

import org.springframework.stereotype.Service;

//相当于:= @Component+语义: 表示这是一个业务层的类，这个类由spring管理 相当于<bean id="orderBiz" class="com.yc.bean2.OrderBiz"/>
//有了这个@Service注解，对事务处理有帮助
@Service
public class OrderBiz {

}
