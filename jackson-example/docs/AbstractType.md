## Deserializing Abstract types

**If a property has abstract type, how can I make it deserialize into proper concrete type?**

There are two distinct cases: if you just have a simple one-to-one mapping from abstract type (interface) into concrete implementation, there are multiple ways to handle this, shown below. If you have multiple implementation types, please look at the next question ("deserializing polymorphic types")

There are multiple ways to indicate specific implementation class of an abstract type:

- Annotation `@JsonDeserialize(as=Impl.class)` is a simple way to indicate implementation class, and can be used for both classes (default impl for interface / abstract class annotated) and properties (default type for property value annotated)
    - For `java.util.List` values you would use `@JsonDeserialize(contentAs=ValueTypeImpl.class)` instead, and for `java.util.Map` keys, `@JsonDeserialize(keyAs=KeyTypeImpl.class)`.
- You can also register abstract type mappings using `Module` interface: when using `SimpleModule`, you can call: `simpleModule.addAbstractTypeMapping(MyInterface.class, MyImpl.class)`;


## Annotations for choosing more/less specific types

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