package com.demotodo.json.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.junit.Test;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class JsonParserTest {

    @Test
    public void test() throws Exception {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";

        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(carJson);

        Car car = new Car();
        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();
            System.out.println(jsonToken);

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = parser.getCurrentName();
                System.out.println(fieldName);

                jsonToken = parser.nextToken();
                System.out.println(jsonToken);

                if ("brand".equals(fieldName)) {
                    car.brand = parser.getValueAsString();
                } else if ("doors".equals(fieldName)) {
                    car.doors = parser.getValueAsInt();
                }
            }
        }

        System.out.println("car.brand = " + car.brand);
        System.out.println("car.doors = " + car.doors);
    }

}
