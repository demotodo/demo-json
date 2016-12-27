# Jackson Annotations

Multiple sets of annotations may be used to configure how Jackson databinding works.
Document is split along the lines of Jackson component that defines annotations and/or adds support for handling annotations.

* [Core Jackson annotations](https://github.com/FasterXML/jackson-annotations/wiki/Jackson-Annotations) are defined by `jackson-annotations` core component, which has no dependencies to any other package
* [Jackson Databind annotations](https://github.com/FasterXML/jackson-databind/wiki/Databind-Annotations) are defined by `jackson-databind` and depend on types of `jackson-core` and `jackson-annotations`
* [Support for JAXB annotations](https://github.com/FasterXML/jackson-module-jaxb-annotations/) is optional add-on, usually used when handling [XML](https://github.com/FasterXML/jackson-datatype-xml), or when using Java Object generated using `JAXB` tools (usually from XML Schema definitions)

Usage samples, see https://github.com/FasterXML/jackson-annotations


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


## XML Annotations

https://github.com/FasterXML/jackson-dataformat-xml/wiki/Jackson-XML-annotations

- `@JacksonXmlElementWrapper` allows specifying XML element to use for wrapping `List` and `Map` properties
- `@JacksonXmlProperty` allows specifying XML namespace and local name for a property; as well as whether property is to be written as an XML element or attribute.
- `@JacksonXmlRootElement` allows specifying XML element to use for wrapping the root element (default uses 'simple name' of the value class)
- `@JacksonXmlText` allows specifying that value of one property is to be serialized as "unwrapped" text, and not in an element.
- `@JacksonXmlCData` allows specifying that the value of a property is to be serialized within a CData tag.
