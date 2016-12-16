# Support for Schemas

Jackson annotations define intended properties and expected handling for POJOs, and in addition to Jackson itself
using this for reading/writing JSON and other formats, it also allows generation of external schemas.
Some of this functionality is included in above-mentioned data-format extensions; but there are also
many stand-alone Schema tools, such as:

## JSON Schema

* [Ant Task for JSON Schema Generation](https://github.com/xdarklight/jackson-jsonschema-ant-task): Generate JSON Schemas from your Java classes with Apache Ant using the Jackson library and extension modules.
* [JSON Schema generator module](https://github.com/FasterXML/jackson-module-jsonSchema): programmatically generate JSON Schema, based on Jackson POJO introspection, including annotations
* [Maven plug-in](https://github.com/FasterXML/jackson-schema-maven-plugin) for JSON Schema generation (based on JSON Schema module)

## Other schema languages

* [Ember Schema Generator](https://github.com/marcus-nl/ember-schema-generator): Generate schemas for [Ember.js](https://github.com/emberjs/ember.js)

