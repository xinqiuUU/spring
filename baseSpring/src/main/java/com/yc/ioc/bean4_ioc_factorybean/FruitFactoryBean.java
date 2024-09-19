package com.yc.ioc.bean4_ioc_factorybean;

import com.yc.ioc.bean4_ioc_factorybean.other.Banana;
import com.yc.ioc.bean4_ioc_factorybean.other.Fruit;
import com.yc.ioc.bean4_ioc_factorybean.other.Grape;
import org.springframework.beans.factory.FactoryBean;

import java.util.Random;

public class FruitFactoryBean implements FactoryBean<Fruit> {

    public FruitFactoryBean(){
        System.out.println("水果工厂的构造方法");
    }
    @Override  //这一段是精华
    public Fruit getObject() throws Exception {
        Random r  = new Random();
        if ( r.nextInt(2) == 0  ){
            return new Banana();
        }else {
            return new Grape();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return Fruit.class;
    }

}
