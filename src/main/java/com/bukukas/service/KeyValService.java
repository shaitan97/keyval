package com.bukukas.service;

import com.bukukas.core.InMemMapStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyValService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyValService.class);

    @Autowired
    InMemMapStore inMemMapStore;

    public List searchKeysPrefix(String prefix) {
        LOGGER.info("Searching keys with {}", prefix);
        return inMemMapStore.searchPrefix(prefix);
    }

    public List searchKeysSuffix( String suffix) {
        LOGGER.info("Searching {}", suffix);
        return inMemMapStore.searchSuffix(suffix);
    }

    public String getVal(String key) {

        return inMemMapStore.getValue(key);
    }

    public void save(String key, String val){
        LOGGER.info("Saving {}", key);
        inMemMapStore.setKey(key,val);
    }

}
