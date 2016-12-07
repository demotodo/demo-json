package com.demotodo.json.gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.junit.Test;

import java.io.StringReader;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class JsonReaderTest {

    @Test
    public void test() throws Exception {
        String json = "{\"brand\" : \"Toyota\", \"doors\" : 5}";

        JsonReader jsonReader = new JsonReader(new StringReader(json));

        while (jsonReader.hasNext()) {
            JsonToken nextToken = jsonReader.peek();
            System.out.println(nextToken);

            if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {

                jsonReader.beginObject();

            } else if (JsonToken.NAME.equals(nextToken)) {

                String name = jsonReader.nextName();
                System.out.println(name);

            } else if (JsonToken.STRING.equals(nextToken)) {

                String value = jsonReader.nextString();
                System.out.println(value);

            } else if (JsonToken.NUMBER.equals(nextToken)) {

                long value = jsonReader.nextLong();
                System.out.println(value);

            }
        }
    }

}
