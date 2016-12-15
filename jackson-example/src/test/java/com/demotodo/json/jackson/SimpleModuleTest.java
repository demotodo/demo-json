package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/14.
 */
public class SimpleModuleTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule("MixInModule");
        simpleModule.setMixInAnnotation(Rectangle.class, RectangleMixIn.class);

        objectMapper.registerModule(simpleModule);

        Rectangle rectangle = new Rectangle(100, 200);

        System.out.println(objectMapper.writeValueAsString(rectangle));

        Rectangle res = objectMapper.readValue("{\"width\":100,\"height\":200}", Rectangle.class);

        assertEquals(100, res.getW());
        assertEquals(200, res.getH());
        assertEquals(20000, res.getSize());
    }

}
