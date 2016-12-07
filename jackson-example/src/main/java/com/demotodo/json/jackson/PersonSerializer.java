package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonSerializer {

    public long id;
    String name;
    public boolean male;

    @JsonSerialize(using = OptimizedBooleanSerializer.class)
    @JsonDeserialize(using = OptimizedBooleanDeserializer.class)
    public boolean enabled;

}
