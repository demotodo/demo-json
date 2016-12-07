package com.demotodo.json.jackson;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class JsonGeneratorTest {

    @Test
    public void test() throws Exception {
        JsonFactory factory = new JsonFactory();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        JsonGenerator generator = factory.createGenerator(bos, JsonEncoding.UTF8);

        generator.writeStartObject();
        generator.writeStringField("brand", "Mercedes");
        generator.writeNumberField("doors", 5);
        generator.writeEndObject();

        generator.close();

        System.out.println(bos.toString());
    }

}
