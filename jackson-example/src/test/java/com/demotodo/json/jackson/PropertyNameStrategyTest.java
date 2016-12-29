package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.Test;

/**
 * Created by bribin.zheng on 2016/12/27.
 */
public class PropertyNameStrategyTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);

        System.out.println(objectMapper.writeValueAsString(new Car()));
    }

    //   class MyBean {
    //       private String inReply;
    //       public void setInReplyToUserId(String s) { inReply = s; }
    //       public String getInReplyToUserId() { return inReply; }
    //   }
    //
    //   class MyNaming extends PropertyNamingStrategy {
    //       @Override
    //       public String nameForGetterMethod(MapperConfig<?> config,
    //                                         AnnotatedMethod method, String defaultName)
    //       {
    //           // Replace underscore+letter with upper-case(letter)
    //           // (left as exercise to reader!)
    //           return convertName(defaultName);
    //       }
    //   }
    //
    // objectMapper.setPropertyNamingStrategy(new MyNaming());
    //   // and serialize, deserialize values as
}
