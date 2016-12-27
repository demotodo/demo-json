## Deserializing Generic types

Due to Java Type Erasure (losing generics declarations when Classes are compiled), it is NOT possible to specify generic types to bind to. In order to pass full generics type information to, say, bind JSON into List<MyBean>, you have to use methods that take either TypeReference or JavaType. Most commonly former is used; and it is used like so:
```
    List<MyBean> result = mapper.readValue(src, new TypeReference<List<MyBean>>() { });
```
to use JavaType, you need to construct method by passing java.lang.reflect.Type into TypeFactory (check out JavaDocs for details), or:
```
    List<MyBean> result = mapper.readValue(src, TypeFactory.defaultInstance().constructArrayType(MyBean.class));
```
if you don't like anonymous inner classes (you can similarly construct all kinds of generics types, with `TypeFactory.constructParametricType(...))`.
