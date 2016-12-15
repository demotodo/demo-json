package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * Created by bribin.zheng on 2016/12/14.
 */
public class MappingJsonFactoryTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // use ObjectMapper as ObjectCodec
        MappingJsonFactory factory = new MappingJsonFactory(objectMapper);

        // ...
    }

}
