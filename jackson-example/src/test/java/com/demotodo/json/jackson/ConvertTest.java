package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/28.
 */
public class ConvertTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int[] ints = objectMapper.convertValue(numbers, int[].class);

        assertArrayEquals(new int[]{1, 2, 3}, ints);
    }

    @Test
    public void test2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Car car = new Car();
        car.brand = "BMW";
        car.doors = 4;

        Map<String, ObjectMapper> map = objectMapper.convertValue(car, Map.class);

        assertEquals("BMW", map.get("brand"));
        assertEquals(4, map.get("doors"));

        Car car2 = objectMapper.convertValue(map, Car.class);

        assertEquals("BMW", car2.brand);
        assertEquals(4, car2.doors);
    }

    @Test
    public void test3() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String base64 = "TWFuIGlzIGRpc3Rpbmd1aXNoZWQsIG5vdCBvbmx5IGJ5IGhpcyByZWFzb24sIGJ1dCBieSB0aGlz";
        byte[] binary = objectMapper.convertValue(base64, byte[].class);

        assertEquals("Man is distinguished, not only by his reason, but by this", new String(binary));
    }

}
