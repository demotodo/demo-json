package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/28.
 */
public class ObjectReaderTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Compared to ObjectMapper, configuration is done using chainable "fluent"-style configuration calls which will create new ObjectReader instances and never change current instance (instances are immutable).
        // This is what fully guarantees thread-safety, so that instances can be shared as well as used as "blueprints" in cases where alternate configurations are needed.
        ObjectReader objectReader = objectMapper.readerFor(Car.class);

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        Car car = objectReader.readValue(carJson);

        assertEquals("Mercedes", car.brand);
    }

    @Test
    public void test2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Compared to ObjectMapper, configuration is done using chainable "fluent"-style configuration calls which will create new ObjectWriter instances and never change current instance (instances are immutable).
        // This is what fully guarantees thread-safety, so that instances can be shared as well as used as "blueprints" in cases where alternate configurations are needed.
        ObjectWriter objectWriter = objectMapper.writerFor(Car.class);

        Car car = new Car();
        car.brand = "BMW";

        System.out.println(objectWriter.writeValueAsString(car));
    }

}
