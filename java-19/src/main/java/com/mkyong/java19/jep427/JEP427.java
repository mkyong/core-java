package com.mkyong.java19.jep427;

public class JEP427 {

    public static void main(String[] args) {

        testJava19("mkyong");
        testJava19("mkyongmkyong");
    }

    /* Old guarded pattern
    static void test(Object o) {
        switch (o) {
            case String s && s.length() > 6 -> System.out.println("String's length longer than 10!");
            case String s -> System.out.println("String's length is " + s.length());
            default -> {
            }
        }
    }*/

    //Guarded patterns are replaced with when clauses in switch blocks.
    static void testJava19(Object o) {
        switch (o) {
            case String s
                    when s.length() > 10 -> System.out.println("String's length longer than 10!");
            case String s -> System.out.println("String's length is " + s.length());
            default -> {}
        }
    }

}
