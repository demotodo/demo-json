package com.demotodo.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class BooleanSerializerTest {

    @Test
    public void test() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder()
            .serializeNulls()
            .registerTypeAdapter(Boolean.class, new BooleanSerializer());

        Gson gson = gsonBuilder.create();

        Car car = new Car();
        car.hasWindows = true;
        System.out.println(gson.toJson(car));

        System.out.println(gson.toJson(new Car())); // will print 'null' for Boolean type with value 'null'
    }

}