package com.yc.ioc.bean5_ioc_import;

import com.yc.ioc.bean5_ioc_import.other.Apple;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class FruitNameImportBeanDefinitionRegistar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //
//        System.out.println(importingClassMetadata);
//        System.out.println(registry);
        //判断容器中是否已经有了这个 Apple  bean
        boolean flag = registry.containsBeanDefinition( Apple.class.getName() );
        if ( flag == false ){ //没有则注册
            //bean的定义信息对象
            //spring对bean的扫描和加载流程: 先扫描到bean -> 先创建BeanDefinition -> 再创建bean对象 -> 考虑依赖的顺序
            String beanid = Apple.class.getSimpleName().substring(0,1).toLowerCase()+Apple.class.getSimpleName().substring(1);
            RootBeanDefinition bean = new RootBeanDefinition( Apple.class );
            registry.registerBeanDefinition( beanid, bean );
        }

    }
}
