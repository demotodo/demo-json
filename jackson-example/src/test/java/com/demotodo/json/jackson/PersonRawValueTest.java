package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonRawValueTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.writeValueAsString(new PersonRawValue()));
    }

}