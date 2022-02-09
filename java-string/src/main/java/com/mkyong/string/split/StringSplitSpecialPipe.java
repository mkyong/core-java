package com.mkyong.string.split;

import java.util.regex.Pattern;

public class StringSplitSpecialPipe {

    public static void main(String[] args) {

        String csv = "a|b|c|d";

        // three ways to escape regex special character
        //  String[] output = csv.split("\\|");
        //  String[] output = csv.split("[|]");
        String[] output = csv.split(Pattern.quote("|"));

        //System.out.println(output.length);

        for (String s : output) {
            System.out.println(s);
        }

    }

}
