package com.yc;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
@EnableAspectJAutoProxy  //开启aspectj的自动代理功能
@EnableTransactionManagement //开启事务管理
@EnableCaching  //开启spring cache缓存
@EnableMBeanExport //开启MBean的导出功能 启动待监控的类
public class AppDataSourceConfig {

    @Value("${url}")
    private String dbUrl;

    @Value("${uname}")
    private String dbUser;

    @Value("${pwd}")
    private String dbPassword;

    @Value("${driverClassName}")
    private String dbDriver;

    // 声明缓存管理器--》给Dao层加入缓存管理器
    @Bean
    public CacheManager cacheManager() {
        // 返回一个ConcurrentMapCacheManager的实例，其中包含一个名为"users"的缓存区域，用于存储和管理相关的数据
        return new ConcurrentMapCacheManager("users");
    }

    //创建事务管理器 (  给业务层加入事务处理代码 )
    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource( dataSource );
        return txManager;
    }


    //利用上面的属性值来创建jdbcTemplate对象
    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DruidDataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate( dataSource );
        return jdbcTemplate;
    }

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
