## Is ObjectMapper thread-safe?

Short answer: yes

Long answer: yes, as long as you always configure instance before use, and do not call configure methods during operation (or synchronize such calls appropriately). Usually it is better to construct separate mapper instance if configurations differ in any case.

Further: it is beneficial to use just one instance (or small number of instances) for data binding; many optimizations for reuse (of symbol tables, some buffers) depend on ObjectMapper instances being reused.

Instances are fully thread-safe when "configure-then-use" pattern is used (i.e. configuration is not thread-safe but usage is). And when using ObjectWriter and ObjectReader instances even configuration is thread safe.

For more information, check out [JacksonFAQThreadSafety](http://wiki.fasterxml.com/JacksonFAQThreadSafety).


## Tree Model

**Can I compare JsonNode instances for equality? (and by extension: "Can I compare full JSON trees by just comparing root nodes")**

Yes! JsonNode instances implemented expected deep value comparison, using expected conceptual rules (like: order of JSON Object properties is insignificant for equality, but matters for JSON Arrays).

So you can compare equality of JSON trees by just comparing root nodes like so:
```
    boolean areEqual = tree1RootNode.equals(tree2RootNode);
```


