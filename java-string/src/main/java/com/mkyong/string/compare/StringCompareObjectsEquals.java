package com.mkyong.string.compare;

import java.util.Objects;

public class StringCompareObjectsEquals {

    public static void main(String[] args) {

        String str1 = "apple";
        String str2 = "banana";

        // false
        System.out.println(Objects.equals(str1, str2));

        // true
        System.out.println(Objects.equals(str1, new String("apple")));

        // false
        System.out.println(Objects.equals(null, str2));

        // false
        System.out.println(Objects.equals(str1, null));

        // true
        System.out.println(Objects.equals(null, null));

    }

}
