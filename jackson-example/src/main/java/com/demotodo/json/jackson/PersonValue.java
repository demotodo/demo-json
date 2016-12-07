package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonValue {

    public long personId = 0;
    public String name = null;

    @JsonValue
    @JsonRawValue
    public String toJson() {
        return "{\"id\": " + this.personId + ", \"name\":\"" + this.name + "\"}";
    }

}
