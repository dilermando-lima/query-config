package com.queryconfig.query.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.queryconfig.query.domain.ColRequest;
import com.queryconfig.query.domain.PeriodRequest;
import com.queryconfig.query.domain.WhereRequest;

public abstract class ContextQuery implements BaseContext {


    private final Map<String, Object> contextParameter = new HashMap<>();
    private String selectBuilt;
    private List<Map<String, Object>> resultQuery;
    private WhereRequest whereRequest;
    private WhereRequest whereRequired;
    private ColRequest colRequest;
    private PeriodRequest periodRequest;
    private List<Object> valuesWhere = new LinkedList<>();

    public void saveParameter(String key, Object value){
        this.contextParameter.put(key, value);
    }

    public boolean containsParameter(String key){
        return this.contextParameter.containsKey(key);
    }

    public Object getParameter(String key){
        return this.contextParameter.get(key);
    }

    public String getSelectBuilt() {
        return selectBuilt;
    }

    public void saveSelectBuilt(String selectBuilt){
        this.selectBuilt = selectBuilt;
    }

    public List<Map<String, Object>> getResultQuery() {
        return resultQuery;
    }

    public List<Object> getValuesWhere() {
        return valuesWhere;
    }

    public void saveResultQuery(List<Map<String, Object>> resultQuery) {
        this.resultQuery = resultQuery;
    }

    public WhereRequest getWhereRequest() {
        return whereRequest;
    }

    public void saveWhereRequest(WhereRequest whereRequest) {
        this.whereRequest = whereRequest;
        this.valuesWhere.addAll(whereRequest.getValuesList());
    }

    public ColRequest getColRequest() {
        return colRequest;
    }

    public void saveColRequest(ColRequest colRequest) {
        this.colRequest = colRequest;
    }

    public PeriodRequest getPeriodRequest() {
        return periodRequest;
    }

    public void savePeriodRequest(PeriodRequest periodRequest) {
        this.periodRequest = periodRequest;
        this.valuesWhere.addAll(periodRequest.getValuesList());
    }

    public WhereRequest getWhereRequired() {
        return whereRequired;
    }

    public void saveWhereRequired(WhereRequest whereRequired) {
        this.whereRequired = whereRequired;
        this.valuesWhere.addAll(whereRequired.getValuesList());
    }
    
}
