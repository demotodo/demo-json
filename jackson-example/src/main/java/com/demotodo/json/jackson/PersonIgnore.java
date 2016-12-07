package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonIgnore {

    @JsonIgnore
    public long personId = 0;

    public String name = null;

}
