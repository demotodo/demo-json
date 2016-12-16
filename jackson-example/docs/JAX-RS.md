## Providers for JAX-RS

[Jackson JAX-RS Providers](https://github.com/FasterXML/jackson-jaxrs-providers) has handlers to add dataformat
support for JAX-RS implementations (like Jersey, RESTeasy, CXF).
Providers implement `MessageBodyReader` and `MessageBodyWriter`.
Supported formats currently include `JSON`, `Smile`, `XML`, `YAML` and `CBOR`.


## Usage: registering providers

Due to auto-registration, it should be possible to simply add Maven dependency (or include jar if using other build systems) and let JAX-RS implementation discover provider. 


## Usage: registering supporting datatypes module

As of Jackson 2.8, there is a small supporting datatype module, `jackson-datatype-jaxrs` (see under `datatypes/`).
It will not be auto-registered automatically (unless user calls `ObjectMapper.findAndRegisterModules()`); instead,
user has to register it by normal means:

```java
ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new Jaxrs2TypesModule());
// and then register mapper with JAX-RS provider(s)
```

and ensuring that configured mapper is used by JAX-RS providers.

It is possible that later versions of providers may offer additional ways to get datatype module registered.


## Annotations on resources

In addition to annotation value classes, it is also possible to use a subset
of Jackson annotations with provider.

Here is a short list of supported annotations that work with all formats:

* `@JsonView` can be used to define active view for specific endpoint
* `@JsonRootName` can be used to specify alternate rootname; most often used with XML, but possibly with JSON as well.
* `@JacksonAnnotationsInside` meta-annotation may be used as a marker, to create "annotation bundles", similar to how they are used with value type annotations
* `com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures` can be used with all provid to enable/disable
    * `SerializationFeature` / `DeserializationFeature` for data-binding configuration
    * `JsonParser.Feature` / `JsonGenerator.Feature` for low(er) level Streaming read/write options

In addition there are format-specific annotations that may be used:

* JSON has:
    * `com.fasterxml.jackson.jaxrs.json.annotation.JSONP` to define `JSONP` wrapping for serialized result
