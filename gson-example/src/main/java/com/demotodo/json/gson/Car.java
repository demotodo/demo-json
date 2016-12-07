package com.demotodo.json.gson;

import com.google.gson.annotations.Expose;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class Car {

    @Expose(serialize = true)
    public String brand = null;

    public int doors = 0;

    public Boolean hasWindows;

    public transient String s1;     // won't be serialized

    @Expose(serialize = false)
    public String s2;

}
