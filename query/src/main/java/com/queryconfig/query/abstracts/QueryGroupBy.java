package com.queryconfig.query.abstracts;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.queryconfig.query.base.BaseQuery;
import com.queryconfig.query.constant.Const;


public abstract class QueryGroupBy extends BaseQuery {

    @Override
    public String buildSelectOnRequiredStepOrder(){
        return String.join(" ", 
                        getReplacedColRequestIntoBodySelectAsString(),
                        getPeriodRequestAsString(),
                        getRequiredWhereAsString(),
                        getWhereRequestAsString(),
                        getGroupByAsString(),
                        getHavingOrderByAsString(),
                        getTreatedUpperCaseSelectAsString()
                        );
    }

    private static final String SPACE_AND_SPACE = " and "; 

    private String getReplacedColRequestIntoBodySelectAsString() {
       
        Function<Map.Entry<String,String>,String> getNameColWithAliasAsKeyCol =  
                                column -> column.getValue() + " as " + column.getKey();

        var colRequestAsString = String.join(" , " , this.getColRequest().getCurrentCollumMap()
                                                        .entrySet()
                                                        .stream()
                                                        .map(getNameColWithAliasAsKeyCol)
                                                        .collect(Collectors.toList()));

        return this.configBodySelect().replace(Const.KEY_REPLACE_COLS, colRequestAsString);
    }

    private String getPeriodRequestAsString() {
        if( this.getPeriodRequest().getCurrentPeriodMap().isEmpty() ) 
            // TODO: return throw
            return "";

        var whereAsString = String.join(
                                SPACE_AND_SPACE ,
                                this.getPeriodRequest().getCurrentPeriodMap()
                                    .entrySet()
                                    .stream()
                                    .map(Map.Entry::getValue)
                                    .collect(Collectors.toList())
                                );

        return String.format(" where  %s ", whereAsString);
    }

    private String getRequiredWhereAsString() {
        
        if(  this.getWhereRequired().getCurrentWhereMap().isEmpty() ){
                return "";  // TODO: return throw
        }
       
        var whereAsString = String.join(
                                SPACE_AND_SPACE ,
                                this.getWhereRequired().getCurrentWhereMap()
                                    .entrySet()
                                    .stream()
                                    .map(Map.Entry::getValue)
                                    .collect(Collectors.toList())
                                );

        return String.format(" and  %s ", whereAsString);
    }

    private String getWhereRequestAsString() {
        if( this.getWhereRequest().getCurrentWhereMap().isEmpty() ){
                return "";  // TODO: return throw
        }

        var whereAsString = String.join(
                            SPACE_AND_SPACE ,
                            this.getWhereRequest().getCurrentWhereMap()
                                .entrySet()
                                .stream()
                                .map(Map.Entry::getValue)
                                .collect(Collectors.toList())
                            );

        return String.format(" and  %s ", whereAsString);
    }

    private String getGroupByAsString() {

        Predicate<Map.Entry<String,String>> colsNotUsedOnGroupByStatement = 
                        column -> !column.getValue().contains("COUNT(") && 
                                !column.getValue().contains("MAX(") &&
                                !column.getValue().contains("AVG(") &&
                                !column.getValue().contains("SUM(");

        Function<Map.Entry<String,String>,String> treatedNameWithNoAliasToUseOnGroupBy =  
                    column -> column.getValue().toLowerCase().contains(" as ") ? 
                            column.getValue().split(" as ")[0] :
                            column.getValue();

        var colRequestToGroupByAsString = 
        String.join(" , " , 
                    this.getColRequest().getCurrentCollumMap()
                    .entrySet()
                    .stream()
                    .filter(colsNotUsedOnGroupByStatement)
                    .map(treatedNameWithNoAliasToUseOnGroupBy)
                    .collect(Collectors.toList())
                );

        return String.format(" group by %s ", colRequestToGroupByAsString );
    }

    
    private String getHavingOrderByAsString() {
        // TODO: do later
        return "";
    }

    private String getTreatedUpperCaseSelectAsString() {
        // TODO: 
        return "";
    }
    
}
