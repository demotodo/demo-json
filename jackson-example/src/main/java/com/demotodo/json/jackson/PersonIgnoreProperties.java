package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
@JsonIgnoreProperties({"firstName", "lastName"})
public class PersonIgnoreProperties {

    public long personId = 0;

    public String firstName = null;
    public String lastName = null;

}