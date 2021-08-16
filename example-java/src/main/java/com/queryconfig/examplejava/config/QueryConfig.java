package com.queryconfig.examplejava.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.queryconfig.query.base.BaseConfig;
import com.queryconfig.query.base.BaseQuery;
import com.queryconfig.examplejava.constant.Const;
import com.queryconfig.examplejava.implement.PersonGroupBy;

public class QueryConfig implements BaseConfig{

    @Override
    public Connection configConnection() throws SQLException {
        return ConnFactory.getConnection();
    }

    @Override
    public Map<String, Class<? extends BaseQuery>> configQuery() {
        return Map.of(Const.KEY_QUERY_PERSON,PersonGroupBy.class);
    }
    
}
