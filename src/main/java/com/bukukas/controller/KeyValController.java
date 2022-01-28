package com.bukukas.controller;

import com.bukukas.service.KeyValService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class KeyValController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KeyValController.class);

    private final KeyValService keyValService;

    public KeyValController(KeyValService keyValService) {
        this.keyValService = keyValService;
    }

    @RequestMapping(value = "/hello", method= RequestMethod.GET)
    public String hello(){
        return "Hello from KeyVal App!!";
    }

    @RequestMapping(value = "/set", method= RequestMethod.POST, consumes = {"application/json"})
    public String setKeyVal(@RequestBody final KeyValRequest keyValRequest) throws Exception {
        LOGGER.info("Saving Key Value: " + keyValRequest.getKey() + "=" + keyValRequest.getVal());
        String key = keyValRequest.getKey(), value = keyValRequest.getVal();
        if (key != null &&  value != null && !key.isEmpty() && !value.isEmpty())
            keyValService.save(keyValRequest.getKey(), keyValRequest.getVal());
        else throw new IOException("Please enter a valid key value set");
        return "Key Value set successfully!";
    }

    @RequestMapping(value = "/search", method=RequestMethod.GET)
    public List search(@RequestParam(required = false) String prefix,
                       @RequestParam(required = false) String suffix) throws IOException, Exception {
        LOGGER.info("Received prefix: " + prefix);
        LOGGER.info("Received prefix: " + suffix);
        List<String> foundKeys= new ArrayList();
        if (prefix != null && !prefix.isEmpty()){
            foundKeys = keyValService.searchKeysPrefix(prefix);
        }
        else if (suffix != null && !suffix.isEmpty()){
            foundKeys = keyValService.searchKeysSuffix(suffix);
        }
        else throw new IOException("no prefix/suffix provided");

        if (foundKeys.isEmpty())
            throw new Exception("No matching keys found");

        return foundKeys;

    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getValue(@RequestParam String key)throws Exception{
        LOGGER.info("Received Key: " + key);
        if (key == null || key.isEmpty())
            throw new IOException("Provide a valid key!");
        String val = keyValService.getVal(key);
        if (val == null)
            throw new Exception("Key not found!");
        return val;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}

 class KeyValRequest{
    private String key;
    private String val;

    @JsonCreator
    public KeyValRequest() {
    }

    @JsonCreator
    public KeyValRequest(@JsonProperty("key") String key, @JsonProperty("value") String value) {
        this.key = key;
        this.val = value;
    }


    public String getKey() {
        return key;
    }

    public void setkey(String key) {
        this.key = key;
    }

    public String getVal() {
         return val;
    }

    public void setVal(String val) {
         this.val = val;
    }
    @Override
    public String toString() {
        return "KeyValRequest{" +
                "key='" + key + '\''  + "value='" + val +
                '}';
    }
}