package com.demotodo.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonTest {

    @Test
    public void test() throws Exception {
        GsonBuilder builder = new GsonBuilder();
        builder.setVersion(2.0);

        Gson gson = builder.create();

        Person person = new Person();
        person.firstName = "John";
        person.lastName = "Doe";
        person.middleName = "Blocks";
        person.email = "john@doe.com";

        String personJson = gson.toJson(person);

        System.out.println(personJson);

        Person person2 = gson.fromJson("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"middleName\":\"Blocks\",\"email\":\"john@doe.com\"}", Person.class);
        assertEquals("John", person2.firstName);
        assertNull(person2.email);
    }

}