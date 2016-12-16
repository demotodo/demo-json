package com.demotodo.json.jackson.polymorphic;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

/**
 * Created by bribin.zheng on 2016/12/16.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CUSTOM, include = JsonTypeInfo.As.PROPERTY, property = "command")
@JsonTypeIdResolver(CommandTypeIdResolver.class)
public abstract class Command {

    // https://www.thomaskeller.biz/blog/2013/09/10/custom-polymorphic-type-handling-with-jackson/

}
