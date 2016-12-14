package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/9.
 */
public class MixInModuleTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new MixInModule());

        Rectangle rectangle = new Rectangle(100, 200);

        System.out.println(objectMapper.writeValueAsString(rectangle));

        Rectangle res = objectMapper.readValue("{\"width\":100,\"height\":200}", Rectangle.class);

        assertEquals(100, res.getW());
        assertEquals(200, res.getH());
        assertEquals(20000, res.getSize());
    }

}