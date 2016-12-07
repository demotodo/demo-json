package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonInjectTest {

    @Test
    public void test() throws Exception {
        InjectableValues inject = new InjectableValues.Std().addValue(String.class, "src");

        PersonInject personInject = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .reader(inject)
            .forType(PersonInject.class)
            .readValue("{\"id\":10, \"name\":\"abc\", \"male\": true, \"enabled\": 1}");

        assertEquals(10, personInject.id);
        assertEquals("abc", personInject.name);
        assertEquals("src", personInject.source);
    }

}