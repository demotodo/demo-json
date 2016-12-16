## Core modules

Core modules are the foundation on which extensions (modules) build upon.
There are 3 such modules currently (as of Jackson 2.6):

* [Streaming](https://github.com/FasterXML/jackson-core) ([docs](https://github.com/FasterXML/jackson-core/wiki)) ("jackson-core") defines low-level streaming API, and includes JSON-specific implementations
* [Annotations](https://github.com/FasterXML/jackson-annotations) ([docs](https://github.com/FasterXML/jackson-annotations/wiki)) ("jackson-annotations") contains standard Jackson annotations
* [Databind](https://github.com/FasterXML/jackson-databind) ([docs](https://github.com/FasterXML/jackson-databind/wiki)) ("jackson-databind") implements data-binding (and object serialization) support on `streaming` package; it depends both on `streaming` and `annotations` packages


## Third-party datatype modules

These extensions are plug-in Jackson `Module`s (registered with `ObjectMapper.registerModule()`),
and add support for datatypes of various commonly used Java libraries, by adding
serializers and deserializers so that Jackson `databind` package (`ObjectMapper` / `ObjectReader` / `ObjectWriter`) can read and write these types.

Datatype modules directly maintained by Jackson team are:

* Standard [Collections](https://github.com/FasterXML/jackson-datatypes-collections) datatype modules:
    * [HPPC](https://github.com/FasterXML/jackson-datatypes-collections/tree/master/hppc): support for [High-Performance Primitive Containers](http://labs.carrotsearch.com/hppc.html) containers
    * [PCollections](https://github.com/FasterXML/jackson-datatypes-collections/tree/master/pcollections): support for [PCollections](http://pcollections.org/) datatypes (NEW in Jackson 2.7!)
* [Guava](https://github.com/FasterXML/jackson-datatype-guava): support for many of [Guava](http://code.google.com/p/guava-libraries/) datatypes.
* [Hibernate](https://github.com/FasterXML/jackson-datatype-hibernate): support for Hibernate features (lazy-loading, proxies)
* [Joda](https://github.com/FasterXML/jackson-datatype-joda): support for types of [Joda](http://joda-time.sourceforge.net/) date/time library datatypes
* [JDK7](https://github.com/FasterXML/jackson-datatype-jdk7): support for JDK 7 data types not included in previous versions
    * Deprecated in 2.7, as baseline JDK becomes 7, support to be included in `jackson-databind`
* [JDK8](https://github.com/FasterXML/jackson-datatype-jdk8): support for JDK 8 data types not included in previous versions, including `Optional` (but excluding new Date types which are in JSR-310 module below)
* [JSR-310 (Java 8 Date)](https://github.com/FasterXML/jackson-datatype-jsr310): support for "Java 8 Dates" (ones added in JDK 8)
    * Also, for pre-Java8 users can use one of alternate pre-Java8 backports:
        * [joschi/jackson-datatype-threetenbp](https://github.com/joschi/jackson-datatype-threetenbp)
        * [lldata/jackson-datatype-threetenbp](https://github.com/lldata/jackson-datatype-threetenbp)
* [JSR-353](https://github.com/FasterXML/jackson-datatype-jsr353): support for "Java JSON API" types (specifically, its tree model objects)
* [org.json](https://github.com/FasterXML/jackson-datatype-json-org): support for "org.json JSON lib" types like `JSONObject`, `JSONArray`

In addition, we are aware of additional modules that are not directly maintained by core Jackson team:

* [jackson-datatype-bolts](https://github.com/v1ctor/jackson-datatype-bolts) support for reading/writing types defined by [Yandex Bolts](https://bitbucket.org/stepancheg/bolts/wiki/Home) collection types (Functional Programming inspired immutable collections)
* [jackson-datatype-commons-lang3](https://github.com/bramp/jackson-datatype-commons-lang3) for types of [Apache Commons Lang v3](https://commons.apache.org/proper/commons-lang/)
* [jackson-datatype-money](https://github.com/zalando/jackson-datatype-money) for "Java Money", see [javax.money](http://javamoney.github.io/api.html)
* [javaslang-jackson](https://github.com/javaslang/javaslang-jackson) for [Javaslang](https://github.com/javaslang/javaslang) support (Feature-rich & self-contained functional programming in Java™ 8 and above)
* [jackson-datatype-json-lib](https://github.com/swquinn/jackson-datatype-json-lib) for supporting types defined by "net.sf.json" library (aka "json-lib")
* [jackson-datatype-jts](https://github.com/bedatadriven/jackson-datatype-jts) (JTS Geometry) for [GeoJSON](https://en.wikipedia.org/wiki/GeoJSON) support
* [jackson-lombok](https://github.com/xebia/jackson-lombok) for better support of [Lombok](http://projectlombok.org/) classes
* [jackson-datatype-mongo](https://github.com/commercehub-oss/jackson-datatype-mongo) for MongoDB types
    * NOTE: there are a few alternatives to handling MongoDB datatypes
* [jackson-module-objectify](https://github.com/tburch/jackson-module-objectify) for datatypes of [Objectify](https://github.com/objectify/objectify)
* [jackson-datatype-protobuf](https://github.com/HubSpot/jackson-datatype-protobuf) for handling datatypes defined by the standard Java protobuf library, developed by [HubSpot](http://www.hubspot.com/)
    * NOTE! This is different from `jackson-dataformat-protobuf` which adds support for encoding/decoding protobuf content but which does NOT depend on standard Java protobuf library
* [TinyTypes](https://github.com/caligin/tinytypes) includes Jackson module (group id `com.github.caligin.tinytypes`, artifact `tinytypes-jackson`)
* [jackson-datatype-vertx](https://github.com/Crunc/jackson-datatype-vertx) for reading/writing [Vert.x](http://vertx.io/) `org.vertx.java.core.json.JsonObject` objects (repackaged `org.json` node types)


## Other modules, stable

Other fully usable modules by FasterXML team include:

* [JAXB Annotations](https://github.com/FasterXML/jackson-module-jaxb-annotations): allow use of `JAXB` annotations as an alternative (in addition to or instead of) standard Jackson annotations
* [JDK 8 Parameter names](https://github.com/FasterXML/jackson-module-parameter-names): Module that adds support for using a new JDK8 feature: ability to access names of constructor and method parameters.
* [Base](https://github.com/FasterXML/jackson-modules-base) modules:
    * [Afterburner](https://github.com/FasterXML/jackson-modules-base/tree/master/afterburner): speed up databinding by 30-40% with bytecode generation to replace use of Reflection for field access, method/constructor calls
    * [Guice](https://github.com/FasterXML/jackson-modules-base/tree/master/guice): extension that allows injection values from Guice injectors (and basic Guice annotations), instead of standard `@JacksonInject` (or in addition to)
    * [Mr Bean](https://github.com/FasterXML/jackson-modules-base/tree/master/mrbean): "type materialization" -- let Mr Bean generate implementation classes on-the-fly (NO source code generation), to avoid monkey code
    * [OSGi](https://github.com/FasterXML/jackson-modules-base/tree/master/osgi): allows injection of values from OSGi registry, via standard Jackson `@JacksonInject` annotation
    * [Paranamer](https://github.com/FasterXML/jackson-modules-base/tree/master/paranamer): tiny extension for automatically figuring out creator (constructor, factory method) parameter names, to avoid having to specify `@JsonProperty`.
