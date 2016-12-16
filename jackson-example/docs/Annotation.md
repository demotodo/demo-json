# Jackson Annotations

Multiple sets of annotations may be used to configure how Jackson databinding works.
Document is split along the lines of Jackson component that defines annotations and/or adds support for handling annotations.

* [Core Jackson annotations](https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations) are defined by `jackson-annotations` core component, which has no dependencies to any other package
* [Jackson Databind annotations](https://github.com/FasterXML/jackson-databind/wiki/Databind-Annotations) are defined by `jackson-databind` and depend on types of `jackson-core` and `jackson-annotations`
* [Support for JAXB annotations](https://github.com/FasterXML/jackson-module-jaxb-annotations/) is optional add-on, usually used when handling [XML](https://github.com/FasterXML/jackson-datatype-xml), or when using Java Object generated using `JAXB` tools (usually from XML Schema definitions)

Usage samples, see https://github.com/FasterXML/jackson-annotations


## Type handling

* `@JsonSubTypes`: class annotation used to indicate sub-types of annotated type; necessary when deserializing polymorphic types using logical type names (and not class names)
* `@JsonTypeId`: property annotation used to indicate that the property value should be used as the `Type Id` for object, instead of using class name or external type name.
* `@JsonTypeInfo`: class/property annotation used to indicate details of what type information is included in serialization, as well as how.
* `@JsonTypeName`: class annotation used to define logical type name to use for annotated class; type name can be used as `Type Id` (depending on settings of `@JsonTypeInfo`)


### Annotations for choosing more/less specific types

Sometimes the type Jackson uses when reading or writing a property is not quite what you want:

- When reading (deserializing), declared type may be a general type, but you know which exact implementation type to use
- When writing (serializing), Jackson will by default use the specific runtime type; but you may not want to include all information from that type but rather just contents of its supertype.

These cases can be handled by following annotations:

```java
public class ValueContainer {
  // although nominal type is 'Value', we want to read JSON as 'ValueImpl'
  @JsonDeserialize(as=ValueImpl.class)
  public Value value;

  // although runtime type may be 'AdvancedType', we really want to serialize
  // as 'BasicType'; two ways to do this:
  @JsonSerialize(as=BasicType.class)
  // or could also use: @JsonSerialize(typing=Typing.STATIC)
  public BasicType another;
}
```


## Polymorphic type handling

Following annotations work together with standard `@JsonTypeInfo` annotation defined by core annotations package.

* **@JsonTypeResolver** (_class_) can be used to plug in a custom type information handler, to replace default one (for specific class)
* **@JsonTypeIdResolver** (_class_) can be used to replace standard type id converter (type to/from JSON String) with a custom version; for example, to create more convenient handler for using logical type names.

See http://www.cowtowncoder.com/blog/archives/2010/03/entry_372.html


## Object references, identity

* `@JsonManagedReference`, `@JsonBackReference`: pair of annotations used to indicate and handle parent/child relationships expressed with pair of matching properties
* `@JsonIdentityInfo`: class/property annotation used to indicate that `Object Identity` is to be used when serializing/deserializing values, such that multiple references to a single Java Object can be properly deserialized. This can be used to properly deal with cyclic object graphs and directed-acyclic graphs.


## Mixin Annotations

See http://wiki.fasterxml.com/JacksonMixInAnnotations

Here are some notes on what kinds of annotations can be used, and for what purpose:

- All annotation sets that Jackson recognizes (core annotations, JAXB extensions) can be mixed in
- All kinds of annotations (member method, static method, field, constructor annotations) can be mixed in
- Only method (and field) name and signature are used for matching annotations: access definitions (private, protected, ...) and method implementations are ignored
    - hint: since method implementations are ignored, it often makes sense to define mix-ins as **interfaces** or **abstract** classes
    - hint: if you can, it often makes sense to define mix-in class as a **sub-class** of target class, and use @Override JDK annotation to ensure method name and signature match
- Mix-ins work as expected within inheritance hierarchy: it is feasible (and useful) to attach mix-in annotations to super-classes -- if so, mix-in annotations can further be overridden by annotations sub-classes (of target) provide.


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
