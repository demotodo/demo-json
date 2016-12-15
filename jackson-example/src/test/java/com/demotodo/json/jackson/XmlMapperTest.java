package com.demotodo.json.jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

/**
 * Created by bribin.zheng on 2016/12/14.
 */
public class XmlMapperTest {

    @Test
    public void test() throws Exception {

//        XmlFactory
        XmlMapper xmlMapper = new XmlMapper();

        Car car = new Car();

        System.out.println(xmlMapper.writeValueAsString(car));
        // <Car><brand/><doors>0</doors></Car>
    }

}
