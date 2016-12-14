package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bribin.zheng on 2016/12/9.
 */
public abstract class RectangleMixIn {

    RectangleMixIn(@JsonProperty("width") int w,
                   @JsonProperty("height") int h) {
    }

    // note: could alternatively annotate fields "w" and "h" as well -- if so, would need to @JsonIgnore getters
    @JsonProperty("width")
    abstract int getW(); // rename property

    @JsonProperty("height")
    abstract int getH(); // rename property

    @JsonIgnore
    abstract int getSize(); // we don't need it!

}
