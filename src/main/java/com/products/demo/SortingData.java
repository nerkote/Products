package com.products.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.TreeMultimap;
import org.json.simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class SortingData extends Thread {

    public  Map<String, ArrayList<String>> multiValueMapApiOne = new TreeMap<String, ArrayList<String>>();
    public Map<String, ArrayList<String>> multiValueMapApiTwo = new TreeMap<String, ArrayList<String>>();

    public Map<String, ArrayList<String>> multiValueMapApiThree = new TreeMap<String, ArrayList<String>>();

    public Map<String, ArrayList<String>> multiValueMapApiFour = new TreeMap<String, ArrayList<String>>();
    public static Map<String, ArrayList<String>> multiValueMap = new TreeMap<String, ArrayList<String>>();

    public SortingData(Map<String, ArrayList<String>> multiValueMapApiOne, Map<String, ArrayList<String>> multiValueMapApiTwo, Map<String, ArrayList<String>> multiValueMapApiThree, Map<String, ArrayList<String>> multiValueMapApiFour) {
        this.multiValueMapApiOne = multiValueMapApiOne;
        this.multiValueMapApiTwo = multiValueMapApiTwo;
        this.multiValueMapApiThree = multiValueMapApiThree;
        this.multiValueMapApiFour = multiValueMapApiFour;
    }

    @Override
    public void run() {
        if (!multiValueMapApiOne.isEmpty()) {
            multiValueMap.putAll(multiValueMapApiOne);
        } else if (!multiValueMapApiTwo.isEmpty()) {
                    Set<String> keys = multiValueMapApiTwo.keySet();
            for(String key: keys){
                        if (multiValueMap.containsKey(key)) {
                            multiValueMap.get(key).add(String.valueOf(multiValueMapApiTwo.get(key)));
                        } else {
                            multiValueMap.put(key, new ArrayList<String>());
                            multiValueMap.get(key).add("price:" + multiValueMapApiTwo.get(key));
                        }
                    }
        } else if (!multiValueMapApiThree.isEmpty()) {
            Set<String> keys = multiValueMapApiThree.keySet();
            for(String key: keys){
                if (multiValueMap.containsKey(key)) {
                    multiValueMap.get(key).add(String.valueOf(multiValueMapApiThree.get(key)));
                } else {
                    multiValueMap.put(key, new ArrayList<String>());
                    multiValueMap.get(key).add("price:" + multiValueMapApiThree.get(key));
                }
            }
        } else if (!multiValueMapApiFour.isEmpty()) {
            Set<String> keys = multiValueMapApiFour.keySet();
            for(String key: keys){
                if (multiValueMap.containsKey(key)) {
                    multiValueMap.get(key).add(String.valueOf(multiValueMapApiFour.get(key)));
                } else {
                    multiValueMap.put(key, new ArrayList<String>());
                    multiValueMap.get(key).add("price:" + multiValueMapApiFour.get(key));
                }
            }
        }
    }

    public static Map<String, ArrayList<String>> getMultiValueMap() {
        return multiValueMap;
    }
}


