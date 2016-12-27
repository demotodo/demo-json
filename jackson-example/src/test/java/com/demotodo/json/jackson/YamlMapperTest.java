package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/27.
 */
public class YamlMapperTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        String yaml = "brand: BWM\ndoors: 4";

        Car car = objectMapper.readValue(yaml, Car.class);

        assertEquals("BWM", car.brand);
        assertEquals(4, car.doors);

        // YAMLFactory factory = new YAMLFactory();
        // JsonParser parser = factory.createJsonParser(yamlString); // don't be fooled by method name...
        // while (parser.nextToken() != null) {
        //     // do something!
        // }
    }
}
