package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;

/**
 * Created by bribin.zheng on 2016/12/14.
 */
public class XmlMapperTest {

    @Test
    public void test() throws Exception {

        // XmlFactory
        ObjectMapper xmlMapper = new XmlMapper();

        Car car = new Car();

        System.out.println(xmlMapper.writeValueAsString(car));
        // <Car><brand/><doors>0</doors></Car>
    }

    @Test
    public void test2() throws Exception {
        JacksonXmlModule module = new JacksonXmlModule();
        // and then configure, for example:
        module.setDefaultUseWrapper(false);

        XmlMapper xmlMapper = new XmlMapper(module);
        // and you can also configure AnnotationIntrospectors etc here:
    }

    @Test
    public void testIncrementalWrite() throws Exception {
        // First create Stax components we need
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        StringWriter out = new StringWriter();
        XMLStreamWriter sw = xmlOutputFactory.createXMLStreamWriter(out);

        // then Jackson components
        XmlMapper mapper = new XmlMapper(xmlInputFactory);

        sw.writeStartDocument();
        sw.writeStartElement("root");

        // Write whatever content POJOs...
        // SomePojo value1 = ...;
        // OtherPojo value2 = ...;
        // mapper.writeValue(sw, value1);
        // mapper.writeValue(sw, value2);

        // and/or regular Stax output
        sw.writeComment("Some insightful commentary here");
        sw.writeEndElement();
        sw.writeEndDocument();
    }

    @Test
    public void testIncrementalRead() throws Exception {
        XMLInputFactory f = XMLInputFactory.newFactory();
        File inputFile = new File("file.xml");
        XMLStreamReader sr = f.createXMLStreamReader(new FileInputStream(inputFile));

        XmlMapper mapper = new XmlMapper();
        sr.next(); // to point to <root>
        sr.next(); // to point to root-element under root

        // SomePojo value1 = mapper.readValue(sr, SomePojo.class);
        // sr now points to matching END_ELEMENT, so move forward

        sr.next(); // should verify it's either closing root or new start, left as exercise
        // OtherPojo value = mapper.readValue(sr, OtherPojo.class);

        // and more, as needed, then
        sr.close();
    }

}
