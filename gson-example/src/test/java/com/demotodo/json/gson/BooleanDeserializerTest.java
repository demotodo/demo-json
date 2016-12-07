package com.demotodo.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class BooleanDeserializerTest {

    @Test
    public void test() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder()
            .serializeNulls()
            .registerTypeAdapter(Boolean.class, new BooleanDeserializer());

        Gson gson = gsonBuilder.create();

        Car car = gson.fromJson("{\"brand\":null,\"doors\":0,\"hasWindows\":1,\"s2\":null}", Car.class);
        assertTrue(car.hasWindows);
    }

}