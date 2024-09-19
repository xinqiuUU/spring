package com.yc.ioc.bean5_ioc_import;

import com.yc.ioc.bean5_ioc_import.other.Apple;
import com.yc.ioc.bean5_ioc_import.other.Banana;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {Apple.class, Banana.class,FruitImportSelector.class})
public class AppConfig_2 {

}
