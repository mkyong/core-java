package com.mkyong.java17.jep406;

public class JEP406 {

    public static void main(String[] args) {

        System.out.println(formatterJava17("Java 17"));
        System.out.println(formatterJava17(17));

        testStringJava17("Java 16");  // Ok
        testStringJava17("Java 11");  // LTS
        testStringJava17("");         // Ok
        testStringJava17(null);       // Unknown!
    }

    // check null, before Java 17
    static void testString(String s) {
        if (s == null) {
            System.out.println("Unknown!");
            return;
        }
        switch (s) {
            case "Java 11", "Java 17"   -> System.out.println("LTS");
            default                     -> System.out.println("Ok");
        }
    }

    // check null, Java 17
    static void testStringJava17(String s) {
        switch (s) {
            case null                   -> System.out.println("Unknown!");
            case "Java 11", "Java 17"   -> System.out.println("LTS");
            default                     -> System.out.println("Ok");
        }
    }

    // if...else chain, before Java 17
    static String formatter(Object o) {
        String formatted = "unknown";
        if (o instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (o instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (o instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (o instanceof String s) {
            formatted = String.format("String %s", s);
        }
        return formatted;
    }

    // if...else chain, Java 17
    static String formatterJava17(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> o.toString();
        };
    }

}
