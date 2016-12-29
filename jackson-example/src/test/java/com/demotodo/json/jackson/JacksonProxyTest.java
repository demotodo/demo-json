package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by bribin.zheng on 2016/12/28.
 */
public class JacksonProxyTest {

    private JsonInterface json;

    @Before
    public void setUp() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        json = JacksonProxy.newProxyInstance(mapper, JsonInterface.class, MixinInterface.class);
    }

    @Test
    public void testReadSingle() throws IOException {
        Assert.assertEquals(json.readString("\"value\""), "value");
        Assert.assertEquals(json.readInt("42"), 42);
        Assert.assertEquals(json.readInteger("42"), (Integer) 42);
        Assert.assertEquals(json.readBean("{ \"myString\" : \"value\", \"myInt\" : 42 }"), new Bean("value", 42));
    }

    @Test
    public void testReadList() throws IOException {
        Assert.assertEquals(json.readStringList("[\"value0\",\"value1\",\"value2\",\"value3\"]"),
            Arrays.asList("value0", "value1", "value2", "value3"));
        Assert.assertEquals(json.readIntegerList("[0, 1, 2, 3]"),
            Arrays.asList(0, 1, 2, 3));
        Assert.assertTrue(Arrays.equals(json.readIntArray("[0, 1, 2, 3]"),
            new int[]{0, 1, 2, 3}));
        Assert.assertEquals(json.readBeanList("[{ \"myString\" : \"value0\", \"myInt\" : 0 }, { \"myString\" : \"value1\", \"myInt\" : 1 }]"),
            Arrays.asList(new Bean("value0", 0), new Bean("value1", 1)));
    }

    @Test
    public void testWrite() throws IOException {
        Assert.assertEquals(json.readString(json.writeString("value")), "value");
        Assert.assertEquals(json.readInt(json.writeInt(42)), 42);
        Assert.assertEquals(json.readInteger(json.writeInteger(42)), (Integer) 42);
        Assert.assertEquals(json.readBean(json.writeBean(new Bean("value", 42))), new Bean("value", 42));
    }

    @Test
    public void testWriteList() throws IOException {
        Assert.assertEquals(json.readStringList(json.writeStringList(Arrays.asList("value0", "value1", "value2", "value3"))),
            Arrays.asList("value0", "value1", "value2", "value3"));
        Assert.assertEquals(json.readIntegerList(json.writeIntegerList(Arrays.asList(0, 1, 2, 3))),
            Arrays.asList(0, 1, 2, 3));
        Assert.assertTrue(Arrays.equals(json.readIntArray(json.writeIntArray(new int[]{0, 1, 2, 3})),
            new int[]{0, 1, 2, 3}));
        Assert.assertEquals(json.readBeanList(json.writeBeanList(Arrays.asList(new Bean("value0", 0), new Bean("value1", 1)))),
            Arrays.asList(new Bean("value0", 0), new Bean("value1", 1)));

    }

    @Test
    public void testWriteVariants() {
        List<Bean> expected = Arrays.asList(new Bean("value0", 0), new Bean("value1", 1));

        StringWriter writer = new StringWriter();
        json.writeBeanList(expected, writer);
        Assert.assertEquals(json.readBeanList(writer.toString()), expected);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        json.writeBeanList(expected, out);
        Assert.assertEquals(json.readBeanList(out.toByteArray()), expected);
    }

    @Test
    public void testMixinInterface() {
        MixinInterface mixin = (MixinInterface) json;
        Map<String, String> map = mixin.readMap("{ \"key\" : \"value\" }");
        Assert.assertEquals(map, Collections.singletonMap("key", "value"));

        Map<String, Bean> map2 = mixin.readBeanMap("{ \"key\" : { \"myString\" : \"value0\", \"myInt\" : 0 } }");
        Assert.assertEquals(map2, Collections.singletonMap("key", new Bean("value0", 0)));
    }

    public static interface JsonInterface {
        String readString(String value) throws IOException;

        int readInt(String value);

        Integer readInteger(String value);

        Bean readBean(String value);

        List<String> readStringList(String value) throws IOException;

        int[] readIntArray(String value);

        List<Integer> readIntegerList(String value);

        List<Bean> readBeanList(String value);

        List<Bean> readBeanList(byte[] value);

        List<Bean> readBeanList(Reader value);

        List<Bean> readBeanList(InputStream value);

        String writeString(String value) throws IOException;

        String writeInt(int value);

        String writeInteger(Integer value);

        String writeBean(Bean value);

        String writeStringList(List<String> value) throws IOException;

        String writeIntArray(int[] value);

        String writeIntegerList(List<Integer> value);

        String writeBeanList(List<Bean> value);

        void writeBeanList(List<Bean> value, Writer writer);

        void writeBeanList(List<Bean> value, OutputStream writer);

        byte[] writeBeanListToByteArray(List<Bean> value);
    }

    public static interface MixinInterface {
        Map<String, String> readMap(String value);

        Map<String, Bean> readBeanMap(String value);
    }

    public static class Bean {
        private String myString;
        private int myInt;

        public Bean() {
        }

        public Bean(String myString, int myInt) {
            this.myInt = myInt;
            this.myString = myString;
        }

        public int getMyInt() {
            return myInt;
        }

        public void setMyInt(int myInt) {
            this.myInt = myInt;
        }

        public String getMyString() {
            return myString;
        }

        public void setMyString(String myString) {
            this.myString = myString;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Bean bean = (Bean) o;

            return !(myString != null ? !myString.equals(bean.myString) : bean.myString != null) &&
                myInt == bean.myInt;
        }

        @Override
        public int hashCode() {
            int result = myString != null ? myString.hashCode() : 0;
            result = 31 * result + myInt;
            return result;
        }
    }

}