package com.queryconfig.query.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import com.queryconfig.query.base.BaseQuery;

public class ColRequest {

    private Map<String,String> currentCollumMap;
    private Map<String,String> configCollumnMap;

    private void initVars(){
        currentCollumMap =  new LinkedHashMap<>();
    }

    private ColRequest(){
        initVars();
    }

    public static ColRequest build(){
        return new ColRequest();
    }

    public ColRequest setConfigColumn(BaseQuery abstractQuery){
        this.configCollumnMap = abstractQuery.configColumn();
        return this;
    }

    public ColRequest add(String keyCol){
        currentCollumMap.put(keyCol, configCollumnMap.get(keyCol));
        return this;
    }

    public ColRequest add(String... keyCol){
        for(String keyColItem : keyCol) add(keyColItem);
        return this;
    }

    public Map<String, String> getCurrentCollumMap() {
        return currentCollumMap;
    }


}
