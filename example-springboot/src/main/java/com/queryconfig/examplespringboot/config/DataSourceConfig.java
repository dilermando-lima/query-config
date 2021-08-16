package com.queryconfig.examplespringboot.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder
                .create()
                //.driverClassName("org.h2.Driver")
                .url("mysql://root:root@mysql_app_client:3306/test_query")
                .username("root")
                .password("root")
                .build();
    }
    
}
