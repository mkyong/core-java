package com.mkyong.io.object;

import java.math.BigDecimal;

public class Person2 extends Person{

    private String address;

    public Person2(String name, int age, BigDecimal salary, String address) {
        super(name, age, salary);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
