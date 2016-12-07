package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JacksonInject;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonInject {

    public long id = 0;
    public String name = null;

    @JacksonInject
    public String source = null;

}
