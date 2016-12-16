## Processing model: Streaming API

aka **Incremental Processing**, or **Token Streams**

Of 3 major processing modes that Jackson supports, Streaming Processing (also known as Incremental Processing) is the most efficient way to process JSON content. 
It has the lowest memory and processing overhead, and can often match performance of many binary data formats available on Java platform.

This performance comes at a cost: this is not the most convenient way to process JSON content, because:
- All content to read/write has to be processed in exact same order as input comes in (or output is to go out) -- for random access, you need to use Data Binding or Tree Model (which both actually use Streaming Api for actual JSON reading/writing).
- No Java objects are created unless specifically requested; and even then only very basic types are supported (Strings, byte[] for base64-encoded binary content)

As a result, Streaming API is most commonly used by middleware and by frameworks (where performance benefits are available to wider range of using applications, and competition between implementation drives performance as one of measured features), and less often by applications.
