package com.queryconfig.query.base;

import java.util.Map;

import com.queryconfig.query.domain.WhereRequest;

public abstract class BaseQuery extends ContextQuery implements BaseTemplateBuildQuery {

    public abstract String configBodySelect();

    public abstract Map<String, String> configColumn();

    public abstract Map<String, String> configWhere();

    public abstract String description();

    public abstract Map<String, String> configPeriod();

    public void execBeforeBuildSelect(){}
    public void execAfterBuildSelect(){}
    public void execAfterExec(){}

    public WhereRequest configRequiredWhere(){
           return WhereRequest.build();
    }


}
