package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/28.
 */
public class JsonOrgTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JsonOrgModule());

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JSONObject obj = objectMapper.readValue(carJson, JSONObject.class);

        assertEquals("Mercedes", obj.get("brand"));
        assertEquals(5, obj.get("doors"));

        assertEquals("{\"doors\":5,\"brand\":\"Mercedes\"}", objectMapper.writeValueAsString(obj));
    }

    @Test
    public void test2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JsonOrgModule());

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JSONObject obj = objectMapper.readValue(carJson, JSONObject.class);

        Car car = objectMapper.convertValue(obj, Car.class);

        assertEquals("Mercedes", car.brand);
        assertEquals(5, car.doors);
    }

    @Test
    public void test3() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JsonOrgModule());

        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JSONObject obj = objectMapper.readValue(carJson, JSONObject.class);

        JsonNode jsonNode = objectMapper.valueToTree(obj);

        assertEquals("Mercedes", jsonNode.get("brand").asText());
    }

}
