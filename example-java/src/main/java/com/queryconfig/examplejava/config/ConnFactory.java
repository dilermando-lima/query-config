package com.queryconfig.examplejava.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnFactory {

    private ConnFactory(){}

    public static Connection getConnection() throws SQLException{
            return DriverManager.getConnection("mysql://root:root@mysql_app_client:3306/test_query", "root", "root");
    }
    
}
