## Deserializing Polymorphic types

**But what if there are multiple subtypes for given declared type?**

If there are multiple alternate implementations, you need to enable addition of so-called Type Information. For full description, refer to JacksonPolymorphicDeserialization, but there are two basic approaches:

- Use `@JsonTypeInfo` on base type (or next to property) to indicate that additional type information is to be included when serializing values of annotated type (or property).
- Enable "default typing" (using `ObjectMapper.enableDefaultTyping()`), which works similar to as if `@JsonTypeInfo` was implicitly added to set of types (set defined by default typing rules: default rule applies it to java.lang.Object and all abstract types)

In both cases what happens is that during serialization, a type identifier (class name or logical configurable type name) is included in JSON (using one of multiple placement strategies; most common being as a simple property). And when deserializing, deserializer expects to find this type identifier and will use it to correctly determine expected type to create, and then deserialize instance of that concrete type.


## Polymorphic type handling

Starting with version 1.5, Jackson allows fully configurable Polymorphic Type Handling, using following annotations:

- `@JsonTypeInfo` (class) is the main annotation used to enable polymorphic type handling for a type and its subtypes.
- `@JsonSubTypes` (class) allows enumerating sub-types (classes) of given class: it is used if (and only if) sub-types can not be otherwise detected. This is the case when logical type names are used instead of physical Java class names
- `@JsonTypeId`: property annotation used to indicate that the property value should be used as the `Type Id` for object, instead of using class name or external type name.
- `@JsonTypeName` (class) is used to define logical type name for a class
- `@JsonTypeResolver` (class) can be used to plug in a custom type information handler, to replace default one (for specific class)
- `@JsonTypeIdResolver` (class) can be used to replace standard type id converter (type to/from JSON String) with a custom version; for example, to create more convenient handler for using logical type names.


## See Also:
- http://www.cowtowncoder.com/blog/archives/2010/03/entry_372.html
- https://github.com/FasterXML/jackson-docs/wiki/JacksonPolymorphicDeserialization
- http://wiki.fasterxml.com/JacksonPolymorphicDeserialization
