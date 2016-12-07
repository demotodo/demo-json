package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
@JsonPropertyOrder({"name", "personId"})
public class PersonPropertyOrder {

    public long personId = 0;
    public String name = null;

}
