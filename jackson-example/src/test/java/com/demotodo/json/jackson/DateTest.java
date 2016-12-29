package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.junit.Test;

import java.util.Date;

/**
 * Created by bribin.zheng on 2016/12/28.
 */
public class DateTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);   // enabled by default

        Date now = new Date();

        System.out.println(objectMapper.writeValueAsString(now));
    }

    @Test
    public void test2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setDateFormat(new ISO8601DateFormat());

        Date now = new Date();

        System.out.println(objectMapper.writeValueAsString(now));
    }

    @Test
    public void test3() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        DateStuff dateStuff = new DateStuff();
        dateStuff.creationTime = new Date();

        System.out.println(objectMapper.writeValueAsString(dateStuff));
    }

    static class DateStuff {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd,HH:mm", timezone = "GMT+0800")
        Date creationTime;
    }

}
