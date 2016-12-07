package com.demotodo.json.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class BooleanSerializer implements JsonSerializer<Boolean> {

    @Override
    public JsonElement serialize(Boolean value, Type type, JsonSerializationContext jsonSerializationContext) {
        if (value) {
            return new JsonPrimitive(1);
        }
        return new JsonPrimitive(0);
    }

}
