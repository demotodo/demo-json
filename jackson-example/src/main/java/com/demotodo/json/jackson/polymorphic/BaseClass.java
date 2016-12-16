package com.demotodo.json.jackson.polymorphic;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by bribin.zheng on 2016/12/16.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class BaseClass {

}

