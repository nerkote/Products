package com.products.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class Controller {

    @RequestMapping(value="/data", method=GET, produces="application/json")
    @ResponseBody
        public Map<String, ArrayList<String>> data() throws JsonProcessingException {
        return SortingData.getMultiValueMap();
    }

    @RequestMapping(value="/responses", method=GET, produces="application/json")
    @ResponseBody
    public JSONArray avgResponseTime() throws JsonProcessingException {
        return AverageResponseTime.getResponseTime();
    }
}