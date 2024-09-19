package com.yc.ioc.bean5_ioc_import;

import com.yc.ioc.bean5_ioc_import.other.Apple;
import com.yc.ioc.bean5_ioc_import.other.Banana;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ Apple.class, Banana.class })  //约定
public class AppConfig_1 {

    // id="a"
    @Bean
    public Apple a(){
        return new Apple();
    }

}
