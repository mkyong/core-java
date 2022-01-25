package com.mkyong.string.compare;

public class StringCompare {

    public static void main(String[] args) {

        String str1 = "apple";
        if (str1.equals("apple")) {
            System.out.println("I have an apple.");
        }

        String str2 = "apple";
        if (str2.equalsIgnoreCase("APPLE")) {
            System.out.println("I have an APPLE.");
        }


        // true
        boolean result = new String("mkyong").equals("mkyong");
        System.out.println(result);

        // true
        boolean result2 = "mkyong".equals(new String("mkyong"));
        System.out.println(result2);

        // true
        boolean result3 = "mkyong".equals("mkyong");
        System.out.println(result3);

        // false, equals is case-sensitive
        boolean result4 = new String("mkyong").equals("MKYONG");
        System.out.println(result4);

        // true, equalsIgnoreCase is case-insensitive
        boolean result5 = new String("mkyong").equalsIgnoreCase("MKYONG");
        System.out.println(result5);

    }

}
