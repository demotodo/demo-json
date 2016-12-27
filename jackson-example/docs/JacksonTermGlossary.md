## Jackson Term Glossary

Here is a work-in-progress list of terms you may see referred from other documentation:

### Main classes from 'jackson-core' package

 * JsonFactory
 * JsonParser, JsonGenerator
 * ObjectCodec

Helper classes you may encounter:

 * CharacterEscapes
 * SerializableString (and its implementation, SerializedString)
 * Version, Versioned

### Main classes from 'jackson-mapper' package

 * ObjectMapper
    * related: ObjectReader, ObjectReader
 * SerializationConfig, DeserializationConfig
 * SerializerFactory, DeserializerFactory
    * related: JsonSerializer (ResolvableSerializer, ContextualSerializer), JsonDeserializer (ResolvableDeserializer, ContextualDeserializer)
 * AnnotationIntrospector

Helper classes

 * AbstractTypeResolver
 * HandlerInstantiator

### Main classes from 'xc' package

 * XmlMapper, XmlFactory
 * JaxbAnnotationIntrospector

### Main classes from 'smile' package 

 * SmileFactory

### Main classes from 'mrbean' package 

 * AbstractTypeMaterializer
 * BeanBuilder
