package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonSerializerTest {

    @Test
    public void testSerializer() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        PersonSerializer person = objectMapper.readerFor(PersonSerializer.class)
            .readValue("{\"id\":10, \"name\":\"abc\", \"male\": true, \"enabled\": 1}");

        assertEquals(10, person.id);
        assertNull(person.name);    // will be ignored
        assertTrue(person.male);
        assertTrue(person.enabled);

        System.out.println(objectMapper.writeValueAsString(person));

    }

}