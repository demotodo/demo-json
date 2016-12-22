package com.demotodo.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/21.
 */
public class UnknowPropertyTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.addHandler(new DeserializationProblemHandler() {
            @Override
            public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser p, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException {
                System.out.println("unkown property: " + propertyName);
                p.skipChildren();
                return true;
            }
        });

        CarXml carXml = objectMapper.readValue("{\"brand\":\"BMW\",\"doors-xml\":4,\"windows-jackson\":3}", CarXml.class);

        assertEquals("BMW", carXml.brand);
        assertEquals(0, carXml.doors);
        assertEquals(3, carXml.windows);
    }

}
