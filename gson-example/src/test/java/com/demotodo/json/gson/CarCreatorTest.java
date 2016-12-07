package com.demotodo.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class CarCreatorTest {

    @Test
    public void test() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder()
            .serializeNulls()
            .registerTypeAdapter(Car.class, new CarCreator());

        Gson gson = gsonBuilder.create();

        String carJson = "{ \"doors\" : 4 }";
        Car car = gson.fromJson(carJson, Car.class);

        assertEquals("Toyota", car.brand);
        System.out.println(gson.toJson(car));
    }

}