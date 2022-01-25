package com.mkyong.string.compare;

public class StringCompareNull {

    public static void main(String[] args) {

        String str1 = null;

        // throw NullPointerException
        /*if (str1.equals("hello")) {
            System.out.println("equals");
        }*/

        // check null
        if (str1 != null && str1.equals("hello")) {
            System.out.println("equals");
        }

    }

}
