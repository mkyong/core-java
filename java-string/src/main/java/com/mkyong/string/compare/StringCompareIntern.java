package com.mkyong.string.compare;

public class StringCompareIntern {

    public static void main(String[] args) {

        String str1 = "apple";
        String str2 = "apple";

        // true, why true because of the string constant pool
        System.out.println(str1 == str2);

        String str3 = new String("apple");
        // false
        System.out.println(str1 == str3);

        // add this to string pool.
        String str4 = str3.intern();

        // true
        System.out.println(str1 == str4);

        // still false
        System.out.println(str1 == str3);

    }
}
