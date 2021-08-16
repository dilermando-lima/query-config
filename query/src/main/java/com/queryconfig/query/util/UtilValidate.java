package com.queryconfig.query.util;

import java.util.List;

import com.queryconfig.query.exception.QueryException;

abstract class UtilValidate {

    protected void validateSelectSql(String regexSelect){
        if( regexSelect == null )   throw new QueryException("select cannot be null");
        regexSelect =  regexSelect.toLowerCase();
		if( regexSelect.contains("inner")){
			if(  !regexSelect.matches(".*select .*? from .*? join .*") ){
                            throw new QueryException( "select dosen't match to '.*select .*? from .*? join .*' ");
                    }	 
		}else if( regexSelect.contains(" where ") ){
			if(  !regexSelect.matches(".*select .*? from .*? where .*")){
                throw new QueryException("select dosen't match to '.*select .*? from .*? where .*' ");
            }
		}else{ 
			if( !regexSelect.matches(".*select .*? from .*") ){
                throw new QueryException("select dosen't match to '.*select .*? from .*' ");
            }
		}

    }

    protected void validateCol(String col){
        if( col == null || col.isBlank() ) throw new QueryException("col cannot be null or empty");

        boolean isNotValidNameOfCol = !col.toLowerCase().matches(".* as *.") || !col.toLowerCase().matches(".*case*when*then*") ||  col.strip().contains(" ");

        if( isNotValidNameOfCol ) throw new QueryException(
                                                
                                                "col '%s' is not valid name of col. try something like 'FIELD' or 'FILED AS NAME_FIELD' or a 'CASE' expression",
                                                col );
    }

    protected void validateCalc(String calc){
        if( calc == null || calc.isBlank() ) throw new QueryException("calc columns cannot be null or empty");

        boolean isNotValidNameOfCalcColumn = !calc.toLowerCase().matches(".*\\(*\\)*.") ||  !calc.toLowerCase().matches(".*\\(*\\)* as *.");

        if( isNotValidNameOfCalcColumn ) throw new QueryException(
                                                
                                                "calc column '%s' is not valid name of col. try something like 'COUNT(*)' or 'COUNT(*) AS NAME_FIELD'",
                                                calc );
    }


    protected void validateCol(List<String> colList){
        if( colList.isEmpty() ) throw new QueryException("select must contains at least one col");
        colList.forEach(this::validateCol);
    }

    protected void validateCalc(List<String> colList){
        if( colList.isEmpty() ) throw new QueryException("select must contains at least one calc column");
        colList.forEach(this::validateCalc);
    }

    
    
}
