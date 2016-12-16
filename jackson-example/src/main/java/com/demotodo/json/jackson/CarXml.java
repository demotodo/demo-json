package com.demotodo.json.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by bribin.zheng on 2016/12/16.
 */
@XmlRootElement(name = "car-xml")
public class CarXml {

    @XmlElement(name = "brand-xml")
    public String brand;

    @XmlElement(name = "doors-xml")
    public int doors;

    @JsonProperty(value = "windows-jackson")
    public int windows;

}
