package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class BagAnyGetterSetter {

    private Map<String, Object> properties = new HashMap<>();

    @JsonAnySetter
    public void set(String fieldName, Object value) {
        this.properties.put(fieldName, value);
    }

    public Object get(String fieldName) {
        return this.properties.get(fieldName);
    }

    @JsonAnyGetter
    public Map<String, Object> properties() {
        return properties;
    }

}
