package com.demotodo.json.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by bribin.zheng on 2016/12/8.
 */
public class GenericTypeTest {

    @Test
    public void test1() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        User user = objectMapper.readValue(getClass().getResourceAsStream("/user.json"), User.class);

        assertEquals("Joe", user.getName().getFirst());
        assertEquals(User.Gender.MALE, user.getGender());
        assertFalse(user.isVerified());
    }

    @Test
    public void test2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Map result = objectMapper.readValue(getClass().getResourceAsStream("/user.json"), Map.class);

        assertEquals(4, result.size());

        assertTrue(result.get("name") instanceof Map);  // nested Map

        ////////////////////////////////////////////////////////////////////////////////
        //   JSON Type         ===>             Java Type
        //     object                       LinkedHashMap<String, Object>
        //     array                        ArrayList<Object>
        //     string                       String
        //     number (no fraction)         Integer, Long or BigInteger
        //     number (fraction)            Double (configurable to use BigDecimal)
        //     true|false                   Boolean
        //     null                         null
        ////////////////////////////////////////////////////////////////////////////////
    }

    @Test
    public void testGeneric() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Map result = objectMapper.readValue(getClass().getResourceAsStream("/user2.json"), new TypeReference<Map<String, User>>() {
        });

        assertTrue(result.get("user1") instanceof User);
        assertEquals("Joe", ((User) result.get("user1")).getName().getFirst());

        assertTrue(result.get("user2") instanceof User);
    }

    @Test
    public void testGeneric2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Map result = objectMapper.readValue(getClass().getResourceAsStream("/user2.json"), TypeFactory.defaultInstance().constructMapType(Map.class, String.class, User.class));

        assertTrue(result.get("user1") instanceof User);
        assertEquals("Joe", ((User) result.get("user1")).getName().getFirst());

        assertTrue(result.get("user2") instanceof User);
    }

}