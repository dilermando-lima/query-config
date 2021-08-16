package com.queryconfig.examplespringboot.controller;

import java.util.List;
import java.util.Map;

import com.queryconfig.examplespringboot.service.QueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @GetMapping("/groupby")
    public List<Map<String, Object>> query(
                        @RequestParam String type,
                        @RequestParam String col,
                        @RequestParam String where,
                        @RequestParam String period
    ){
        return queryService.query(type, col, where, period);
    }
    
}
