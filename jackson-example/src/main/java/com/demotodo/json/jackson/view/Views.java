package com.demotodo.json.jackson.view;

/**
 * Created by bribin.zheng on 2016/12/29.
 */
public class Views {

    static class Public {
    }

    static class ExtendedPublic extends Public {
    }

    static class Internal extends ExtendedPublic {
    }

}
