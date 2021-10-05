package com.mkyong.io.object;

import java.io.Serializable;
import java.math.BigDecimal;

public class Person implements Serializable {

    // optional, if missing, JVM will create it.
    // better declare as a version control.
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;

    // dun save this field into file
    private transient BigDecimal salary;

    public Person(String name, int age, BigDecimal salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
