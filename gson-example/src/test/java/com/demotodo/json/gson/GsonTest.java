package com.demotodo.json.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class GsonTest {

    @Test
    public void testGson1() throws Exception {
        Gson gson = new Gson();

        String json = "{\"brand\":\"Jeep\", \"doors\": 3}";
        Car car = gson.fromJson(json, Car.class);

        assertEquals("Jeep", car.brand);
        assertEquals(3, car.doors);
        assertNull(car.hasWindows);

        System.out.println(gson.toJson(car));
    }

    @Test
    public void testGson2() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        String json = "{\"brand\":\"Jeep\", \"doors\": 3}";
        Car car = gson.fromJson(json, Car.class);

        assertEquals("Jeep", car.brand);
        assertEquals(3, car.doors);
        assertNull(car.hasWindows);

        System.out.println(gson.toJson(car));
    }

    @Test
    public void testIgnore() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder()
            .serializeNulls()
            .excludeFieldsWithoutExposeAnnotation();

        Gson gson = gsonBuilder.create();

        Car car = new Car();

        System.out.println(gson.toJson(car));
    }

    @Test
    public void testIgnore2() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder()
            .serializeNulls()
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return "brand".equals(f.getName());
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            });

        Gson gson = gsonBuilder.create();

        Car car = new Car();

        System.out.println(gson.toJson(car));
    }

}
