package com.bukukas;

import com.bukukas.core.InMemMapStore;
import com.bukukas.service.KeyValService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class KeyValServiceTest {

    @Autowired
    KeyValService keyValService;

    @Autowired
    InMemMapStore inMemMapStore;

    String key1 = "TestKey-1";
    String key2 = "TestKey-2";
    String value = "TestVal";

    @BeforeTestClass
    public void saveKeysIntoDB(){
        keyValService.save(key1,value);
        keyValService.save(key2,value+"-2");
    }

    @Test
    public void SaveKey(){
        keyValService.save(key1,value);
        assertNotNull(inMemMapStore.getValue(key1));
    }

    @Test
    public void returnsEmptyForNotFoundKeys(){
        List found = keyValService.searchKeysPrefix("abcd");
        assertEquals(0,found.size());
    }

    @Test
    public void sameResultWhenKeyUsedAsPrefixSuffix(){
        List foundPrefix = keyValService.searchKeysPrefix(key1);
        List foundSuffix = keyValService.searchKeysSuffix(key1);
        assertEquals(foundPrefix,foundSuffix);
    }


}
