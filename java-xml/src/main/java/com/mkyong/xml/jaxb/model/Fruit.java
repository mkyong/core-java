package com.mkyong.xml.jaxb.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlRootElement
// order of the fields in XML
//@XmlType(propOrder = {"price", "name"})

// this tells jaxb impl only takes fields for mapping
// by default, jaxb impl takes get/set pairs, public fields, and annotated non public fields as mapped, if we annotate the field, we get duplicated error
@XmlAccessorType(XmlAccessType.FIELD)
public class Fruit {

    @XmlAttribute
    int id;

    @XmlElement(name = "n")
    String name;

    //@XmlTransient
    String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
