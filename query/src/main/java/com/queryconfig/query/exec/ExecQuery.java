package com.queryconfig.query.exec;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.queryconfig.query.base.BaseConfig;
import com.queryconfig.query.base.BaseQuery;
import com.queryconfig.query.exception.QueryException;

public class ExecQuery {

    private ExecQuery(){}

    public static List<Map<String, Object>> exec(BaseConfig baseConfig, BaseQuery baseQuery  ){

        try( 
            var conn = baseConfig.configConnection();
            var ps = getPreparedStatementFilled(
                                        baseQuery.getValuesWhere(), 
                                        conn.prepareStatement(baseQuery.getSelectBuilt()));
            var rs = ps.executeQuery();
        ){
            return resultSetToListOfMap(rs);
        }catch(Exception e){
            throw new QueryException("message");
        }

    }

    private static PreparedStatement getPreparedStatementFilled(List<Object> valuesWhere, PreparedStatement ps ) throws SQLException{
        for (int i = 0; i < valuesWhere.size(); i++) {
            ps.setObject(i, valuesWhere.get(i));
        }
        return ps;
    }

    private static List<Map<String, Object>> resultSetToListOfMap(ResultSet rs){
        try{
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            List<Map<String, Object>> list = new ArrayList<>();

            while (rs.next()){
                Map<String, Object> row = new HashMap<>(columns);

                for(int i=1; i<=columns; ++i) row.put(md.getColumnName(i),rs.getObject(i));

                list.add(row);
            }

            return list;
        }catch(Exception e){
            throw new QueryException("message"); // TODO: 
        }
    }

}
