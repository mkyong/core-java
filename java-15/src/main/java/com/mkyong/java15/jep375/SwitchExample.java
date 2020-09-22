package com.mkyong.java15.jep375;

public class SwitchExample {

    public static void main(String[] args) {

        printWithPatternMatching("java15");

    }

    private static void print(Object obj) {

        if (obj instanceof String) {        // instanceof
            String s = (String) obj;        // cast
            if ("java15".equalsIgnoreCase(s)) {
                System.out.println("Hello Java 15");
            }
        } else {
            System.out.println("not a string");
        }

    }

    private static void printWithPatternMatching(Object obj) {

        if (obj instanceof String s) {         // instanceof, cast and bind variable in one line.

            if ("java15".equalsIgnoreCase(s)) {
                System.out.println("Hello Java 15");
            }
        } else {
            System.out.println("not a string");
        }

    }

}
