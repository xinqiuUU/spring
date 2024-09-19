package com.yc.ioc.bean5_ioc_import;

import com.yc.ioc.bean5_ioc_import.other.Banana;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ Banana.class, FruitImportSelector.class, FruitNameImportBeanDefinitionRegistar.class })  //约定
public class AppConfig_3 {

}
