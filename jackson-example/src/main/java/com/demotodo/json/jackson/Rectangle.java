package com.demotodo.json.jackson;

/**
 * Created by bribin.zheng on 2016/12/9.
 */
public class Rectangle {

    final private int w, h;

    public Rectangle(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int getSize() {
        return w * h;
    }

}
