package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

/**
 * Created by bribin.zheng on 2016/12/16.
 */
public enum MyEnum {

    A,
    B,

    @JsonEnumDefaultValue
    UNKNOWN
}
