package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import org.junit.Test;

/**
 * Created by bribin.zheng on 2016/12/14.
 */
public class TokenBufferTest {

    @Test
    public void test() throws Exception {
        Car car = new Car();
        car.brand = "BMW";
        car.doors = 4;

        ObjectMapper objectMapper = new ObjectMapper();
        TokenBuffer buffer = new TokenBuffer(objectMapper, false);

        //TODO

// in old version
//        TokenBuffer buffer = new TokenBuffer();
//        // serialize object as JSON tokens (but don't serialize as JSON text!)
//        objectMapper.writeValue(buffer, myBean);
//        // read back as tree
//        JsonNode root = objectMapper.readTree(buffer.asParser());
//        // modify some more, write out
//        // ...
//        String jsonText = objectMapper.writeValueAsString(root);

    }

}
