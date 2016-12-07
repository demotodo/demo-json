package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PersonInclude {

    public long personId = 0;
    public String name = null;

}
