package com.mkyong.xml.jaxb.model;

//import javax.xml.bind.annotation.*;

// @Since 3.0.0
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"name", "list"})
// this tells jaxb impl only takes fields for mapping
// by default, jaxb impl takes get/set pairs, public fields, and annotated non public fields as mapped, if we annotate the field, we get duplicated error
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {

    // if annotate here, need @XmlAccessorType(XmlAccessType.FIELD)
    // alternative is put annotation on setters or getters
    @XmlElement(name = "staff")
    List<Staff> list;

    String name;

    public String getName() {
        return name;
    }

    // ignore this field for mapping
    // @XmlTransient
    public void setName(String name) {
        this.name = name;
    }

    public List<Staff> getList() {
        return list;
    }

    public void setList(List<Staff> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Company{" +
                "list=" + list +
                ", name='" + name + '\'' +
                '}';
    }
}
