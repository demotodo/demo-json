## Is ObjectMapper thread-safe?

Short answer: yes

Long answer: yes, as long as you always configure instance before use, and do not call configure methods during operation (or synchronize such calls appropriately). 
Usually it is better to construct separate mapper instance if configurations differ in any case.

Further: it is beneficial to use just one instance (or small number of instances) for data binding; 
many optimizations for reuse (of symbol tables, some buffers) depend on ObjectMapper instances being reused.

Instances are fully thread-safe when "configure-then-use" pattern is used (i.e. configuration is not thread-safe but usage is). 
And when using ObjectWriter and ObjectReader instances even configuration is thread safe.

For more information, check out [JacksonFAQThreadSafety](http://wiki.fasterxml.com/JacksonFAQThreadSafety).


## Thread-Safety

The basic rule of Jackson thread-safety is that factories follow "thread-safe after configuration" philosophy. 
This means that:
- Configuring an instance is not synchronized or thread-safe, 
    i.e. do not change settings while using it (which makes sense for other reasons too -- not all settings take effect once mapper has been in use, due to caching of serializers and deserializers)
- Once configuration is complete, operation is fully thread-safe and synchronized in few places where that is needed, for symbol table and buffer reuse.

So as long as you first configure such factories from a single thread, and only then use it (from any number of threads), usage will be thread-safe without additional synchronization.

This rule specifically is used for:
- `ObjectMapper` (and sub-classes)
- `JsonFactory` (and sub-classes, like `SmileFactory`)

Note that more recently some new factory-like classes use "Fluent" pattern, whereby instances are immutable (and thus fully thread-safe!), 
and differently configured instances are constructed by factory methods. Specifically:
- `ObjectReader` and `ObjectWriter` instances are immutable and thread-safe: 
    they are created by `ObjectMapper`, and once constructed, their state NEVER CHANGES. 
- To get instance with different configuration, one uses factory methods to create NEW INSTANCES (existing one is not changed). 
    This allows complete thread-safe use at any point in life cycle.

This latter approach is preferred for future development, so new factory types will probably follow same style.
