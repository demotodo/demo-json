package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class ObjectMapperTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        Car car = objectMapper.readValue(carJson, Car.class);

        System.out.println("car.brand = " + car.brand);
        System.out.println("car.doors = " + car.doors);
    }

    @Test
    public void test2() throws Exception {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node = objectMapper.readValue(carJson, JsonNode.class);

        System.out.println(node.get("brand").asText());
    }

    @Test
    public void testJsonNode() throws Exception {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
            "  \"owners\" : [\"John\", \"Jack\", \"Jill\"]," +
            "  \"nestedObject\" : { \"field\" : \"value\" } }";

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode node = objectMapper.readValue(carJson, JsonNode.class);

        JsonNode brandNode = node.get("brand");
        String brand = brandNode.asText();
        System.out.println("brand = " + brand);

        JsonNode doorsNode = node.get("doors");
        int doors = doorsNode.asInt();
        System.out.println("doors = " + doors);

        JsonNode array = node.get("owners");
        JsonNode jsonNode = array.get(0);
        String john = jsonNode.asText();
        System.out.println("owners/0  = " + john);

        JsonNode child = node.get("nestedObject");
        JsonNode childField = child.get("field");
        String field = childField.asText();
        System.out.println("field = " + field);
    }

    @Test
    public void testReader() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 4 }";
        Reader reader = new StringReader(carJson);
        Car car = objectMapper.readValue(reader, Car.class);

        assertEquals("Mercedes", car.brand);
        assertEquals(4, car.doors);
    }

    @Test
    public void testWrite() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Car car = new Car();
        car.brand = "BMW";
        car.doors = 4;

//        objectMapper.writeValue(new FileOutputStream("data/output-2.json"), car);
        System.out.println(objectMapper.writeValueAsString(car));
    }

}
