package com.mkyong.java18.jep420;

public class JEP420_2 {

    // java: the switch expression does not cover all possible input values
    // error
    /*static int coverage(Object o) {
        return switch (o) {         // Error - not exhaustive
            case String s  -> s.length();
            case Integer i -> i;
        };
    }*/

    static int coverage(Object o) {
        return switch (o) {
            case String s  -> s.length();
            case Integer i -> i;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        System.out.println("Hello JEP 420");
    }
}
