package com.bukukas.core;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemMapStore {

    public static final InMemMapStore INSTANCE = new InMemMapStore();
    private static Map<String,String> keyValDB = new HashMap<>();


    private InMemMapStore() {
    }


    public List searchPrefix(String prefix){
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String,String> entry: keyValDB.entrySet()) {
            if (entry.getKey().startsWith(prefix)){
                System.out.println("Found--->"+entry.getKey());
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public List searchSuffix(String suffix){
        List<String> keys = new ArrayList<>();
        for (Map.Entry<String,String> entry: keyValDB.entrySet()) {
            if (entry.getKey().endsWith(suffix)){
                System.out.println("Found--->"+entry.getKey());
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    public String getValue(String key){
        return keyValDB.get(key);
    }

    public void setKey(String key, String value){

        keyValDB.put(key, value);
    }


}
