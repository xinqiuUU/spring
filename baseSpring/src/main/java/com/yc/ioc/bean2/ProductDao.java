package com.yc.ioc.bean2;

import org.springframework.stereotype.Repository;

//@Repository = @Component+语义: 表示这是一个持久层的类，这个类由spring管理 相当于<bean id="productDao" class="com.yc.bean2.ProductDao"/>
@Repository   //这个注解它的不同之处:它会转换异常
public class ProductDao {

}
