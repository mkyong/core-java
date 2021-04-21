package com.mkyong.xml.jaxb.model;

import com.mkyong.xml.jaxb.adaptor.TimeZoneAdaptor;

// @Since 3.0
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.persistence.oxm.annotations.XmlCDATA;

// Java 8?
//import com.sun.xml.internal.txw2.annotation.XmlCDATA;
//import javax.xml.bind.annotation.*;
import java.time.ZonedDateTime;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Staff {

    @XmlAttribute
    int id;
    String name;
    String Salary;
    @XmlCDATA
    String bio;
    @XmlJavaTypeAdapter(TimeZoneAdaptor.class)
    ZonedDateTime joinDate;

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

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ZonedDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(ZonedDateTime joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Salary='" + Salary + '\'' +
                ", bio='" + bio + '\'' +
                ", joinDate=" + joinDate +
                '}';
    }
}