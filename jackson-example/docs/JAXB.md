## JAXB

Jackson supports some level of interoperability with JAXB API (javax.xml.bin):

- With Jackson 1.1, it is possible to use JAXB annotations in addition to (or instead of) core Jackson annotations
- In addition to setting Map and Collection properties, it is also possible to do 'JAXB style' "modify-via-get" access (call getter-method to get Map/Collection instance, modify directly, not call setter method at all): this is enabled by default, used iff no setter is located for a Map/Collection property (but getter is found).
    - Can be disabled using `DeserializationConfig.USE_GETTERS_AS_SETTERS` (enabled by default)


## Usage: JAXB annotations

To enable use of JAXB annotations, one must add `JaxbAnnotationIntrospector` provided by this module. 
There are two ways to do this:

* Register `JaxbAnnotationModule`, or
* Directly add `JaxbAnnotationIntrospector`

Module registration works in standard way:

```
JaxbAnnotationModule module = new JaxbAnnotationModule();
// configure as necessary
objectMapper.registerModule(module);
```

and the alternative -- explicit configuration is done as:

```
AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
// if ONLY using JAXB annotations:
mapper.setAnnotationIntrospector(introspector);
// if using BOTH JAXB annotations AND Jackson annotations:
AnnotationIntrospector secondary = new JacksonAnnotationIntrospector();
mapper.setAnnotationIntrospector(new AnnotationIntrospector.Pair(introspector, secondary);
```

Note that by default Module version will use JAXB annotations as the primary, and Jackson annotations as secondary source; but you can change this behavior
