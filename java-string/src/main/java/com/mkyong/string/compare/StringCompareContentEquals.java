package com.mkyong.string.compare;

public class StringCompareContentEquals {

    public static void main(String[] args) {

        String str1 = "apple";
        String str2 = "apple";
        StringBuilder sb = new StringBuilder("apple");
        StringBuffer buffer = new StringBuffer("apple");

        // true
        System.out.println(str1.equals(str2));
        // false, .equals cant compare StringBuilder
        System.out.println(str1.equals(sb));

        // .contentEquals supports CharSequence
        // CharSequence implementations: StringBuffer, StringBuilder, String, etc.
        // true
        System.out.println(str1.contentEquals(sb));
        // true
        System.out.println(str1.contentEquals(buffer));

    }

}
