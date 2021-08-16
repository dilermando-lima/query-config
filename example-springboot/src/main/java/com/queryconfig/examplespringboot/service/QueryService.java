package com.queryconfig.examplespringboot.service;

import java.util.List;
import java.util.Map;

import com.queryconfig.examplespringboot.config.QueryConfig;
import com.queryconfig.query.exec.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

    @Autowired
    private QueryConfig queryConfig;
    
    public List<Map<String, Object>> query(String type, String col, String where, String period) {
        return Query.build(queryConfig)
                    .keyConfigQuery(type)
                    .addCol(col.split(","))
                    .addWhere("=",where.split(","))
                    .addPeriod("=",period.split(","))
                    .exec();
    }
    
}
