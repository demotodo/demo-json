package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Created by bribin.zheng on 2016/12/16.
 */
public class EnumTest {

    @Test(expected = Exception.class)
    public void test() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        mapper.readValue("\"foo\"", MyEnum.class);
    }

    @Test
    public void test2() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);

        MyEnum value = mapper.readValue("\"foo\"", MyEnum.class);
        assertSame(MyEnum.UNKNOWN, value);
    }

    @Test
    public void test3() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);

        MyEnum value = mapper.readValue("\"foo\"", MyEnum.class);
        assertNull(value);
    }

}
