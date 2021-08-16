package com.queryconfig.query.exec;

import java.util.List;
import java.util.Map;

import com.queryconfig.query.base.BaseConfig;
import com.queryconfig.query.base.BaseQuery;
import com.queryconfig.query.domain.ColRequest;
import com.queryconfig.query.domain.PeriodRequest;
import com.queryconfig.query.domain.WhereRequest;
import com.queryconfig.query.exception.QueryException;

public class Query {

    private ColRequest colRequest;
    private WhereRequest whereRequest;
    private PeriodRequest periodRequest;
    private String keyConfigQuery;
    private BaseConfig baseConfig;
    
    private void initVars(){
        colRequest = ColRequest.build();
        whereRequest = WhereRequest.build();
        periodRequest = PeriodRequest.build();
    }

    private Query(BaseConfig baseConfig){
        this.baseConfig = baseConfig;
        initVars();
    }

    public static Query build(BaseConfig baseConfig){
        return new Query(baseConfig);
    }

    public Query addCol(String... col){
        colRequest.add(col);
        return this;
    }

    public Query addCol(String col){
        colRequest.add(col);
        return this;
    }

    public Query addWhere(String keyWhere, Object value){
        whereRequest.add(keyWhere, value);
        return this;
    }

    public Query addWhere(String delimiter, String... where){
        whereRequest.add(delimiter, where);
        return this;
    }


    public Query addPeriod(String keyWhere, Object value){
        periodRequest.add(keyWhere, value);
        return this;
    }

    public Query addPeriod(String delimiter, String... where){
        periodRequest.add(delimiter, where);
        return this;
    }

    public Query keyConfigQuery(String keyConfigQuery){
        this.keyConfigQuery = keyConfigQuery;
        return this;
    }

    private BaseQuery buildQuery(){
        BaseQuery baseQuery = getInstanceBaseQuery(keyConfigQuery);
        baseQuery.saveColRequest(colRequest);
        baseQuery.saveWhereRequest(whereRequest);
        baseQuery.savePeriodRequest(periodRequest);
        baseQuery.execBeforeBuildSelect();
        baseQuery.saveSelectBuilt(baseQuery.buildSelectOnRequiredStepOrder());
        baseQuery.execAfterBuildSelect();
        return baseQuery;
    }

    public List<Map<String, Object>> exec(){
        BaseQuery baseQuery = buildQuery();
        baseQuery.saveResultQuery(ExecQuery.exec(this.baseConfig, baseQuery));
        baseQuery.execAfterExec();
        return baseQuery.getResultQuery();
    }
    
    private BaseQuery getInstanceBaseQuery(String keyConfigQuery){
        try {
            return baseConfig.configQuery().get(keyConfigQuery).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new QueryException("keyConfigQuery not registred on BaseConfig.configQuery");
        }
    }

    
}
