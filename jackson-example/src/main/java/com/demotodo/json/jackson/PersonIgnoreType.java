package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class PersonIgnoreType {

    @JsonIgnoreType
    public static class Address {
        public String streetName = null;
        public String houseNumber = null;
        public String zipCode = null;
        public String city = null;
        public String country = null;
    }

    public long personId = 0;

    public String name = null;

    public Address address = null;
}
