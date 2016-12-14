package com.demotodo.json.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by bribin.zheng on 2016/12/9.
 */
public class MixInModule extends SimpleModule {

    public MixInModule() {
        super("MixInModule");

        setMixInAnnotation(Rectangle.class, RectangleMixIn.class);
    }

}
