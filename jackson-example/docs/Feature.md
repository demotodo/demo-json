# Jackson features

https://github.com/FasterXML/jackson-databind/wiki/JacksonFeatures


## Databind features

For core databinding, features are divided further in 3 categories:

- [Mapper features](https://github.com/FasterXML/jackson-databind/wiki/Mapper%20Features)
- [Serialization features](https://github.com/FasterXML/jackson-databind/wiki/Serialization%20Features) (writing POJOs as JSON and other formats)
- [Deserialization features](https://github.com/FasterXML/jackson-databind/wiki/Deserialization%20Features) (reading JSON and other formats as POJOs)

Of these, Mapper Features can only be modified before using `ObjectMapper`: changes after use may or may not take effects. 
If you need different configured mapper (or `ObjectWriter` or `ObjectReader`), you will need a new mapper instance: you can do this with `ObjectMapper.copy()` -- it will copy current settings, but allow changes to Mapper Features.

Both Serialization and Deserialization features may, however, be configured by constructing new `ObjectReader` and `ObjectWriter` instances.

Since both `ObjectReader` / `ObjectWriter` are immutable, methods will create new readers/writers and configuration is fully thread-safe. 
Cost of these instances is minimal (unlike cost of creating `ObjectMappers`, which is substantial) so while they may be freely shared and pooled, this is typically not necessary nor improve performance measurably.


### Mapper features

Jackson defines a set of per-mapper configuration, which can ONLY be defined before using `ObjectMapper` -- meaning that these settings can not be changed on-the-fly, on per-request basis. 
They configure fundamental POJO introspection details, and resulting built objects (serializers, deserializers, related) are heavily cached. 
If you need differing settings for these, you have to use separate `ObjectMapper` instances.


### Seerialization features

Jackson defines a set of features that relate to serialization (writing Java objects as JSON), and that can be changed on per-call basis (by using `ObjectWriter`). For example:

```
final ObjectWriter w = objectMapper.writer();
// enable one feature, disable another
byte[] json = w
  .with(SerializationFeature.INDENT_OUTPUT)
  .without(FAIL_ON_EMPTY_BEANS.WRAP_EXCEPTIONS)
  .writeValueAsBytes(value);
```

You can also change defaults for `ObjectMapper`, to be used as the base for all `ObjectWriter` instances, using `enable(feature)`, `disable(feature)` and `configure(feature, state)` methods.


### Deserialization features

Jackson defines a set of features that relate to deserialization (reading JSON into Java Objects), and that can be changed on per-call basis, by using `ObjectReader`; for example:

```
final ObjectReader r = objectMapper.reader(MyType.class);
// enable one feature, disable another
MyType value = r
  .with(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
  .without(DeserializationFeature.WRAP_EXCEPTIONS)
  .readValue(source);
```

You can also change defaults for `ObjectMapper`, to be used as the base for all `ObjectReader` instances, using `enable(feature)`, `disable(feature)` and `configure(feature, state)` methods.


## Streaming API features

Similarly, low-level features are divided in 3 categories:

- [Factory features](https://github.com/FasterXML/jackson-core/wiki/JsonFactory-Features)
- [Generator features](https://github.com/FasterXML/jackson-core/wiki/JsonGenerator-Features) (generating content encoded as JSON and other supported data formats)
- [Parser features](https://github.com/FasterXML/jackson-core/wiki/JsonParser-Features) (decoding content encoded as JSON and other supported data formats)

Similar to databind features, Factory Features may only be changed after constructing factory (either directly, or automatically via `ObjectMapper`), but before usage. 
Parser and Generator Features may be modified dynamically between calls, using `ObjectReader` and `ObjectWriter` methods similar to how Serialization and Deserialization features may be enabled and disabled.


### Jackson Streaming: JsonFactory.Feature

Jackson Streaming API has a set of on/off features that change aspects that have effect on both reading and writing JSON. Settings are set on `JsonFactory`, and generally can NOT be dynamically changed after first `JsonParser` or `JsonGenerator` has been created.

```
JsonFactory f = new JsonFactory();
f.disable(JsonFactory.Feature.CANONICALIZE_FIELD_NAMES);
```


### Jackson Streaming: JsonGenerator.Feature

Jackson Streaming API has a set of on/off features that change the way streaming parsing is done. Features can be directly enabled/disabled on `JsonGenerator` instances, but more commonly default values are changed on `JsonFactory` instances. For example:

```
JsonFactory f = new JsonFactory();
f.enable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
f.disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET);
JsonGenerator g = f.createGenerator(destination);
g.enable(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION);
```


### Jackson Streaming: JsonParser.Feature

Jackson Streaming API has a set of on/off features that change the way streaming parsing is done. Features can be directly enabled/disabled on `JsonParser` instances, but more commonly default values are changed on `JsonFactory` instances. For example:

```
JsonFactory f = new JsonFactory();
f.enable(JsonParser.Feature.ALLOW_COMMENTS);
f.disable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
JsonParser p = f.createParser(jsonSource);
p.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
```
