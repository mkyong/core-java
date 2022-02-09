package com.mkyong.string.split;

import java.util.regex.Pattern;

public class StringSplitSpecialPeriod {

    public static void main(String[] args) {

        String dir = "a.b.c.d.e";

        // three ways to escape regex special character
        //String[] output = dir.split("\\.");
        //String[] output = dir.split("[.]");
        String[] output = dir.split(Pattern.quote("."));

        for (String s : output) {
            System.out.println(s);
        }

    }

}
