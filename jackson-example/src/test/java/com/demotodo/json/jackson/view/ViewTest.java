package com.demotodo.json.jackson.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;

/**
 * Created by bribin.zheng on 2016/12/29.
 */
public class ViewTest {

    @Test
    public void test() throws Exception {
        ValueBean bean = new ValueBean();
        bean.noview = "no view";
        bean.name = "my name";
        bean.address = "my address";
        bean.ssn = "my ssn";

        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        System.out.println(objectMapper.writeValueAsString(bean));

        ObjectWriter objectWriter = objectMapper.writerWithView(Views.Public.class);
        System.out.println(objectWriter.writeValueAsString(bean));

        objectWriter = objectMapper.writerWithView(Views.ExtendedPublic.class);
        System.out.println(objectWriter.writeValueAsString(bean));

        objectWriter = objectMapper.writerWithView(Views.Internal.class);
        System.out.println(objectWriter.writeValueAsString(bean));
    }

}