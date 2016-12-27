# Turbo-charging Jackson

Although Jackson JSON Processor is fast out-of-the-box, with default settings and common usage patterns, there are ways to make it process things even faster.

This presentation looks at couple of things you can use that can make a big difference in performance, for cases where every last drop of CPU power matters.


## Basics: Things You Should Do Anyway

There are some basic ground rules to follow to ensure that Jackson processes things optimally. 
These are things that you should "do anyway", even if you do not have actual performance problems: think of them as an interpretation of the "Boy Scout Rule" ("Always leave the campground cleaner than you found it"). 
Note that guidelines are shown in loosely decreasing order of importance.

1. Reuse heavy-weight objects: `ObjectMapper` (data-binding) and `JsonFactory` (streaming API)
    * To a lesser degree, you may also want to reuse `ObjectReader` and `ObjectWriter` instances -- this is just some icing on the cake, but they are fully thread-safe and reusable
2. Close things that need to be closed: `JsonParser`, `JsonGenerator`
    * This helps reuse underlying things such as symbol tables, reusable input/output buffers
    * Nothing to close for `ObjectMapper`
3. Use "unrefined" (least processed) forms of input: i.e., do not try decorating input sources and output targets:
    * Input: `byte[]` is best if you have it; `InputStream` second best; followed by `Reader` -- and in every case, do NOT try reading input into a `String`!
    * Output: `OutputStream` is best; `Writer` second best; and calling `writeValueAsString()` is the least efficient (why construct an intermediate `String`?)
    * Rationale: Jackson is very good at finding the most efficient (sometimes zero-copy) way to consume/produce JSON encoded data -- allow it do its magic
4. If you need to re-process, then replay and don't re-parse
    * Sometimes you need to process things in multiple phases; 
      for example, you may need to parse part of JSON encoded data to plan out further processing or data-binding rules, 
      and/or modify intermediate presentation for further processing
    * Instead of writing out intermediate forms back as JSON (which will incur both JSON writing and reading overhead), 
      it is better to use a more efficient intermediate form
    * The most efficient intermediate form is `TokenBuffer` (flat sequence of JSON Tokens); 
      followed by JSON Tree model (`JsonNode`)
    * May also want to use `ObjectMapper.convertValue()`, to convert between Object types
5. Use `ObjectReader` method `readValues()` for reading sequences of the same POJO type
    * Functionally equivalent to calling `readValue()` multiple times, but both more convenient AND (slightly) more efficient
6. Prefer `ObjectReader`/`ObjectWriter` over `ObjectMapper`
    * `ObjectReader` and `ObjectWriter` are safer to use 
    -- they are fully immutable and freely shareable between threads 
    -- but they can also be bit more efficient, since they can avoid some of the lookups that `ObjectMapper` has to do


## Specific options for further improving performance

Once you have reviewed the basics discussed above, you may want to consider other tasks specifically aimed at further improving performance.

### Ease vs Compatibility

There are two main criteria that differentiate the approaches listed below:

1. Ease -- how much work is involved in making the change
2. Compatibility -- is the resulting system interoperable with "Plain Old JSON" usage?

### Compatible, not so easy: Use the Streaming API

### Non-compatible, easy: Smile binary "JSON"

### Non-compatible, easy: POJOs as JSON Arrays (Jackson 2.1)

### Compatible, easy: Afterburner


## Maturity of approaches

The approaches discussed so far have different levels of maturity, and this may affect your decision process.

* Streaming API - based converters ("hand-written"): The Streaming API has been available since the first Jackson release
* Smile format: First introduced in Jackson 1.6, very stable, both format and parser/generator implementations
    * Significant amount of real, heavy production use by projects like Elastic Search
* Afterburner: Has been available since Jackson 1.8 -- not experimental, but has not been used as heavily as Smile.
* POJO-as-array: Experimental, included in Jackson 2.1; work in progress


## See Also

- https://github.com/FasterXML/jackson-docs/wiki/Presentation:-Jackson-Performance
- http://wiki.fasterxml.com/JacksonBestPracticesPerformance
