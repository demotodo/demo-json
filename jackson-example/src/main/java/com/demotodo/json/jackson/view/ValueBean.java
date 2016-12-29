package com.demotodo.json.jackson.view;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by bribin.zheng on 2016/12/29.
 */
public class ValueBean {

    public String noview;

    // Name is public
    @JsonView(Views.Public.class)
    String name;

    // Address semi-public
    @JsonView(Views.ExtendedPublic.class)
    String address;

    // SSN only for internal usage
    @JsonView(Views.Internal.class)
    String ssn;

}
