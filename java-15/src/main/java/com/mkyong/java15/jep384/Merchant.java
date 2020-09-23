package com.mkyong.java15.jep384;

public class Merchant {

    public Merchant(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "name='" + name + '\'' +
                '}';
    }
}
