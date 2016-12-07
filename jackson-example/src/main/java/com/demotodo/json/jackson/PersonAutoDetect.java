package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PersonAutoDetect {

    private long personId = 123;
    public String name = null;

}
