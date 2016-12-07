package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonValueTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        PersonValue personValue = new PersonValue();
        personValue.name = "abc";

        System.out.println(objectMapper.writeValueAsString(personValue));
    }

}