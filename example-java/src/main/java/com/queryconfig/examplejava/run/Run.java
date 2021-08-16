package com.queryconfig.examplejava.run;

import com.queryconfig.query.base.BaseConfig;
import com.queryconfig.examplejava.config.QueryConfig;
import com.queryconfig.examplejava.constant.Const;
import com.queryconfig.query.exec.Query;

public class Run {
    
    public static void main(String[] args) {
        
        BaseConfig queryconfig = new QueryConfig();

        var keyQuery = Const.KEY_QUERY_PERSON;
        var columns = "NAME,DATE_CREATED,NICKNAME";
        var where = "NAME_LIKE=%jhon%,NICKNAME_LIKE=%jhon%";
        var period = "DATE_CREATED_MORE_THAN=2012-02-01 00:00:00";
    
        Query
        .build(queryconfig)
        .keyConfigQuery(keyQuery)
        .addCol(columns.split(","))
        .addWhere("=",where.split(","))
        .addPeriod("=",period.split(","))
        .exec();  

    }
}
