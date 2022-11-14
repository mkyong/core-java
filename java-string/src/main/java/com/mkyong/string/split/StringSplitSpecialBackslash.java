package com.mkyong.string.split;

import java.util.regex.Pattern;

public class StringSplitSpecialBackslash {

    public static void main(String[] args) {

        String dir = "C:\\Users\\mkyong\\projects\\mkyong-tutorials";

        // three ways to escape regex special character
        // String[] output = dir.split("\\\\");
        // String[] output = dir.split("[\\\\]");
        String[] output = dir.split(Pattern.quote("\\"));

        for (String s : output) {
            System.out.println(s);
        }

    }

}
