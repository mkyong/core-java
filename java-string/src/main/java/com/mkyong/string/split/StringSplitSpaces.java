package com.mkyong.string.split;

public class StringSplitSpaces {

    public static void main(String[] args) {

        String str = "1 2   3 4  5";
        String[] output = str.split("\\s+");

        for (String s : output) {
            System.out.println(s);
        }

    }

}