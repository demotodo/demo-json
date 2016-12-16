## Data format modules

Data format modules offer support for data formats other than JSON.
Most of them simply implement `streaming` API abstractions, so that databinding component can be used as is; some offer (and few require) additional `databind` level functionality for handling things like schemas.

Currently following data format modules are fully usable and supported:

* [Avro](https://github.com/FasterXML/jackson-dataformat-avro): supports [Avro](http://en.wikipedia.org/wiki/Apache_Avro) data format, with `streaming` implementation plus additional `databind`-level support for Avro Schemas
* [CBOR](https://github.com/FasterXML/jackson-dataformat-cbor): supports [CBOR](http://tools.ietf.org/search/rfc7049) data format (a binary JSON variant).
* [CSV](https://github.com/FasterXML/jackson-dataformat-csv): supports [Comma-separated values](http://en.wikipedia.org/wiki/Comma-separated_values) format -- `streaming` api, with optional convenience `databind` additions
* [(Java) Properties](https://github.com/FasterXML/jackson-dataformat-properties) (NEW with Jackson 2.7.2!): creating nested structure out of implied notation (dotted by default, configurable), flattening similarly on serialization
* [Protobuf](https://github.com/FasterXML/jackson-dataformat-protobuf) (NEW with Jackson 2.6): supported similar to `Avro`
* [Smile](https://github.com/FasterXML/jackson-dataformat-smile): supports [Smile (binary JSON)](http://wiki.fasterxml.com/SmileFormatSpec) -- 100% API/logical model compatible via `streaming` API, no changes for `databind`
* [XML](https://github.com/FasterXML/jackson-dataformat-xml): supports XML; provides both `streaming` and `databind` implementations. Similar to JAXB' "code-first" mode (no support for "XML Schema first", but can use JAXB beans)
* [YAML](https://github.com/FasterXML/jackson-dataformat-yaml): supports [YAML](http://en.wikipedia.org/wiki/Yaml), which being similar to JSON is fully supported with simple `streaming` implementation


There are also other data format modules, provided by developers outside Jackson core team:

* [BEncode](https://github.com/zsoltm/jackson-dataformat-bencode): support for reading/writing [BEncode](https://en.wikipedia.org/wiki/Bencode) (BitTorrent format) encoded data
* [bson4jackson](https://github.com/michel-kraemer/bson4jackson): adds support for [BSON](http://en.wikipedia.org/wiki/BSON) data format (by Mongo project).
    * Implemented as full streaming implementation, which allows full access (streaming, data-binding, tree-model)
    * Also see [MongoJack] library below; while not a dataformat module, it allows access to BSON data as well.
* [jackson-dataformat-msgpack](https://github.com/msgpack/msgpack-java/tree/develop/msgpack-jackson) adds support [MessagePack](http://en.wikipedia.org/wiki/MessagePack) (aka `MsgPack`) format
    * Implemented as full streaming implementation, which allows full access (streaming, data-binding, tree-model)
* [HOCON](https://github.com/jclawson/jackson-dataformat-hocon): experimental, partial implementation to support [HOCON](https://github.com/typesafehub/config) format -- work in progress
* [Rison](https://github.com/Hronom/jackson-dataformat-rison): Jackson backend to support [Rison](https://github.com/Nanonid/rison))
