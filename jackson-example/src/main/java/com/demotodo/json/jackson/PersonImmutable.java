package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonImmutable {

    private long id = 0;
    private String name = null;

    @JsonCreator
    public PersonImmutable(
        @JsonProperty("id") long id,
        @JsonProperty("name") String name) {

        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
