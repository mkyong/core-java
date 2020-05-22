package com.mkyong.java14.jep305;

public class InstanceOfApp {

    public static void main(String[] args) {

        String obj = "jdk14";

        // Before Java 14
        if (obj instanceof String) {            // instanceof
            String s = (String) obj;            // cast
            if ("jdk14".equalsIgnoreCase(s)) {
                System.out.println(obj);
            }
        } else {
            System.out.println("not a string");
        }

        // Now Java 14
        if (obj instanceof String s) {          // instanceof, cast and bind variable in one line.
            if ("jdk14".equalsIgnoreCase(s)) {
                System.out.println(obj);
            }
        } else {
            System.out.println("not a string");
        }

    }
}
