package com.yc.project1;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
@EnableAspectJAutoProxy  //开启aspectj的自动代理功能
public class AppConfig {

    @Value("${url}")
    private String dbUrl;

    @Value("${uname}")
    private String dbUser;

    @Value("${pwd}")
    private String dbPassword;

    @Value("${driverClassName}")
    private String dbDriver;

    //利用上面的属性值来创建druid的数据源对象
    @Bean(initMethod = "init")  //另外要注意:idea会对这个方法的返回值进行解析，判断
    public DruidDataSource druidDataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(  dbUrl  );
        ds.setUsername(  dbUser );
        ds.setPassword( dbPassword );
        ds.setDriverClassName( dbDriver );
//        System.out.println(ds);
        //设置连接池的参数
        ds.setInitialSize( 5 );
        ds.setMaxActive( 10 );
        ds.setMinIdle( 5 );
        return ds;
    }

}
