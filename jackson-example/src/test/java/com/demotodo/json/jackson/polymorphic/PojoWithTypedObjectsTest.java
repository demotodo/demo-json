package com.demotodo.json.jackson.polymorphic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/16.
 */
public class PojoWithTypedObjectsTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        PojoWithTypedObjects value = objectMapper.readValue(
            "{ \"items\" : [ { \"@class\":\"com.demotodo.json.jackson.polymorphic.Impl2\", \"name\":\"Bob\" }, "
                + "{ \"@class\":\"com.demotodo.json.jackson.polymorphic.Impl1\", \"x\":13 } ]}",
            PojoWithTypedObjects.class);

        assertEquals(2, value.items.size());
        assertEquals("Bob", ((Impl2) value.items.get(0)).name);
        assertEquals(13, ((Impl1) value.items.get(1)).x);
    }

}