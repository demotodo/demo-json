package com.demotodo.json.jackson.mrbean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/29.
 */
public class MrBeanTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new MrBeanModule());

        Coordinates coordinates = objectMapper.readValue("{ \"x\": 1, \"y\": 2 }", Coordinates.class);

        assertEquals(1, coordinates.getX());
        assertEquals(2, coordinates.getY());
    }
}
