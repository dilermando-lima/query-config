package com.queryconfig.query.domain;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.queryconfig.query.base.BaseQuery;


public class WhereRequest {

    private Map<String,String> currentWhereMap;
    private Map<String,String> configWhereMap;
    private List<Object> valuesList;

    private void initVars(){
        currentWhereMap =  new LinkedHashMap<>();
        valuesList = new LinkedList<>();
    }

    private WhereRequest(){
        initVars();
    }

    public static WhereRequest build(){
        return new WhereRequest();
    }


    public WhereRequest setConfigWhere(BaseQuery abstractQuery){
        this.configWhereMap = abstractQuery.configWhere();
        return this;
    }

    public static WhereRequest build( Function<WhereRequest, ? extends WhereRequest> function){
        return function.apply(new WhereRequest());
    }

    public WhereRequest add(String keyWhere, Object value){
        currentWhereMap.put(keyWhere, configWhereMap.get(keyWhere));
        valuesList.add(value);
        return this;
    }

    public WhereRequest add(String delimiter, String... where){
        for (String string : where) {
            add(string.split(delimiter)[0],string.split("=")[1]);
        }
        return this;
    }

    public Map<String, String> getCurrentWhereMap() {
        return currentWhereMap;
    }

    public List<Object> getValuesList() {
        return valuesList;
    }

}
