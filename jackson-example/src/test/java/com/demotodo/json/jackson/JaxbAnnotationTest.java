package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bribin.zheng on 2016/12/16.
 */
public class JaxbAnnotationTest {

    @Test
    public void test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        JaxbAnnotationModule jaxbAnnotationModule = new JaxbAnnotationModule();
        objectMapper.registerModule(jaxbAnnotationModule);

        CarXml carXml = new CarXml();
        carXml.brand = "BMW";
        carXml.doors = 4;

        assertEquals("{\"brand-xml\":\"BMW\",\"doors-xml\":4,\"windows-jackson\":0}", objectMapper.writeValueAsString(carXml));
    }

    @Test
    public void test2() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        JaxbAnnotationIntrospector jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector(objectMapper.getTypeFactory());
        objectMapper.setAnnotationIntrospector(jaxbAnnotationIntrospector);

        CarXml carXml = new CarXml();
        carXml.brand = "BMW";
        carXml.doors = 4;

        assertEquals("{\"windows\":0,\"brand-xml\":\"BMW\",\"doors-xml\":4}", objectMapper.writeValueAsString(carXml));
    }

    @Test
    public void test3() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        JaxbAnnotationIntrospector jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector(objectMapper.getTypeFactory());
        JacksonAnnotationIntrospector jacksonAnnotationIntrospector = new JacksonAnnotationIntrospector();
        objectMapper.setAnnotationIntrospector(new AnnotationIntrospectorPair(jaxbAnnotationIntrospector, jacksonAnnotationIntrospector));

        CarXml carXml = new CarXml();
        carXml.brand = "BMW";
        carXml.doors = 4;

        assertEquals("{\"brand-xml\":\"BMW\",\"doors-xml\":4,\"windows-jackson\":0}", objectMapper.writeValueAsString(carXml));
    }

    @Test
    public void test4() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        JacksonAnnotationIntrospector jacksonAnnotationIntrospector = new JacksonAnnotationIntrospector();
        JaxbAnnotationIntrospector jaxbAnnotationIntrospector = new JaxbAnnotationIntrospector(objectMapper.getTypeFactory());
        objectMapper.setAnnotationIntrospector(new AnnotationIntrospectorPair(jacksonAnnotationIntrospector, jaxbAnnotationIntrospector));

        CarXml carXml = new CarXml();
        carXml.brand = "BMW";
        carXml.doors = 4;

        assertEquals("{\"brand-xml\":\"BMW\",\"doors-xml\":4,\"windows-jackson\":0}", objectMapper.writeValueAsString(carXml));
    }

}
