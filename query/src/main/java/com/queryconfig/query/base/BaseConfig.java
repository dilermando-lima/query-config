package com.queryconfig.query.base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface BaseConfig {

    public Connection configConnection() throws SQLException;

    public default boolean configUpperCaseOnAllSelect(){
        return true;
    }
    public Map<String, Class<? extends BaseQuery>> configQuery();
    
}
