## Tree Model

**Can I compare JsonNode instances for equality? (and by extension: "Can I compare full JSON trees by just comparing root nodes")**

Yes! JsonNode instances implemented expected deep value comparison, using expected conceptual rules (like: order of JSON Object properties is insignificant for equality, but matters for JSON Arrays).

So you can compare equality of JSON trees by just comparing root nodes like so:
```
    boolean areEqual = tree1RootNode.equals(tree2RootNode);
```


