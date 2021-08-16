package com.queryconfig.examplespringboot.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import com.queryconfig.examplespringboot.constant.Const;
import com.queryconfig.examplespringboot.implement.PersonGroupBy;
import com.queryconfig.query.base.BaseConfig;
import com.queryconfig.query.base.BaseQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryConfig implements BaseConfig {

    
    @Autowired
    private DataSource datasource;

    @Override
    public Connection configConnection() throws SQLException {
        return datasource.getConnection();
    }

    @Override
    public Map<String, Class<? extends BaseQuery>> configQuery() {
        return Map.of(Const.KEY_QUERY_PERSON, PersonGroupBy.class);
    }
    
}
