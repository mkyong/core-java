package com.mkyong.string.utils;

public class CustomStringUtils {

    public static boolean isNumber(String input) {

        // if null return false.
        if (input == null) return false;

        // trim for extra spaces
        input = input.trim();

        // if empty return false
        if ("".equals(input)) return false;

        if (input.startsWith("-") || input.startsWith("+")) {
            // positive or negative numbers, cut the first char
            // +1 -> 1, -1 -> 1
            return input.substring(1).matches("[0-9]+");
        } else {
            // other numbers, let check it
            return input.matches("[0-9]+");
        }

    }

}
