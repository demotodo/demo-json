package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonGetterSetter {

    private long personId = 0;

    @JsonGetter("id")
    public long personId() {
        return this.personId;
    }

    @JsonSetter("id")
    public void personId(long personId) {
        this.personId = personId;
    }

}
