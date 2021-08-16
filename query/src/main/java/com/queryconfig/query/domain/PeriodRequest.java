package com.queryconfig.query.domain;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.queryconfig.query.base.BaseQuery;


public class PeriodRequest {

    private Map<String,String> currentPeriodMap;
    private Map<String,String> configPeriodMap;
    private List<Object> valuesList;

    private void initVars(){
        currentPeriodMap =  new LinkedHashMap<>();
        valuesList = new LinkedList<>();
    }

    private PeriodRequest(){
        initVars();
    }

    public static PeriodRequest build(){
        return new PeriodRequest();
    }

    public PeriodRequest setConfigPeriod(BaseQuery abstractQuery){
        this.configPeriodMap = abstractQuery.configPeriod();
        return this;
    }
    
    public static PeriodRequest build( Function<PeriodRequest, ? extends PeriodRequest> function){
        return function.apply(new PeriodRequest());
    }

    public PeriodRequest add(String keyPeriod, Object value){
        currentPeriodMap.put(keyPeriod, configPeriodMap.get(keyPeriod));
        valuesList.add(value);
        return this;
    }

    public PeriodRequest add(String delimiter, String... period){
        for (String string : period) {
            add(string.split(delimiter)[0],string.split("=")[1]);
        }
        return this;
    }

    public Map<String, String> getCurrentPeriodMap() {
        return currentPeriodMap;
    }

    public List<Object> getValuesList() {
        return valuesList;
    }

}
