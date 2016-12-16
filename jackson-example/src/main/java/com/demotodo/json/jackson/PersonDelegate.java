package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;

/**
 * Created by bribin.zheng on 2016/12/16.
 */
public class PersonDelegate {

    private long id = 0;
    private String name = null;

    @JsonCreator
    public PersonDelegate(Map<String, Object> delegate) {
        this.id = (Integer) delegate.get("id");
        this.name = (String) delegate.get("name");
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
