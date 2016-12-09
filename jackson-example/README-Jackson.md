## Processing Models

**Which processing models (streaming, tree, objects) does Jackson implement?**

Three main methods (one with 2 alternatives) are implemented:

- Streaming/incremental parsing (reading) and generation (writing) of JSON content
- Tree model (based on JsonNodes)
- Data binding to/from Java objects (POJOs, Beans, primitives, lists/arrays/maps)
    - alternative "untyped" binding to only use Lists/Maps/String/Boolean/Number/null (bind to Object.class)


## Is ObjectMapper thread-safe?

Short answer: yes

Long answer: yes, as long as you always configure instance before use, and do not call configure methods during operation (or synchronize such calls appropriately). Usually it is better to construct separate mapper instance if configurations differ in any case.

Further: it is beneficial to use just one instance (or small number of instances) for data binding; many optimizations for reuse (of symbol tables, some buffers) depend on ObjectMapper instances being reused.

For more information, check out [JacksonFAQThreadSafety](http://wiki.fasterxml.com/JacksonFAQThreadSafety).


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


## Deserializing Abstract types

**If a property has abstract type, how can I make it deserialize into proper concrete type?**

There are two distinct cases: if you just have a simple one-to-one mapping from abstract type (interface) into concrete implementation, there are multiple ways to handle this, shown below. If you have multiple implementation types, please look at the next question ("deserializing polymorphic types")

There are multiple ways to indicate specific implementation class of an abstract type:

- Annotation `@JsonDeserialize(as=Impl.class)` is a simple way to indicate implementation class, and can be used for both classes (default impl for interface / abstract class annotated) and properties (default type for property value annotated)
    - For `java.util.List` values you would use `@JsonDeserialize(contentAs=ValueTypeImpl.class)` instead, and for `java.util.Map` keys, `@JsonDeserialize(keyAs=KeyTypeImpl.class)`.
- You can also register abstract type mappings using `Module` interface: when using `SimpleModule`, you can call: `simpleModule.addAbstractTypeMapping(MyInterface.class, MyImpl.class)`;


## Deserializing Polymorphic types

**But what if there are multiple subtypes for given declared type?**

If there are multiple alternate implementations, you need to enable addition of so-called Type Information. For full description, refer to JacksonPolymorphicDeserialization, but there are two basic approaches:

- Use `@JsonTypeInfo` on base type (or next to property) to indicate that additional type information is to be included when serializing values of annotated type (or property).
- Enable "default typing" (using `ObjectMapper.enableDefaultTyping()`), which works similar to as if `@JsonTypeInfo` was implicitly added to set of types (set defined by default typing rules: default rule applies it to java.lang.Object and all abstract types)

In both cases what happens is that during serialization, a type identifier (class name or logical configurable type name) is included in JSON (using one of multiple placement strategies; most common being as a simple property). And when deserializing, deserializer expects to find this type identifier and will use it to correctly determine expected type to create, and then deserialize instance of that concrete type.


## Tree Model

**Can I compare JsonNode instances for equality? (and by extension: "Can I compare full JSON trees by just comparing root nodes")**

Yes! JsonNode instances implemented expected deep value comparison, using expected conceptual rules (like: order of JSON Object properties is insignificant for equality, but matters for JSON Arrays).

So you can compare equality of JSON trees by just comparing root nodes like so:
```
    boolean areEqual = tree1RootNode.equals(tree2RootNode);
```


## JAX-RS

**Can I use Jackson to do JSON serialization with JAX-RS?**

Yes. Starting with Jackson 1.0, there is jar (jackson-jaxrs-VERSION.jar) that implements JAX-RS `MessageBodyReader` and `MessageBodyWriter`, needed for converting Java beans to/from JSON.

NEW: With version 1.3, there is an improved JAX-RS provider available, `org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider`, which by default uses both JAXB and Jackson annotations. 
This can be used in place of earlier `JacksonJsonProvider` (which is its super-class), and provides extended set of configuration methods. 
It does depend on features from jax-xc package, so make sure it is also included if using this new provider.

In addition to adding jackson-jaxrs jar (and jackson core and mapper jars it depends on, if not bundled by the JAX-RS implementation), you will also need to register provider. 
There are 2 ways to do this:

- Add root provider with JAX-RS registration mechanism; for example, by returning provider class/instance from Rest `Application` class.
- Add `META-INF/services/javax.ws.rs.ext.MessageBodyReader` file with one entry, provider class name (`org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvide`) (and similarly for `MessageBodyWriter`) to a jar that gets loaded by JAX-RS implementation (this can be done by post-processing jackson-jaxrs jar, for example).


## JAXB

Jackson supports some level of interoperability with JAXB API (javax.xml.bin):

- With Jackson 1.1, it is possible to use JAXB annotations in addition to (or instead of) core Jackson annotations
- In addition to setting Map and Collection properties, it is also possible to do 'JAXB style' "modify-via-get" access (call getter-method to get Map/Collection instance, modify directly, not call setter method at all): this is enabled by default, used iff no setter is located for a Map/Collection property (but getter is found).
    - Can be disabled using `DeserializationConfig.USE_GETTERS_AS_SETTERS` (enabled by default)

### Enabling JAXB annotation support

By default, only core Jackson annotations are used for configuring data binding aspects. 
To enable JAXB annotation support, you need to:

- Include jackson-xc jar, which contains `org.codehaus.jackson.xc.JaxbAnnotationIntrospector` (Jackson 1.x)
    - or, `com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector` (Jackson 2.x)
- Register this annotation introspector

Annotation introspector to use for serialization process is configured separately for serialization and deserialization purposes; for example:
```
    ObjectMapper mapper = new ObjectMapper();
    AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
    // make deserializer use JAXB annotations (only)
    mapper.getDeserializationConfig().setAnnotationIntrospector(introspector);
    // make serializer use JAXB annotations (only)
    mapper.getSerializationConfig().setAnnotationIntrospector(introspector);
```


## Polymorphic type handling

Starting with version 1.5, Jackson allows fully configurable Polymorphic Type Handling, using following annotations:

- `@JsonTypeInfo` (class) is the main annotation used to enable polymorphic type handling for a type and its subtypes.
- `@JsonSubTypes` (class) allows enumerating sub-types (classes) of given class: it is used if (and only if) sub-types can not be otherwise detected. This is the case when logical type names are used instead of physical Java class names
- `@JsonTypeName` (class) is used to define logical type name for a class
- `@JsonTypeResolver` (class) can be used to plug in a custom type information handler, to replace default one (for specific class)
- `@JsonTypeIdResolver` (class) can be used to replace standard type id converter (type to/from JSON String) with a custom version; for example, to create more convenient handler for using logical type names.





## References
- http://wiki.fasterxml.com/JacksonFAQ
- http://wiki.fasterxml.com/JacksonAnnotations
- http://wiki.fasterxml.com/JacksonJAXBAnnotations
