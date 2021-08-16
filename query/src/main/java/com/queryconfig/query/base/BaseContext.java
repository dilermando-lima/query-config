package com.queryconfig.query.base;

import java.util.List;
import java.util.Map;

import com.queryconfig.query.domain.ColRequest;
import com.queryconfig.query.domain.PeriodRequest;
import com.queryconfig.query.domain.WhereRequest;

public interface BaseContext {
    
    public void saveParameter(String key, Object value);

    public boolean containsParameter(String key);

    public Object getParameter(String key);

    public String getSelectBuilt();

    public void saveSelectBuilt(String selectBuilt);

    public List<Map<String, Object>> getResultQuery();

    public void saveResultQuery(List<Map<String, Object>> resultQuery);

    public WhereRequest getWhereRequest();

    public void saveWhereRequest(WhereRequest whereRequest);

    public ColRequest getColRequest();

    public void saveColRequest(ColRequest colRequest);

    public PeriodRequest getPeriodRequest();

    public void savePeriodRequest(PeriodRequest periodRequest);

    public WhereRequest getWhereRequired();

    public void saveWhereRequired(WhereRequest whereRequired);

    public List<Object> getValuesWhere();


}
