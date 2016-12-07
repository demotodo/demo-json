package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonRawValue;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonRawValue {

    public long personId = 0;

    @JsonRawValue
    public String address =
        "{ \"street\" : \"Wall Street\", \"no\":1}";

}
