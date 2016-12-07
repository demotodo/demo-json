package com.demotodo.json.gson;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * Created by bribin.zheng on 2016/12/7.
 */
public class CarCreator implements InstanceCreator<Car> {

    @Override
    public Car createInstance(Type type) {
        Car car = new Car();
        car.brand = "Toyota";
        return car;
    }

}
