package com.queryconfig.examplejava.implement;

import java.util.HashMap;
import java.util.Map;

import com.queryconfig.query.abstracts.QueryGroupBy;
import com.queryconfig.query.constant.Const;

public class PersonGroupBy extends QueryGroupBy{

    @Override
    public String configBodySelect() {
        return  String.join(" ",
                    " SELECT ",
                    Const.KEY_REPLACE_COLS,
                    " FROM person  ");
    }

    @Override
    public Map<String, String> configColumn() {
        Map<String, String> colMap = new HashMap<>();
        colMap.put("NAME","person.name");
        colMap.put("EMAIL","person.email");
        colMap.put("YYYYMM_CREATED","DATE_FORMAT(person.date_created, '%Y%m')");
        colMap.put("YYYY_CREATED","DATE_FORMAT(person.date_created, '%Y')");
        colMap.put("COUNT_ALL","COUNT(*)");
        return colMap;
    }

    @Override
    public Map<String, String> configWhere() {
        Map<String, String> colWhere = new HashMap<>();
        colWhere.put("NAME_LIKE","person.name LIKE ?");
        colWhere.put("EMAIL_LIKE","person.email LIKE ?");
        colWhere.put("BY_YYYY_CREATED","DATE_FORMAT(person.date_created, '%Y') = ?");
        colWhere.put("BY_YYYYMM_CREATED","DATE_FORMAT(person.date_created, '%Y%m') = ?");
        return colWhere;
    }

    @Override
    public String description() {
        return "this report will select row about PERSON entity on database";
    }

    @Override
    public Map<String, String> configPeriod() {
        Map<String, String> periodMap = new HashMap<>();
        periodMap.put("YYYYMMDD_CREATED_MORE_THAN","person.date_created >= DATE_FORMAT(person.date_created, '%Y%m%d')");
        periodMap.put("YYYYMMDD_CREATED_LESS_THAN","person.date_created <= DATE_FORMAT(person.date_created, '%Y%m%d') ");
        return periodMap;
    }
    
}
