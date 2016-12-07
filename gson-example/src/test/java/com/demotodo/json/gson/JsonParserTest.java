package com.demotodo.json.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class JsonParserTest {

    @Test
    public void test() throws Exception {
        JsonParser parser = new JsonParser();

        String json = "{ \"f1\":\"Hello\",\"f2\":{\"f3\":\"World\"}}";

        JsonElement jsonTree = parser.parse(json);

        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();

            JsonElement f1 = jsonObject.get("f1");
            System.out.println(f1.getAsString());

            JsonElement f2 = jsonObject.get("f2");
            if (f2.isJsonObject()) {
                JsonObject f2Obj = f2.getAsJsonObject();

                JsonElement f3 = f2Obj.get("f3");
                System.out.println(f3.getAsString());
            }
        }
    }

}
