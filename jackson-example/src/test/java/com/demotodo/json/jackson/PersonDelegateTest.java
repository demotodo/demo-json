package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/16.
 */
public class PersonDelegateTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        PersonDelegate person = objectMapper.readValue("{ \"id\": 10, \"name\": \"xxx\" }", PersonDelegate.class);

        assertEquals(10, person.getId());
        assertEquals("xxx", person.getName());
    }

}